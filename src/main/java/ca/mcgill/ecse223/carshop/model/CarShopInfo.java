/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.util.*;
import java.sql.Date;

// line 45 "../../../../../carshop.ump"
public class CarShopInfo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CarShopInfo Attributes
  private String phoneNumber;
  private String emailAddress;

  //CarShopInfo Associations
  private Address address;
  private List<WeeklySchedule> weeks_schedule;
  private CarShop carShop;
  private OwnerAccount ownerAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CarShopInfo(String aPhoneNumber, String aEmailAddress, Address aAddress, CarShop aCarShop, OwnerAccount aOwnerAccount)
  {
    phoneNumber = aPhoneNumber;
    emailAddress = aEmailAddress;
    if (aAddress == null || aAddress.getCar_shop() != null)
    {
      throw new RuntimeException("Unable to create CarShopInfo due to aAddress. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    address = aAddress;
    weeks_schedule = new ArrayList<WeeklySchedule>();
    if (aCarShop == null || aCarShop.getCarShopInfo() != null)
    {
      throw new RuntimeException("Unable to create CarShopInfo due to aCarShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    carShop = aCarShop;
    if (aOwnerAccount == null || aOwnerAccount.getCarShopInfo() != null)
    {
      throw new RuntimeException("Unable to create CarShopInfo due to aOwnerAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    ownerAccount = aOwnerAccount;
  }

  public CarShopInfo(String aPhoneNumber, String aEmailAddress, String aStreetnameForAddress, int aStreetNumberForAddress, String aPostalCodeForAddress, String aCityForAddress, CarShop aCarShopForAddress, Address aAddressForCarShop, String aUsernameForOwnerAccount, String aPasswordForOwnerAccount, CarShop aCarShopForOwnerAccount)
  {
    phoneNumber = aPhoneNumber;
    emailAddress = aEmailAddress;
    address = new Address(aStreetnameForAddress, aStreetNumberForAddress, aPostalCodeForAddress, aCityForAddress, aCarShopForAddress, this);
    weeks_schedule = new ArrayList<WeeklySchedule>();
    carShop = new CarShop(aAddressForCarShop, this);
    ownerAccount = new OwnerAccount(aUsernameForOwnerAccount, aPasswordForOwnerAccount, aCarShopForOwnerAccount, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmailAddress(String aEmailAddress)
  {
    boolean wasSet = false;
    emailAddress = aEmailAddress;
    wasSet = true;
    return wasSet;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }
  /* Code from template association_GetOne */
  public Address getAddress()
  {
    return address;
  }
  /* Code from template association_GetMany */
  public WeeklySchedule getWeeks_schedule(int index)
  {
    WeeklySchedule aWeeks_schedule = weeks_schedule.get(index);
    return aWeeks_schedule;
  }

  public List<WeeklySchedule> getWeeks_schedule()
  {
    List<WeeklySchedule> newWeeks_schedule = Collections.unmodifiableList(weeks_schedule);
    return newWeeks_schedule;
  }

  public int numberOfWeeks_schedule()
  {
    int number = weeks_schedule.size();
    return number;
  }

  public boolean hasWeeks_schedule()
  {
    boolean has = weeks_schedule.size() > 0;
    return has;
  }

  public int indexOfWeeks_schedule(WeeklySchedule aWeeks_schedule)
  {
    int index = weeks_schedule.indexOf(aWeeks_schedule);
    return index;
  }
  /* Code from template association_GetOne */
  public CarShop getCarShop()
  {
    return carShop;
  }
  /* Code from template association_GetOne */
  public OwnerAccount getOwnerAccount()
  {
    return ownerAccount;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWeeks_schedule()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public WeeklySchedule addWeeks_schedule(Date aWeekStart, Date aWeekEnd, CarShop aCarShop)
  {
    return new WeeklySchedule(aWeekStart, aWeekEnd, aCarShop, this);
  }

  public boolean addWeeks_schedule(WeeklySchedule aWeeks_schedule)
  {
    boolean wasAdded = false;
    if (weeks_schedule.contains(aWeeks_schedule)) { return false; }
    CarShopInfo existingCar_shop = aWeeks_schedule.getCar_shop();
    boolean isNewCar_shop = existingCar_shop != null && !this.equals(existingCar_shop);
    if (isNewCar_shop)
    {
      aWeeks_schedule.setCar_shop(this);
    }
    else
    {
      weeks_schedule.add(aWeeks_schedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWeeks_schedule(WeeklySchedule aWeeks_schedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aWeeks_schedule, as it must always have a car_shop
    if (!this.equals(aWeeks_schedule.getCar_shop()))
    {
      weeks_schedule.remove(aWeeks_schedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWeeks_scheduleAt(WeeklySchedule aWeeks_schedule, int index)
  {  
    boolean wasAdded = false;
    if(addWeeks_schedule(aWeeks_schedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeks_schedule()) { index = numberOfWeeks_schedule() - 1; }
      weeks_schedule.remove(aWeeks_schedule);
      weeks_schedule.add(index, aWeeks_schedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWeeks_scheduleAt(WeeklySchedule aWeeks_schedule, int index)
  {
    boolean wasAdded = false;
    if(weeks_schedule.contains(aWeeks_schedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeks_schedule()) { index = numberOfWeeks_schedule() - 1; }
      weeks_schedule.remove(aWeeks_schedule);
      weeks_schedule.add(index, aWeeks_schedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWeeks_scheduleAt(aWeeks_schedule, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Address existingAddress = address;
    address = null;
    if (existingAddress != null)
    {
      existingAddress.delete();
    }
    for(int i=weeks_schedule.size(); i > 0; i--)
    {
      WeeklySchedule aWeeks_schedule = weeks_schedule.get(i - 1);
      aWeeks_schedule.delete();
    }
    CarShop existingCarShop = carShop;
    carShop = null;
    if (existingCarShop != null)
    {
      existingCarShop.delete();
    }
    OwnerAccount existingOwnerAccount = ownerAccount;
    ownerAccount = null;
    if (existingOwnerAccount != null)
    {
      existingOwnerAccount.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "emailAddress" + ":" + getEmailAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "address = "+(getAddress()!=null?Integer.toHexString(System.identityHashCode(getAddress())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "carShop = "+(getCarShop()!=null?Integer.toHexString(System.identityHashCode(getCarShop())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ownerAccount = "+(getOwnerAccount()!=null?Integer.toHexString(System.identityHashCode(getOwnerAccount())):"null");
  }
}