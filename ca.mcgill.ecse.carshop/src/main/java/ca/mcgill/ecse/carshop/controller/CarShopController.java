package ca.mcgill.ecse.carshop.controller;

import java.util.List;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.model.BookableService;
import ca.mcgill.ecse.carshop.model.BusinessHour;
import ca.mcgill.ecse.carshop.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.carshop.model.Technician.TechnicianType;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.Garage;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Service;
import ca.mcgill.ecse.carshop.model.User;

public class CarShopController {
	
	private static User loggedInUser;
	
	public CarShopController() {}
	
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
	
	private static BusinessHour getBussinessHourOfDayByGarage(Garage g, DayOfWeek day) {
		List<BusinessHour> businessHourPerGarage = g.getBusinessHours();
		for(BusinessHour hours: businessHourPerGarage) {
			if(hours.getDayOfWeek() == day) {
				return hours;
			}
		}
		
		return null;
	}
	
	private static Garage getGarageOfTechnician(TechnicianType techType) {
		CarShop carshop = CarShopApplication.getCarShop();
		if(carshop == null) {
			return null;
		}
		
		List<Garage> garages = carshop.getGarages();
		for(Garage garage: garages) {
			if (garage.getTechnician().getType() == techType) {
				return garage;
			}
		}
		return null;
	}
}
