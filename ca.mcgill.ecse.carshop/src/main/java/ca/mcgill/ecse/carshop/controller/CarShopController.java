package ca.mcgill.ecse.carshop.controller;

import java.util.List;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.model.BookableService;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.Garage;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Service;
import ca.mcgill.ecse.carshop.model.Technician;
import ca.mcgill.ecse.carshop.model.User;

public class CarShopController {
	
	private static User loggedInUser;
	
	public CarShopController() {}
	
	public static void signUpCustomerAccount(String username, String password) throws InvalidInputException {
		CarShop carShop=CarShopApplication.getCarShop();
		if(username == null || username.length()==0) {
			throw new InvalidInputException("The user name cannot be empty");
		}
		if( password == null || password.length()==0) {
			throw new InvalidInputException("The password cannot be empty");
		}
		
		if(loggedInUser.getUsername().equals("owner")) {
			throw new InvalidInputException("You must log out of the owner account before creating a customer account");
		}
		
		if(loggedInUser.getUsername().equals("Tire-Technician") || loggedInUser.getUsername().equals("Engine-Technician") || loggedInUser.getUsername().equals("Transmission-Technician") || loggedInUser.getUsername().equals("Electronics-Technician") || loggedInUser.getUsername().equals("Fluids-Technician") ) {
			throw new InvalidInputException("You must log out of the technician account before creating a customer account");
		}
		
		try {
			carShop.addCustomer(username, password);
		}
		catch(RuntimeException e) {
			if(e.getMessage().startsWith("Cannot create due to duplicate")) {
				throw new InvalidInputException("The username already exists");
			}
			throw new InvalidInputException(e.getMessage());
		}
	}
	public static void updateCustomerAccount(String newUsername, String newPassword) throws InvalidInputException {
		if(newUsername==null || newUsername.length()==0) {
			throw new InvalidInputException("The user name cannot be empty");
		}
		if(newPassword==null || newPassword.length()==0) {
			throw new InvalidInputException("The password cannot be empty");
		}
		if(loggedInUser instanceof Owner) {
			throw new InvalidInputException("Changing username of owner is not allowed");
		}
		if(loggedInUser instanceof Technician) {
			throw new InvalidInputException("Changing username of technician is not allowed");
		}
		try {
			if(loggedInUser.setUsername(newUsername)==false) {
				throw new InvalidInputException("Username not available");
			}
			loggedInUser.setPassword(newPassword);
		}
		catch(RuntimeException e) {
			
			throw new InvalidInputException(e.getMessage());
		}
	}
	public static void createService(String name, int duration, Garage garage) throws RuntimeException, InvalidInputException {
		if(loggedInUser == null  || loggedInUser.getUsername() != "owner" || !(loggedInUser instanceof Owner)) {
			throw new RuntimeException("You are not autorized to perform this operation");
		}
		
		CarShop carShop = CarShopApplication.getCarShop();
		
		try {
			Service service = new Service(name, carShop, duration, garage);
			carShop.addBookableService(service);
		}
		catch(RuntimeException ex) {
			throw new InvalidInputException(ex.getMessage());
		}
	}
	
	public static void updateService(String oldName, String newName, int newDuration, Garage newGarage) {
		if(loggedInUser == null  || loggedInUser.getUsername() != "owner" || !(loggedInUser instanceof Owner)) {
			throw new RuntimeException("You are not autorized to perform this operation");
		}
		
		CarShop carShop = CarShopApplication.getCarShop();
		Service serviceToModify = getServiceFromName(oldName, carShop);
		if(serviceToModify != null) {
			serviceToModify.setName(newName);
			serviceToModify.setDuration(newDuration);
			serviceToModify.setGarage(newGarage);
		}
	}
	
	public static User getLoggedInUser() {
		return loggedInUser;
	}
	private static Service getServiceFromName(String name, CarShop carShop) {
		List<BookableService> bookableServices = carShop.getBookableServices();
		for(BookableService bookableService: bookableServices) {
			if(bookableService instanceof Service) {
				Service service = (Service) bookableService;
				if(service.getName().equals(name)) {
					return service;
				}
			}
		}
		
		return null;
	}
}
