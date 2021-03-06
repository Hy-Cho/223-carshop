package ca.mcgill.ecse.carshop.controller;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.Garage;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Service;
import ca.mcgill.ecse.carshop.model.User;

public class CarShopController {
	
	private static User loggedInUser;
	
	public CarShopController() {}
	
	public static void createService(String name, int duration, Garage garage) throws InvalidInputException {
		if(loggedInUser == null  || loggedInUser.getUsername() != "owner" || !(loggedInUser instanceof Owner)) {
			throw new RuntimeException("You are not autorized to perform this operation");
		}
		
		CarShop carShop = CarShopApplication.getCarShop();
		Service service = new Service(name, carShop, duration, garage);
		try {
			carShop.addBookableService(service);
		}
		catch(RuntimeException ex) {
			throw new InvalidInputException(ex.getMessage());
		}
	}

}
