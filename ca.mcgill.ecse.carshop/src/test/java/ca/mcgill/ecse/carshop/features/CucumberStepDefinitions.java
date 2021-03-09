package ca.mcgill.ecse.carshop.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.List;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;
import ca.mcgill.ecse.carshop.model.Business;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.Customer;
import ca.mcgill.ecse.carshop.model.Garage;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Technician;
import ca.mcgill.ecse.carshop.model.Technician.TechnicianType;
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
	
	//This is the CucumberStepDefinitions code for signUpCustomer
	
	@Given("a Carshop system exists")
	public void a_carshop_system_exists() {
		carshop = CarShopApplication.getCarShop();
		error = "";
		errorCnt = 0;
	}

	@Given("there is no existing username {string}")
	public void there_is_no_existing_username(String string) {
		for(Customer i : carshop.getCustomers()) {
			if(i.getUsername().equals(string)) {
				i.delete();
			}
		}
	}

	@When("the user provides a new username {string} and a password {string}")
	public void the_user_provides_a_new_username_and_a_password(String string, String string2) {
	    username=string;
	    password=string2;
	}

	@Then("a new customer account shall be created")
	public void a_new_customer_account_shall_be_created() {
		int initialSize=carshop.getCustomers().size();
		try{
			CarShopController.signUpCustomerAccount(username,password);
			assertEquals(initialSize+1, carshop.getCustomers().size());
		}
		catch(InvalidInputException e) {
			error=e.getMessage();
			errorCnt++;
		}
	}

	@Then("the account shall have username {string} and password {string}")
	public void the_account_shall_have_username_and_password(String string, String string2) {
	    for(Customer i : carshop.getCustomers()) {
	    	if(i.getUsername().equals(string)) {
	    		assertEquals(string2,i.getPassword());
	    	}
	    }
	}

	@Then("no new account shall be created")
	public void no_new_account_shall_be_created() {
	    
	}

	@Then("an error message {string} shall be raised")
	public void an_error_message_shall_be_raised(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("there is an existing username {string}")
	public void there_is_an_existing_username(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("the user is logged in to an account with username {string}")
	public void the_user_is_logged_in_to_an_account_with_username(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	
	
	@Given("an owner account exists in the system")
	public void thereIsAnOwner()  {
		Owner owner = new Owner("owner", "password", this.carshop);
	}
	
	@Given("a business exists in the system")
	public void thereIsABusiness() {
		Business business = new Business("Car Shop", "McGill", "mario.bouzakhm@mail.mcgill.ca", "(514) 123-1342", this.carshop);
	}
	
	@Given("the following technicians exist in the system")
	public void thereIsTechnicians(String username, String password, String type) {
		TechnicianType techType = getTechnicianTypeFromString(type);
		
		Technician technician = new Technician(username, password, techType, this.carshop);
	}
	@Given("each technician has their own garage")
	public void eachTechnicianHasGarage() {
		for(Technician tech: this.carshop.getTechnicians()) {
			Garage garage = new Garage(this.carshop, tech);
		}
	}
	
	@Given("the Owner with username {username} is logged in")
	public void ownerWithUsernameLoggedIn(String username) {
		Owner owner = new Owner(username, "password", this.carshop);
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
}