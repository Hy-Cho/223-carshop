package ca.mcgill.ecse.carshop.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.model.Business;
import ca.mcgill.ecse.carshop.model.CarShop;
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
	
	//When Clauses
	@When("{username} initiates the addition of the service {name} belonging to the garage of {garage}")
	public void serviceAddedToSystem(String username, String name, String type) {
		TechnicianType techType = getTechnicianTypeFromString(type);
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
}