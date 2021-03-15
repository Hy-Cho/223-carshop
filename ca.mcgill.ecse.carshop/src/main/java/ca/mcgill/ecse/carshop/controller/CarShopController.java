package ca.mcgill.ecse.carshop.controller;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.model.BookableService;
import ca.mcgill.ecse.carshop.model.Business;
import ca.mcgill.ecse.carshop.model.BusinessHour;
import ca.mcgill.ecse.carshop.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.carshop.model.Technician.TechnicianType;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.Customer;
import ca.mcgill.ecse.carshop.model.Garage;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Service;
import ca.mcgill.ecse.carshop.model.TimeSlot;
import ca.mcgill.ecse.carshop.model.Technician;
import ca.mcgill.ecse.carshop.model.User;
import java.util.List;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.ComboItem;
import ca.mcgill.ecse.carshop.model.User;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Service;
import ca.mcgill.ecse.carshop.model.ServiceCombo;
import ca.mcgill.ecse.carshop.model.BookableService;


public class CarShopController {
	
	private static User loggedInUser;

	private static Date today = Date.valueOf(LocalDate.of(2021, 2, 1));
	private static Time now = Time.valueOf(LocalTime.of(11, 0));
		
	// signUpCustomerAccount was coded by Sami Ait Ouahmane
	// It it doesn't find any exceptions, it signs up the user for a new customer account
	public static void signUpCustomerAccount(String username, String password) throws InvalidInputException {
		CarShop carShop=CarShopApplication.getCarShop();
		//If the username string is null or empty, throw an exception 
		if(username == null || username.length()==0) {
			throw new InvalidInputException("The user name cannot be empty");
		}
		//If the password string is null or empty, throw an exception 
		if( password == null || password.length()==0) {
			throw new InvalidInputException("The password cannot be empty");
		}
		
		//If the loggedInUser isn't null, verify whether it represents the owner or a technician. If it does, throw an error
		if(loggedInUser!=null) {
			if(loggedInUser.getUsername().equals("owner")) {
				throw new InvalidInputException("You must log out of the owner account before creating a customer account");
			}
			if(loggedInUser.getUsername().equals("Tire-Technician") || loggedInUser.getUsername().equals("Engine-Technician") || loggedInUser.getUsername().equals("Transmission-Technician") || loggedInUser.getUsername().equals("Electronics-Technician") || loggedInUser.getUsername().equals("Fluids-Technician") ) {
				throw new InvalidInputException("You must log out of the technician account before creating a customer account");
			}
		}
		
		//Add the customer. Catch the error if any is thrown.
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
	
	//updateCustomerAccount was coded by Sami Ait Ouahmane
	public static void updateCustomerAccount(String newUsername, String newPassword) throws InvalidInputException {
		
		//If the new username string is null or empty, throw an exception 
		if(newUsername==null || newUsername.length()==0) {
			throw new InvalidInputException("The user name cannot be empty");
		}
		
		//If the new password string is null or empty, throw an exception 
		if(newPassword==null || newPassword.length()==0) {
			throw new InvalidInputException("The password cannot be empty");
		}
		
		//If the logged-in user is the owner and that the new username is different from "owner", throw an error
		if(loggedInUser instanceof Owner && !newUsername.equals("owner")) {
			throw new InvalidInputException("Changing username of owner is not allowed");
		}
		
		//If the logged-in user is a technician and that the new username is different from that technician old username, throw an error
		if(loggedInUser instanceof Technician && !(loggedInUser.getUsername().equals(newUsername))) {
			throw new InvalidInputException("Changing username of technician is not allowed");
		}
		
		//Ty updating username and password and throw an error message if a runtime exception occurs
		try {
			//If setUsername returns false, then the username is already taken by somebody else
			if(loggedInUser.setUsername(newUsername)==false ) {
				throw new InvalidInputException("Username not available");
			}
			
			//set Username and passowrd
			loggedInUser.setUsername(newUsername);
			loggedInUser.setPassword(newPassword);
		}
		catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());	
		}
	}
	
	// logIn was coded by Hadi Ghaddar
	public static void logIn(String username, String password) throws InvalidInputException {
		CarShop carShop = CarShopApplication.getCarShop();
		TechnicianType techType = getTechTypeFromUsername(username);
		if(username.equals("owner")) {
			Owner owner = carShop.getOwner();
			
			if(owner == null) {
				owner = new Owner("owner", password, carShop);
			}
			loggedInUser = owner;
			
		}
		//Make sure to implement the case where the case for technicians
		else if(techType != null) {
			Technician existingTechAccount = getTechnicianWithTechType(techType);
			if(existingTechAccount == null) {
				existingTechAccount = new Technician(username, password, techType, carShop);
			}
			Garage garage = new Garage(carShop, existingTechAccount);
			for(DayOfWeek day: DayOfWeek.values()) {
				BusinessHour dayBusinessHour = getBusinessHoursOfShopByDay(day);
				CarShopController.updateGarageOpeningHours(day, dayBusinessHour.getStartTime(), dayBusinessHour.getEndTime());
			}
			
			loggedInUser = existingTechAccount;
		}
		else {
			Customer cust = getCustomerWithUsername(username);
			if(cust != null) {
				String custPassword = cust.getPassword();
				if(custPassword.equals(password)) {
					loggedInUser = cust;
					return;
				}
				
			}
			
			loggedInUser = null;
			throw new InvalidInputException("Username/password not found");
		}
	}
	// UpdateGarageOpeningHours was coded by Hadi Ghaddar
	public static void updateGarageOpeningHours(DayOfWeek day, Time startTime, Time endTime) throws InvalidInputException {
		CarShop carShop = CarShopApplication.getCarShop();
	    List<BusinessHour> bHour = carShop.getBusiness().getBusinessHours();
	    if (!(loggedInUser instanceof Technician)) {
	      throw new InvalidInputException("You are not authorized to perform this operation");
	    }
	    if (startTime.after(endTime)) {
	      throw new InvalidInputException("Start time must be before end time");
	    }
	    
	    Technician tech = (Technician) loggedInUser;
	    
	    BusinessHour b = getBusinessHoursOfShopByDay(day);
	    if (!(b.getStartTime().before(startTime) && b.getEndTime().after(endTime))) {
	    	throw new InvalidInputException("The opening hours cannot overlap");        
	    }
	    
	    Garage g = tech.getGarage();
	    
	    BusinessHour bHourGarage = getBussinessHourOfDayByGarage(g,day);
	    if (bHourGarage == null) {
	    	bHourGarage = new BusinessHour(day, startTime, endTime, carShop);
	    	g.addBusinessHour(bHourGarage);
	    }
	    else {
	    	bHourGarage.setStartTime(startTime);
	    	bHourGarage.setEndTime(endTime);
	    }
	}
	public static void createService(String name, int duration, Garage garage) throws RuntimeException, InvalidInputException {
		if(loggedInUser == null  || loggedInUser.getUsername() != "owner" || !(loggedInUser instanceof Owner)) {
			throw new RuntimeException("You are not authorized to perform this operation");
		}
		
		  CarShop carShop = CarShopApplication.getCarShop();
		   
		  try {
	            Service service = new Service(name, carShop, duration, garage);
	 	       
	            if(duration <= 0) {
	    			throw new RuntimeException("Duration must be positive");
	    		}
	    		if(getServiceFromName(name, carShop) != null) {
	    			throw new InvalidInputException("Service "+name+ " already exists");
	    		}
	        }
		catch(RuntimeException ex) {
			throw new InvalidInputException(ex.getMessage());
		}
	}
		  

	public static void defineCombo(String name, Service mainService, List<Service> services, List<Boolean> mandatory) throws RuntimeException, InvalidInputException {
		CarShop carShop = CarShopApplication.getCarShop();
		if(loggedInUser == null  || loggedInUser.getUsername() != "owner"|| !(loggedInUser instanceof Owner)) {
			throw new RuntimeException("You are not authorized to perform this operation");
		}
		
		if(services.size()<ServiceCombo.minimumNumberOfServices()) {
			throw new RuntimeException("A service Combo must contain at least 2 services");
		}
		
		
		int indexOfMain = services.indexOf(mainService);
		boolean mandatoryOfMain = mandatory.get(indexOfMain);
		if(!mandatoryOfMain) {
			throw new RuntimeException("Main service must be mandatory");
		}
		
		if(!services.contains(mainService)) {
			throw new RuntimeException("Main service must be included in the services");
		}
		
		for(BookableService bookableService: carShop.getBookableServices()) {
			if(!services.contains(bookableService)) {
				throw new RuntimeException("An entered service does not exist");
			}
		}
		
		
		try {

			ServiceCombo serviceCombo = new ServiceCombo(name, carShop);
			carShop.addBookableService(serviceCombo);

		}
		catch(RuntimeException ex) {
			throw new InvalidInputException(ex.getMessage());
		}

	}
	
	public static void updateService(String oldName, String newName, int newDuration, Garage newGarage) throws InvalidInputException {
		if(loggedInUser == null  || loggedInUser.getUsername() != "owner" || !(loggedInUser instanceof Owner)) {
			throw new RuntimeException("You are not authorized to perform this operation");
		}
		
		CarShop carShop = CarShopApplication.getCarShop();
		
		
		try {
			Service serviceToModify = getServiceFromName(oldName, carShop);
			
			if(!oldName.equals(newName) && getServiceFromName(newName, carShop) != null) {
				throw new InvalidInputException("Service "+newName+ " already exists");
			}
			if(newDuration <= 0) {
				throw new InvalidInputException("Duration must be positive");
			}
			
			if(serviceToModify != null) {
				serviceToModify.setName(newName);
				serviceToModify.setDuration(newDuration);
				serviceToModify.setGarage(newGarage);
			}
		}
		catch(RuntimeException ex) {
			throw new InvalidInputException(ex.getMessage());
			
		}
	}
	
	
	
	public static User getLoggedInUser() {
		return loggedInUser;
	}
	
	private static ServiceCombo getServiceComboFromName(String name, CarShop carShop) {
		List<BookableService> bookableServices = carShop.getBookableServices();
		for(BookableService bookableService: bookableServices) {
			if(bookableService instanceof ServiceCombo) {
				ServiceCombo serviceCombo = (ServiceCombo) bookableService;
				if(serviceCombo.getName().equals(name)) {
					return serviceCombo;
				}
			}
		}

		return null;
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
	
	
	// Author: Hyunbum Cho -----------------------------------------------------
	public static void setBusinessInfo (String aName, String aAddress, String aPhoneNumber, String aEmail) throws InvalidInputException, InvalidUserException {
	    if (CarShopController.getLoggedInUser() != CarShopApplication.getCarShop().getOwner()) {
	      throw new InvalidUserException("No permission to set up business information");
	    } else if (!isValidEmailAddress(aEmail)) {
	      throw new InvalidInputException("Invalid email");
	    } else if (aPhoneNumber.length()!=13) {
	      throw new InvalidInputException("Invalid phone number");
	    }
	    Business business = new Business(aName, aAddress, aPhoneNumber, aEmail, CarShopApplication.getCarShop());
	    CarShopApplication.getCarShop().setBusiness(business);
	  }
	  
	  public static void updateBusinessInfo (String aName, String aAddress, String aPhoneNumber, String aEmail) throws InvalidInputException, InvalidUserException {
	    if (CarShopController.getLoggedInUser() != CarShopApplication.getCarShop().getOwner()) {
	      throw new InvalidUserException("No permission to update business information");
	    } else if (!isValidEmailAddress(aEmail)) {
	      throw new InvalidInputException("Invalid email");
	    } else if (aPhoneNumber.length()!=13) {
	      throw new InvalidInputException("Invalid phone number");
	    }
	    
	    Business business = CarShopApplication.getCarShop().getBusiness();
	    business.setName(aName);
	    business.setAddress(aAddress);
	    business.setPhoneNumber(aPhoneNumber);
	    business.setEmail(aEmail);
	    }
	  
	  
	  public static void addBusinessHour (DayOfWeek day, Time newStartTime, Time newEndTime) throws InvalidInputException, InvalidUserException {
	    Business business = CarShopApplication.getCarShop().getBusiness();
	    List<BusinessHour> bHour = business.getBusinessHours();
	    BusinessHour newBHour;
	    if (CarShopController.getLoggedInUser() != CarShopApplication.getCarShop().getOwner()) {
	      throw new InvalidUserException("No permission to update business information");
	    } else if (newStartTime.after(newEndTime)) {
	      throw new InvalidInputException("Start time must be before end time");
	    }
	    for (BusinessHour b: bHour) {
	      if (b.getDayOfWeek().equals(day)) {
	        if (newStartTime.before(b.getEndTime()) && newEndTime.after(b.getStartTime())) {
	          throw new InvalidInputException("The business hours cannot overlap");
	        }
	      }
	    }
	    
	    newBHour = new BusinessHour(day, newStartTime, newEndTime, business.getCarShop());
	    business.addBusinessHour(newBHour);
	  }
	  
	  
	  public static void updateBusinessHour (DayOfWeek oldDay, Time oldStartTime, DayOfWeek day, Time newStartTime, Time newEndTime) throws InvalidInputException, InvalidUserException {
	    Business business = CarShopApplication.getCarShop().getBusiness();
        List<BusinessHour> bHours = business.getBusinessHours();
        BusinessHour bHour = null;
	    if (CarShopController.getLoggedInUser() != CarShopApplication.getCarShop().getOwner()) {
	      throw new InvalidUserException("No permission to update business information");
	    } else if (newStartTime.after(newEndTime)) {
	      throw new InvalidInputException("Start time must be before end time");
	    }
	    for (BusinessHour b: bHours) {
	      if (b.getDayOfWeek().equals(day)) {
	        if (newStartTime.before(b.getEndTime()) && newEndTime.after(b.getStartTime())) {
	          if (!oldDay.equals(b.getDayOfWeek()) && !oldStartTime.equals(b.getStartTime())) {
	            throw new InvalidInputException("The business hours cannot overlap");
	          }
	        }
	      }
	    }
	    
	    for (BusinessHour b: bHours) {
	      if (oldDay.equals(b.getDayOfWeek()) && oldStartTime.equals(b.getStartTime())) {
	        bHour = b;
	      }
	    }
	    if (bHour != null) {
	      bHour.setStartTime(newStartTime);
	      bHour.setEndTime(newEndTime);
	    }
	  }
	  
	  
	  public static void removeBusinessHour (DayOfWeek day, Time startTime) throws InvalidInputException, InvalidUserException {
	    Business business = CarShopApplication.getCarShop().getBusiness();
        List<BusinessHour> bHours = business.getBusinessHours();
        BusinessHour bHour = null;
	    if (CarShopController.getLoggedInUser() != CarShopApplication.getCarShop().getOwner()) {
	      throw new InvalidUserException("No permission to update business information");
	    }
	    
	    for (BusinessHour b: bHours) {
	      if (b.getDayOfWeek().equals(day) && b.getStartTime().equals(startTime)) {
	        bHour = b;
	      }
	    }
	    if (bHour != null) business.removeBusinessHour(bHour);
	  }
	  
	  public static List<String> getBusinessInfo(){
	    Business business = CarShopApplication.getCarShop().getBusiness();
	    ArrayList<String> businessInfo = new ArrayList<String>(4);
	    businessInfo.add(business.getName());
	    businessInfo.add(business.getAddress());
	    businessInfo.add(business.getPhoneNumber());
	    businessInfo.add(business.getEmail());
	    return businessInfo;
	  }
	  
	  public static void addNewTimeSlot(String type, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException, InvalidUserException {
	    Business business = CarShopApplication.getCarShop().getBusiness();
	    List<TimeSlot> holidays;
	    List<TimeSlot> vacations;
	    
	    if (CarShopController.getLoggedInUser() != CarShopApplication.getCarShop().getOwner()) {
	      throw new InvalidUserException("No permission to update business information");
	    }
	    if (endDate.before(startDate)) {
	      throw new InvalidInputException("Start time must be before end time");
	    } else if ((startDate.equals(endDate)) && (endTime.before(startTime))) { // same day
	      throw new InvalidInputException("Start time must be before end time");
	    } else if (!type.contains("holiday") && !type.contains("vacation")) {
	      throw new InvalidInputException("Invalild type");
	    } else if (startDate.before(today)) {
	      if (type.contains("holiday")) {
	        throw new InvalidInputException("Holiday cannot start in the past");
	      } else {
	        throw new InvalidInputException("Vacation cannot start in the past ");
	      }
	    }
	    holidays = business.getHolidays();
	    vacations = business.getVacations();
	    
	    for (TimeSlot h: holidays) {
	      if (startDate.before(h.getEndDate())) { // new start date is before end date of holiday
	        if (endDate.after(h.getStartDate())) { // new end date is after start date of holiday
	          // overlap with holiday
	          if (type.contains("holiday")) {
	            throw new InvalidInputException("Holiday times cannot overlap");
	          } else { // vacation
	            throw new InvalidInputException("Holiday and vacation times cannot overlap");
	          }
	        } else if (endDate.equals(h.getStartDate())) { // ends at start date of holiday
	          if (endTime.after(h.getStartTime())) { // end time after start time of holiday
	            if (type.contains("holiday")) {
	              throw new InvalidInputException("Holiday times cannot overlap");
	            } else { // vacation
	              throw new InvalidInputException("Holiday and vacation times cannot overlap");
	            }
	          }
	        }
	      } 
	    }
	    
	    for (TimeSlot v: vacations) {
	      if (startDate.before(v.getEndDate())) { // new start date is before end date of vacation
	        if (endDate.after(v.getStartDate())) { // new end date is after start date of vacation
	          // overlap with vacation
	          if (type.contains("vacation")) {
	            throw new InvalidInputException("Vacation times cannot overlap");
	          } else { // holiday
	            throw new InvalidInputException("Holiday and vacation times cannot overlap");
	          }
	        } else if (endDate.equals(v.getStartDate())) { // ends at start date of vacation
	          if (endTime.after(v.getStartTime())) { // end time after start time of vacation
	            if (type.contains("vacation")) {
	              throw new InvalidInputException("Vacation times cannot overlap");
	            } else { // holiday
	              throw new InvalidInputException("Holiday and vacation times cannot overlap");
	            }
	          }
	        }
	      } 
	    }
	    
	    // no overlaps
	    TimeSlot ts = new TimeSlot(startDate, startTime, endDate, endTime, CarShopApplication.getCarShop());
	    if (type.contains("holiday")) {
	      business.addHoliday(ts);
	    } else {
	      business.addVacation(ts);
	    }
	  }
	  
	  
	  public static void updateVacation (Date oldDate, Time oldStartTime, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException, InvalidUserException {
	    Business business = CarShopApplication.getCarShop().getBusiness();
	    List<TimeSlot> holidays;
	    List<TimeSlot> vacations;
	    TimeSlot vacation = null;
	    
	    if (CarShopController.getLoggedInUser() != CarShopApplication.getCarShop().getOwner()) {
	      throw new InvalidUserException("No permission to update business information");
	    }
	    if (endDate.before(startDate)) {
	      throw new InvalidInputException("Start time must be before end time");
	    } else if ((startDate.equals(endDate)) && (endTime.before(startTime))) { // same day
	      throw new InvalidInputException("Start time must be before end time");
	    } else if (startDate.before(today)) {
	      throw new InvalidInputException("Vacation cannot start in the past");
	    }

	    holidays = business.getHolidays();
	    vacations = business.getVacations();
	    
	    for (TimeSlot h: holidays) {
	      if (startDate.before(h.getEndDate())) { // new start date is before end date of holiday
	        if (endDate.after(h.getStartDate())) { // new end date is after start date of holiday
	          // overlap with holiday
	          throw new InvalidInputException("Holiday and vacation times cannot overlap");
	        } else if (endDate.equals(h.getStartDate())) { // ends at start date of holiday
	          if (endTime.after(h.getStartTime())) { // end time after start time of holiday
	            throw new InvalidInputException("Holiday and vacation times cannot overlap");
	          }
	        }
	      } 
	    }
	    
	    for (TimeSlot v: vacations) {
	      if (oldDate.equals(v.getStartDate()) && oldStartTime.equals(v.getStartTime())) {
	        vacation = v;
	        continue;
	      }
	      if (startDate.before(v.getEndDate())) { // new start date is before end date of v
	        if (endDate.after(v.getStartDate())) { // new end date is after start date of v
	          throw new InvalidInputException("Vacation times cannot overlap");
	        } else if (endDate.equals(v.getStartDate())) { // ends at start date of v
	          if (endTime.after(v.getStartTime())) { // end time after start time of v
	            throw new InvalidInputException("Vacation times cannot overlap");
	          }
	        }
	      } 
	    }
	    
	    // no overlaps
	    vacation.setStartDate(startDate);
	    vacation.setStartTime(startTime);
	    vacation.setEndDate(endDate);
	    vacation.setEndTime(endTime);
	  }
	  
	  
	  // needs change if TimeSlot is provided instead of oldDate, oldStartTime
	  public static void updateHoliday (Date oldDate, Time oldStartTime, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException, InvalidUserException {
	    Business business = CarShopApplication.getCarShop().getBusiness();
	    List<TimeSlot> holidays;
	    List<TimeSlot> vacations;
	    TimeSlot holiday = null;
	    
	    if (CarShopController.getLoggedInUser() != CarShopApplication.getCarShop().getOwner()) {
	      throw new InvalidUserException("No permission to update business information");
	    }
	    if (endDate.before(startDate)) {
	      throw new InvalidInputException("Start time must be before end time");
	    } else if ((startDate.equals(endDate)) && (endTime.before(startTime))) { // same day
	      throw new InvalidInputException("Start time must be before end time");
	    } else if (startDate.before(today)) {
	      throw new InvalidInputException("Holiday cannot start in the past");
	    }

	    holidays = business.getHolidays();
	    vacations = business.getVacations();
	    
	    for (TimeSlot v: vacations) {
	      if (startDate.before(v.getEndDate())) { // new start date is before end date of holiday
	        if (endDate.after(v.getStartDate())) { // new end date is after start date of holiday
	          // overlap with holiday
	          throw new InvalidInputException("Holiday and vacation times cannot overlap");
	        } else if (endDate.equals(v.getStartDate())) { // ends at start date of holiday
	          if (endTime.after(v.getStartTime())) { // end time after start time of holiday
	            throw new InvalidInputException("Holiday and vacation times cannot overlap");
	          }
	        }
	      } 
	    }
	    
	    for (TimeSlot h: holidays) {
	      if (oldDate.equals(h.getStartDate()) && oldStartTime.equals(h.getStartTime())) {
	        holiday = h;
	        continue;
	      }
	      if (startDate.before(h.getEndDate())) { // new start date is before end date of v
	        if (endDate.after(h.getStartDate())) { // new end date is after start date of v
	          throw new InvalidInputException("Holiday times cannot overlap");
	        } else if (endDate.equals(h.getStartDate())) { // ends at start date of v
	          if (endTime.after(h.getStartTime())) { // end time after start time of v
	            throw new InvalidInputException("Holiday times cannot overlap");
	          }
	        }
	      } 
	    }
	    
	    // no overlaps
	    holiday.setStartDate(startDate);
	    holiday.setStartTime(startTime);
	    holiday.setEndDate(endDate);
	    holiday.setEndTime(endTime);
	  }
	  
	  
	  public static void removeTimeSlot (String type, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException, InvalidUserException {
	    Business business = CarShopApplication.getCarShop().getBusiness();
	    List<TimeSlot> holidays;
	    List<TimeSlot> vacations;
	    TimeSlot holiday = null;
	    TimeSlot vacation = null;
	    if (CarShopController.getLoggedInUser() != CarShopApplication.getCarShop().getOwner()) {
	      throw new InvalidUserException("No permission to update business information");
	    } else if (!type.contains("holiday") && !type.contains("vacation")) {
	      throw new InvalidInputException("Invalild type");
	    }
	    
	    if (type.contains("holiday")) {
	      holidays = business.getHolidays();
	      for (TimeSlot h: holidays) {
	        if (h.getStartDate().equals(startDate) && h.getStartTime().equals(startTime) && h.getEndDate().equals(endDate) && h.getEndTime().equals(endTime)) {
	          holiday = h;
	          break;
	        }
	      }
	      
	      if (holiday == null) {
	        throw new InvalidInputException("No such holiday exists");
	      } else {
	        business.removeHoliday(holiday);
	      }
	    } else { // vacation
	      vacations = business.getVacations();
	      for (TimeSlot v: vacations) {
	        if (v.getStartDate().equals(startDate) && v.getStartTime().equals(startTime) && v.getEndDate().equals(endDate) && v.getEndTime().equals(endTime)) {
	          vacation =v;
	          break;
	        }
	      }
	      
	      if (vacation == null) {
	        throw new InvalidInputException("No such vacation exists");
	      } else {
	        business.removeVacation(vacation);
	      }
	    }
	  }
	  
	  public static void setToday (Date d) {
	    today = d;
	  }
	  
	  public static void setTime (Time t) {
	    now = t;
	  }
	  
	  private static boolean isValidEmailAddress(String email) {
	    boolean valid = true;
	    if(!email.contains("@")) {
	      valid = false; 
	    } else if (!email.contains(".")) {
	      valid = false;
	    } else if (email.charAt(0)=='@') { // starts with @
	      valid = false;
	    } else if (email.charAt(email.length()-1) == '.') { // ends with .
	      valid = false;
	    } else if (email.charAt(email.length() - 1) == '@' || email.charAt(email.length() - 2) == '@') { // @ is last or second last character
	      valid = false;
	    } else if(!email.substring(email.indexOf('@') + 2, email.length() - 1).contains(".")) { 
	      // . has to be at least two characters after @ (need at least one character between them)
	      valid = false;
	    }
	    
	    
	    return valid;
	  }
	  
	  // End of Hyunbum's code -------------------------------------------------------------------------
	  
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
	  private static Customer getCustomerWithUsername(String username) {
		  CarShop carShop = CarShopApplication.getCarShop();
		  for(Customer c: carShop.getCustomers()) {
			  if(c.getUsername().equals(username)) {
				  return c;
			  }
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
	  
	  private static Technician getTechnicianWithTechType(TechnicianType techType) {
		  CarShop carShop = CarShopApplication.getCarShop();
		  for(Technician tech: carShop.getTechnicians()) {
			  if(tech.getType() == techType) {
				  return tech;
			  }
		  }
		  
		  return null;
	  }

	  private static BusinessHour getBusinessHoursOfShopByDay(DayOfWeek day) {
			CarShop carShop = CarShopApplication.getCarShop();
			for(BusinessHour bh: carShop.getBusiness().getBusinessHours()) {
				if(bh.getDayOfWeek() == day) {
					return bh;
				}
			}
			
			return null;
		}
	  public static void updateCombo(String name, String updatedName, Service updatedMainService, List<Service> updatedServices, List<Boolean> updatedMandatory) throws RuntimeException, InvalidInputException {
			CarShop carShop=CarShopApplication.getCarShop();
			if(loggedInUser == null  || loggedInUser.getUsername() != "owner"|| !(loggedInUser instanceof Owner)) {
				throw new RuntimeException("You are not authorized to perform this operation");
			}
			
			if(!updatedServices.contains(updatedMainService)) {
				throw new RuntimeException("Main service must be included in the services");
			}
			
			int indexOfMain = updatedServices.indexOf(updatedMainService);
			boolean mandatoryOfMain = updatedMandatory.get(indexOfMain);
			if(!mandatoryOfMain) {
				throw new RuntimeException("Main service must be mandatory");
			}
			
			if(updatedServices.size()<ServiceCombo.minimumNumberOfServices()) {
				throw new RuntimeException("A service Combo must contain at least 2 services");
			}
			
			for(BookableService bookableService: carShop.getBookableServices()) {
			if(!updatedServices.contains(bookableService)) {
				throw new RuntimeException("An entered service does not exist");
			}
			
			try {
				ServiceCombo oldCombo = getServiceComboFromName(name, carShop);
				if(!name.equals(updatedName) && getServiceComboFromName(updatedName, carShop) != null) {
					throw new InvalidInputException("Service "+ updatedName + " already exists");
				}
				if(oldCombo != null) {
					oldCombo.setName(updatedName);
					ComboItem mainService = new ComboItem(true, updatedMainService, oldCombo);
					oldCombo.setMainService(mainService); //Don't think this is correct at all
				}
			}
			catch(RuntimeException e) {
				if(e.getMessage().startsWith("Cannot create due to duplicate")) {
					throw new InvalidInputException("The service combo already exists");
				}
				throw new InvalidInputException(e.getMessage());
			
			
		}
	}
	  }
}
