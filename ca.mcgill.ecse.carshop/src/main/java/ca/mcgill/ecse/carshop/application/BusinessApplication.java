
package ca.mcgill.ecse.carshop.application;

import java.util.List;
import ca.mcgill.ecse.carshop.model.Business;
import ca.mcgill.ecse.carshop.model.BusinessHour;
import ca.mcgill.ecse.carshop.model.TimeSlot;

public class BusinessApplication {
  
  private static Business business;
  
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new CarShopApplication().getGreeting());
    }
    
    public static Business getBusiness() {
      return business;
    }
    
    public static void setBusiness(Business b) {
      business = b;
    }
    
    public static List<BusinessHour> getBusinessHour() {
      return business.getBusinessHours();
    }
    
    public static List<TimeSlot> getHolidays(){
      return business.getHolidays();
    }
    
    public static List<TimeSlot> getVacations(){
      return business.getVacations();
    }
}
