package ca.mcgill.ecse.carshop.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.model.BookableService;
import ca.mcgill.ecse.carshop.model.Business;
import ca.mcgill.ecse.carshop.model.BusinessHour;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.Garage;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Service;
import ca.mcgill.ecse.carshop.model.TimeSlot;
import ca.mcgill.ecse.carshop.model.User;
import ca.mcgill.ecse.carshop.model.BusinessHour.DayOfWeek;

public class CarShopController {
	
	private static User loggedInUser;
	private static Date today = Date.valueOf("2021-02-01");
	
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
	
	public static void setBusinessInfo (String aName, String aAddress, String aPhoneNumber, String aEmail) throws InvalidInputException {
	    if (CarShopController.getLoggedInUser().getUsername() != "owner") {
	      throw new java.lang.Error("No permission to set up business information");
	    } else if (!isValidEmailAddress(aEmail)) {
	      throw new InvalidInputException("Invalid email");
	    } else if (aPhoneNumber.length()!=13) {
	      throw new InvalidInputException("Invalid phone number");
	    }
	    Business business = new Business(aName, aAddress, aPhoneNumber, aEmail, CarShopApplication.getCarShop());
	    CarShopApplication.setBusiness(business);
	  }
	  
	  public static void updateBusinessInfo (String aName, String aAddress, String aPhoneNumber, String aEmail) throws InvalidInputException {
	    if (CarShopController.getLoggedInUser().getUsername() != "owner") {
	      throw new java.lang.Error("No permission to set up business information");
	    } else if (!isValidEmailAddress(aEmail)) {
	      throw new InvalidInputException("Invalid email");
	    } else if (aPhoneNumber.length()!=13) {
	      throw new InvalidInputException("Invalid phone number");
	    }
	    
	    Business business = CarShopApplication.getBusiness();
	    business.setName(aName);
	    business.setAddress(aAddress);
	    business.setPhoneNumber(aPhoneNumber);
	    }
	  
	  //need to change if there is one bhour per day
	  public static void addBusinessHour (DayOfWeek day, Time newStartTime, Time newEndTime) throws InvalidInputException {
	    List<BusinessHour> bHour = CarShopApplication.getBusinessHour();
	    Business business = CarShopApplication.getBusiness();
	    BusinessHour newBHour;
	    if (CarShopController.getLoggedInUser().getUsername() != "owner") {
	      throw new java.lang.Error("No permission to set up business information");
	    } else if (newStartTime.after(newEndTime)) {
	      throw new InvalidInputException("Start time must be before end time");
	    }
	    for (BusinessHour b: bHour) {
	      if (b.getDayOfWeek() == day) {
	        if (newStartTime.before(b.getEndTime()) && newEndTime.after(b.getStartTime())) {
	          throw new InvalidInputException("The business hours cannot overlap");
	        }
	      }
	    }
	    
	    newBHour = new BusinessHour(day, newStartTime, newEndTime, business.getCarShop());
	    business.addBusinessHour(newBHour);
	  }
	  
	  // need to change if there is one bhour per day
	  public static void updateBusinessHour (DayOfWeek oldDay, Time oldStartTime, DayOfWeek day, Time newStartTime, Time newEndTime) throws InvalidInputException {
	    List<BusinessHour> bHour = CarShopApplication.getBusinessHour();
	    if (CarShopController.getLoggedInUser().getUsername() != "owner") {
	      throw new java.lang.Error("No permission to set up business information");
	    } else if (newStartTime.after(newEndTime)) {
	      throw new InvalidInputException("Start time must be before end time");
	    }
	    for (BusinessHour b: bHour) {
	      if (b.getDayOfWeek() == day) {
	        if (newStartTime.before(b.getEndTime()) && newEndTime.after(b.getStartTime())) {
	          throw new InvalidInputException("The business hours cannot overlap");
	        }
	      }
	    }
	    
	    for (BusinessHour b: bHour) {
	      if (b.getDayOfWeek() == oldDay && b.getStartTime() == oldStartTime) {
	        b.setStartTime(newStartTime);
	        b.setEndTime(newEndTime);
	      }
	    }
	    
	  }
	  
