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
import ca.mcgill.ecse.carshop.model.User;
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
	private String username;
	private String password;
	private int initialSize;
	private String oldServiceName;
	
	//This is the CucumberStepDefinitions code for signUpCustomer
	
	@Given("a Carshop system exists")
	public void a_carshop_system_exists() {
		carshop = CarShopApplication.getCarShop();
		error = "";
		errorCnt = 0;
	}

	@Given("there is no existing username {string}")
	public void there_is_no_existing_username(String string) {
		User i=getUserWithUsername(string);
		if(i!=null) {
			i.delete();
		}
	}

	@When("the user provides a new username {string} and a password {string}")
	public void the_user_provides_a_new_username_and_a_password(String string, String string2) {
	    username=string;
	    password=string2;
	    initialSize=carshop.getCustomers().size();
	     try{
	     	 CarShopController.signUpCustomerAccount(username,password);
	     }
	     catch(InvalidInputException e){
	    	 error=e.getMessage();
		 	 errorCnt++;
	     }
	}

	@Then("a new customer account shall be created")
	public void a_new_customer_account_shall_be_created() {
		assertEquals(initialSize+1, carshop.getCustomers().size());
		assertNotNull(getUserWithUsername(username));	
	}

	@Then("the account shall have username {string} and password {string}")
	public void the_account_shall_have_username_and_password(String string, String string2) {
	    for(Customer i : carshop.getCustomers()) {
	    	if(i.getUsername().equals(string)) {
	    		assertEquals(string2,i.getPassword());
	    		return;
	    	}
	    }
	    throw new AssertionError();
	}

	@Then("no new account shall be created")
	public void no_new_account_shall_be_created() {
		assertEquals(initialSize, carshop.getCustomers().size());
		assertNull(getUserWithUsername(username));
	}

	@Then("an error message {string} shall be raised")
	public void an_error_message_shall_be_raised(String string) {
	    assertEquals(this.error,string);
	    error="";
	}

	@Given("there is an existing username {string}")
	public void there_is_an_existing_username(String string) {
	    if(getUserWithUsername(string)==null) {
	    	if(string.equals("owner")) {
	    		carshop.setOwner(new Owner(string,string,carshop));
	    	}
	    	else if(string.equals("Tire-Technician") || string.equals("Engine-Technician") || string.equals("Fluids-Technician") || string.equals("Electronics-Technician") || string.equals("Transmission-Technician")) {
	    		carshop.addTechnician(string,string,getTechTypeFromUsername(string));
	    	}
	    	else {
	    		try {
	    			CarShopController.signUpCustomerAccount(string, string);
	    		}
	    		catch(InvalidInputException e) {
	    			error=e.getMessage();
	    			errorCnt++;
	    		}
	    	}
	    }
	}

	@Given("the user is logged in to an account with username {string}")
	public void the_user_is_logged_in_to_an_account_with_username(String string) {
		try {
			User u = getUserWithUsername(string);
			CarShopController.logIn(u.getUsername(), u.getPassword());
		}
		catch(InvalidInputException e) {
			error=e.getMessage();
			errorCnt++;
		}
	    
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
	public   void eachTechnicianHasGarage() {
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
			User u = getUserWithUsername(username);
			CarShopController.logIn(username, u.getPassword());
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
	@When("{string} initiates the update of the service {string} to name {string}, duration {string}, belonging to the garage of {string} technician")
	public void updateService(String username, String oldName, String newName, String duration, String garageStr) throws InvalidInputException {
		try	{
			Garage garage = getGarageOfTechnician(getTechnicianTypeFromString(garageStr));
			this.oldServiceName = oldName;
			
			System.out.println("here");
			int durationValue = Integer.valueOf(duration);
			System.out.println("here2");
			
			CarShopController.updateService(oldName, newName, Integer.valueOf(duration), garage);
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
		this.error = null;
	}
	@Then("the service {string} shall not exist in the system")
	public void checkNotExistService(String name) {
		assertNull(getServiceFromName(name));
	}
	@Then("the service {string} shall still preserve the following properties:")
	public void checkServiceProperties(String name, DataTable table) {
		List<Map<String, String>> maps = table.asMaps();
		for(Map<String, String> map: maps) {
			Service service = getServiceFromName(name);
			assertNotNull(service);
			assertEquals(service.getDuration(), Integer.valueOf(map.get("duration")));
			Garage g = getGarageOfTechnician(getTechnicianTypeFromString(map.get("garage")));
			assertEquals(service.getGarage(), g);
		}
		
	}
	
	@Then("the service {string} shall be updated to name {string}, duration {string}")
	public void checkServiceUpdated(String oldName, String newName, String newDuration) {
		if(!oldName.equals(newName)) {
			assertNotNull(this.oldServiceName);
			assertNull(getServiceFromName(this.oldServiceName));
		}
		

		assertEquals(oldName, this.oldServiceName);
		
		Service newService = getServiceFromName(newName);
		assertNotNull(newService);
		assertEquals(Integer.valueOf(newDuration), newService.getDuration());
		this.oldServiceName = null;
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
	private User getUserWithUsername(String username) {
		for(Customer c: carshop.getCustomers()) {
			if(c.getUsername().equals(username)) {
				return c;
			}
		}
		
		for(Technician tech: carshop.getTechnicians()) {
			if(tech.getUsername().equals(username)) {
				return tech;
			}
		}
		
		if(username.equals("owner")) {
			return carshop.getOwner() != null ? carshop.getOwner(): null;
		}
		
		return null;
	}
	
	private static TechnicianType getTechTypeFromUsername(String username) {
		  switch(username) {
			case "Tire-Technician":
				  return TechnicianType.Tire;
			case "Engine-Technician":
				  return TechnicianType.Engine;
			case "Fluids-Technician":
				return TechnicianType.Fluids;
			case "Electronics-Technician":
				return TechnicianType.Electronics;
			case "Transmission-Technician":
				return TechnicianType.Transmission;
			default:
				return null;
		  }
	  }
}