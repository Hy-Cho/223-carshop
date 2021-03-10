package ca.mcgill.ecse.carshop.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;
import ca.mcgill.ecse.carshop.model.BookableService;
import ca.mcgill.ecse.carshop.model.Business;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.Customer;
import ca.mcgill.ecse.carshop.model.Garage;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Service;
import ca.mcgill.ecse.carshop.model.Technician;
import ca.mcgill.ecse.carshop.model.Technician.TechnicianType;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberStepDefinitions {
	private CarShop carshop;
	private String error;
	private int errorCnt;
	
	//Given clauses
	@Given("a Carshop system exists")
	public void thereIsACarShopSystem() {
		carshop = CarShopApplication.getCarShop();
		error = "";
		errorCnt = 0;
	}
	
	@Given("an owner account exists in the system")
	public void thereIsAnOwner()  {
		Owner owner = new Owner("owner", "password", this.carshop);
	}
	
	@Given("a business exists in the system")
	public void thereIsABusiness() {
		Business business = new Business("Car Shop", "McGill", "mario.bouzakhm@mail.mcgill.ca", "(514) 123-1342", this.carshop);
	}
	
	@Given("the following technicians exist in the system:")
	public void thereIsTechnicians(DataTable dataTable) {
		List<Map<String, String>> listReresentation = dataTable.asMaps(String.class, String.class);
		for(Map<String, String> list: listReresentation) {
			String username = list.get("username");
			String password = list.get("password");
			String type = list.get("type");
			
			TechnicianType techType = getTechnicianTypeFromString(type);
			
			Technician technician = new Technician(username, password, techType, this.carshop);
		}
	}
	@Given("each technician has their own garage")
	public void eachTechnicianHasGarage() {
		for(Technician tech: this.carshop.getTechnicians()) {
			Garage garage = new Garage(this.carshop, tech);
		}
	}
	@Given("the following services exist in the system:")
	public void exisitingServiceInSystem(DataTable dataTable) {
		List<Map<String, String>> listReresentation = dataTable.asMaps(String.class, String.class);
		for(Map<String, String> list: listReresentation) {
			String username = list.get("name");
			int duration = Integer.valueOf(list.get("duration"));
			
			Garage g = getGarageOfTechnician(getTechnicianTypeFromString(list.get("garage")));
			Service service = new Service(username, carshop, Integer.valueOf(duration), g);	
		}
	}
	
	@Given("the Owner with username {string} is logged in")
	public void ownerWithUsernameLoggedIn(String username) {
		try {
			CarShopController.logIn(username, "owner");
		} catch (InvalidInputException e) {
			error = e.getMessage();
			errorCnt++;
		}
	}
	@Given("the user with username {string} is logged in")
	public void userLoggedIn(String username) {
		try {
			Customer c = getCustomerWithUsername(username);
			CarShopController.logIn(username, c.getPassword());
		} catch (InvalidInputException e) {
			error = e.getMessage();
			errorCnt++;
		}
	}
	
	@Given("the following customers exist in the system:")
	public void existingCustomer(DataTable dataTable) {
		List<Map<String, String>> listReresentation = dataTable.asMaps(String.class, String.class);
		for(Map<String, String> list: listReresentation) {
			String username = list.get("username");
			String password = list.get("password");
			
			Customer cust = new Customer(username, password, carshop);
		}
	}
	
	@When("{string} initiates the addition of the service {string} with duration {string} belonging to the garage of {string} technician")
	public void initiatesServiceAdded(String username, String name, String duration, String garageStr) {
		try {
			Garage garage = getGarageOfTechnician(getTechnicianTypeFromString(garageStr));
			CarShopController.createService(name, Integer.valueOf(duration), garage);
		}
		catch(InvalidInputException ex) {
			error = ex.getMessage();
			errorCnt++;
		}
		catch(RuntimeException ex) {
			error = ex.getMessage();
			errorCnt++;
		}
	}
	@Then("the service {string} shall exist in the system")
	public void checkServiceInSystem(String name) {
		assertNotNull(getServiceFromName(name));
	}
	@Then("the service {string} shall belong to the garage of {string} technician")
	public void checkServiceInGarage(String name, String garage) {
		Garage g = getGarageOfTechnician(getTechnicianTypeFromString(garage));
		assertNotNull(getServiceFromNameInGarage(name, g));
	}
	@Then("the number of services in the system shall be {string}")
	public void checkServiceNumbers(String number) {
		assertEquals(Integer.valueOf(number), getNumberOfServicesInSystem());
	}
	@Then("an error message with content {string} shall be raised")
	public void checkErrorRaised(String errorMessage) {
		assertEquals(errorMessage, this.error);
	}
	@Then("the service {string} shall not exist in the system")
	public void checkNotExistService(String name) {
		assertNull(getServiceFromName(name));
	}
	@Then("the service {string} shall still preserve the following properties:")
	public void checkServiceProperties(String name, String duration, String garage) {
		Service service = getServiceFromName(name);
		assertNotNull(service);
		assertEquals(service.getDuration(), Integer.valueOf(duration));
		Garage g = getGarageOfTechnician(getTechnicianTypeFromString(garage));
		assertEquals(service.getGarage(), g);
		
	}
	
	@After
	public void tearDown() {
		carshop.delete();
	}
	
	private TechnicianType getTechnicianTypeFromString(String type) {
		TechnicianType techType;
		switch(type) {
			case "Tire":
				techType = TechnicianType.Tire;
				break;
			case "Engine":
				techType = TechnicianType.Engine;
				break;
			case "Transmission":
				techType = TechnicianType.Transmission;
				break;
			case "Electronics":
				techType = TechnicianType.Electronics;
				break;
			case "Fluids":
				techType = TechnicianType.Fluids;
				break;
			default:
				techType = null;
				break;
		}
		return techType;
	}
	
	private Garage getGarageOfTechnician(TechnicianType techType) {
		if(this.carshop == null) {
			return null;
		}
		
		List<Garage> garages = this.carshop.getGarages();
		for(Garage garage: garages) {
			if (garage.getTechnician().getType() == techType) {
				return garage;
			}
		}
		return null;
	}
	
	private int countServicesInSystem() {
		int count = 0;
		for(BookableService bookableService: carshop.getBookableServices()) {
			if(bookableService instanceof Service) {
				count++;
			}
		}
		
		return count;
	}
	
	private Service getServiceFromName(String name) {
		for(BookableService bookableService: carshop.getBookableServices()) {
			if(bookableService instanceof Service && bookableService.getName().equals(name)) {
				return (Service) bookableService;
			}
		}
		
		return null;
 	}
	private Service getServiceFromNameInGarage(String name, Garage garage) {
		for(Service service: garage.getServices()) {
			if(service.getName().equals(name)) {
				return service;
			}
		}
		return null;
	}
	private int getNumberOfServicesInSystem() {
		int count = 0;
		
		for(BookableService bookableService: carshop.getBookableServices()) {
			if(bookableService instanceof Service) {
				count++;
			}
		}
		
		return count;
	}
	private Customer getCustomerWithUsername(String username) {
		for(Customer c: carshop.getCustomers()) {
			if(c.getUsername().equals(username)) {
				return c;
			}
		}
		return null;
	}
}