	  //need to change if there is one bhour per day
	  public static void removeBusinessHour (DayOfWeek day, Time startTime) throws InvalidInputException {
	    List<BusinessHour> bHour = CarShopApplication.getBusinessHour();
	    if (CarShopController.getLoggedInUser().getUsername() != "owner") {
	      throw new java.lang.Error("No permission to set up business information");
	    }
	    
	    for (BusinessHour b: bHour) {
	      if (b.getDayOfWeek() == day && b.getStartTime() == startTime) {
	        CarShopApplication.getBusiness().removeBusinessHour(b);
	      }
	    }
	    
	  }
	  
	  public static TOBusinessInfo getBusinessInfo(){
	    Business business = CarShopApplication.getBusiness();
	    TOBusinessInfo TOBusiness = new TOBusinessInfo(business.getName(), business.getAddress(), business.getPhoneNumber(), business.getEmail());
	    
	    return TOBusiness;
	  }
	  
	  public static void addNewTimeSlot(String type, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException {
	    List<TimeSlot> holidays;
	    List<TimeSlot> vacations;
	    
	    if (CarShopController.getLoggedInUser().getUsername() != "owner") {
	      throw new java.lang.Error("No permission to set up business information");
	    }
	    if (endDate.before(startDate)) {
	      throw new InvalidInputException("Start time must be before end time");
	    } else if ((startDate == endDate) && (endTime.before(startTime))) { // same day
	      throw new InvalidInputException("Start time must be before end time");
	    } else if (type != "holiday" && type != "vacation") {
	      throw new InvalidInputException("Invalild type");
	    } else if (startDate.before(today)) {
	      if (type == "holiday") {
	        throw new InvalidInputException("Holiday cannot start in the past");
	      } else {
	        throw new InvalidInputException("Vacation cannot start in the past ");
	      }
	    }
	    holidays = CarShopApplication.getHolidays();
	    vacations = CarShopApplication.getVacations();
	    
	    for (TimeSlot h: holidays) {
	      if (startDate.before(h.getEndDate())) { // new start date is before end date of holiday
	        if (endDate.after(h.getStartDate())) { // new end date is after start date of holiday
	          // overlap with holiday
	          if (type == "holiday") {
	            throw new InvalidInputException("Holiday times cannot overlap");
	          } else { // vacation
	            throw new InvalidInputException("Holiday and vacation times cannot overlap");
	          }
	        } else if (endDate == h.getStartDate()) { // ends at start date of holiday
	          if (endTime.after(h.getStartTime())) { // end time after start time of holiday
	            if (type == "holiday") {
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
	          if (type == "vacation") {
	            throw new InvalidInputException("Vacation times cannot overlap");
	          } else { // holiday
	            throw new InvalidInputException("Holiday and vacation times cannot overlap");
	          }
	        } else if (endDate == v.getStartDate()) { // ends at start date of vacation
	          if (endTime.after(v.getStartTime())) { // end time after start time of vacation
	            if (type == "vacation") {
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
	    if (type == "holiday") {
	      CarShopApplication.getBusiness().addHoliday(ts);
	    } else {
	      CarShopApplication.getBusiness().addVacation(ts);
	    }
	  }
	  
	  // needs change if TimeSlot is provided instead of oldDate, oldStartTime
	  public static void updateVacation (Date oldDate, Time oldStartTime, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException {
	    List<TimeSlot> holidays;
	    List<TimeSlot> vacations;
	    TimeSlot vacation = null;
	    
	    if (CarShopController.getLoggedInUser().getUsername() != "owner") {
	      throw new java.lang.Error("No permission to set up business information");
	    }
	    if (endDate.before(startDate)) {
	      throw new InvalidInputException("Start time must be before end time");
	    } else if ((startDate == endDate) && (endTime.before(startTime))) { // same day
	      throw new InvalidInputException("Start time must be before end time");
	    } else if (startDate.before(today)) {
	      throw new InvalidInputException("Vacation cannot start in the past");
	    }

	    holidays = CarShopApplication.getHolidays();
	    vacations = CarShopApplication.getVacations();
	    
	    for (TimeSlot h: holidays) {
	      if (startDate.before(h.getEndDate())) { // new start date is before end date of holiday
	        if (endDate.after(h.getStartDate())) { // new end date is after start date of holiday
	          // overlap with holiday
	          throw new InvalidInputException("Holiday and vacation times cannot overlap");
	        } else if (endDate == h.getStartDate()) { // ends at start date of holiday
	          if (endTime.after(h.getStartTime())) { // end time after start time of holiday
	            throw new InvalidInputException("Holiday and vacation times cannot overlap");
	          }
	        }
	      } 
	    }
	    
	    for (TimeSlot v: vacations) {
	      if (oldDate == v.getStartDate() && oldStartTime == v.getStartTime()) {
	        vacation = v;
	        continue;
	      }
	      if (startDate.before(v.getEndDate())) { // new start date is before end date of v
	        if (endDate.after(v.getStartDate())) { // new end date is after start date of v
	          throw new InvalidInputException("Vacation times cannot overlap");
	        } else if (endDate == v.getStartDate()) { // ends at start date of v
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
	  public static void updateHoliday (Date oldDate, Time oldStartTime, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException {
	    List<TimeSlot> holidays;
	    List<TimeSlot> vacations;
	    TimeSlot holiday = null;
	    
	    if (CarShopController.getLoggedInUser().getUsername() != "owner") {
	      throw new java.lang.Error("No permission to set up business information");
	    }
	    if (endDate.before(startDate)) {
	      throw new InvalidInputException("Start time must be before end time");
	    } else if ((startDate == endDate) && (endTime.before(startTime))) { // same day
	      throw new InvalidInputException("Start time must be before end time");
	    } else if (startDate.before(today)) {
	      throw new InvalidInputException("Holiday cannot start in the past");
	    }

	    holidays = CarShopApplication.getHolidays();
	    vacations = CarShopApplication.getVacations();
	    
	    for (TimeSlot v: vacations) {
	      if (startDate.before(v.getEndDate())) { // new start date is before end date of holiday
	        if (endDate.after(v.getStartDate())) { // new end date is after start date of holiday
	          // overlap with holiday
	          throw new InvalidInputException("Holiday and vacation times cannot overlap");
	        } else if (endDate == v.getStartDate()) { // ends at start date of holiday
	          if (endTime.after(v.getStartTime())) { // end time after start time of holiday
	            throw new InvalidInputException("Holiday and vacation times cannot overlap");
	          }
	        }
	      } 
	    }
	    
	    for (TimeSlot h: holidays) {
	      if (oldDate == h.getStartDate() && oldStartTime == h.getStartTime()) {
	        holiday = h;
	        continue;
	      }
	      if (startDate.before(h.getEndDate())) { // new start date is before end date of v
	        if (endDate.after(h.getStartDate())) { // new end date is after start date of v
	          throw new InvalidInputException("Holiday times cannot overlap");
	        } else if (endDate == h.getStartDate()) { // ends at start date of v
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
	  
	  
	  public static void removeTimeSlot (String type, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException {
	    List<TimeSlot> holidays;
	    List<TimeSlot> vacations;
	    TimeSlot holiday = null;
	    TimeSlot vacation = null;
	    if (CarShopController.getLoggedInUser().getUsername() != "owner") {
	      throw new java.lang.Error("No permission to set up business information");
	    } else if (type != "holiday" && type != "vacation") {
	      throw new InvalidInputException("Invalild type");
	    }
	    
	    if (type == "holiday") {
	      holidays = CarShopApplication.getHolidays();
	      for (TimeSlot h: holidays) {
	        if (h.getStartDate() == startDate && h.getStartTime() == startTime && h.getEndDate() == endDate && h.getEndTime() == endTime) {
	          holiday = h;
	          break;
	        }
	      }
	      
	      if (holiday == null) {
	        throw new InvalidInputException("No such holiday exists");
	      } else {
	        CarShopApplication.getBusiness().removeHoliday(holiday);
	      }
	    } else { // vacation
	      vacations = CarShopApplication.getVacations();
	      for (TimeSlot v: vacations) {
	        if (v.getStartDate() == startDate && v.getStartTime() == startTime && v.getEndDate() == endDate && v.getEndTime() == endTime) {
	          vacation =v;
	          break;
	        }
	      }
	      
	      if (vacation == null) {
	        throw new InvalidInputException("No such vacation exists");
	      } else {
	        CarShopApplication.getBusiness().removeVacation(vacation);
	      }
	    }
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
}
