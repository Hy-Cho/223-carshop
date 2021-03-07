/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ca.mcgill.ecse.carshop.application;

import java.util.List;
import ca.mcgill.ecse.carshop.model.Business;
import ca.mcgill.ecse.carshop.model.BusinessHour;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.TimeSlot;

public class CarShopApplication {
	
	private static CarShop carShop;
	
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new CarShopApplication().getGreeting());
    }
    
    public static CarShop getCarShop() {
    	if(carShop == null) {
    		carShop = new CarShop();
    	}
    	
    	return carShop;
    }
    
}
