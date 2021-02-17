/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/
package ca.mcgill.ecse223.carshop.model;

import java.util.*;
import java.sql.Date;

// line 32 "carshop.ump"
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
  private List<WeeklySchedule> weeklySchedule;
  private CarShop carShop;
  private OwnerAccount ownerAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CarShopInfo(String aPhoneNumber, String aEmailAddress, Address aAddress, CarShop aCarShop, OwnerAccount aOwnerAccount)
  {
    phoneNumber = aPhoneNumber;
    emailAddress = aEmailAddress;
    if (aAddress == null || aAddress.getCarShopInfo() != null)
    {
      throw new RuntimeException("Unable to create CarShopInfo due to aAddress. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    address = aAddress;
    weeklySchedule = new ArrayList<WeeklySchedule>();
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
    weeklySchedule = new ArrayList<WeeklySchedule>();
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
  public WeeklySchedule getWeeklySchedule(int index)
  {
    WeeklySchedule aWeeklySchedule = weeklySchedule.get(index);
    return aWeeklySchedule;
  }

  public List<WeeklySchedule> getWeeklySchedule()
  {
    List<WeeklySchedule> newWeeklySchedule = Collections.unmodifiableList(weeklySchedule);
    return newWeeklySchedule;
  }

  public int numberOfWeeklySchedule()
  {
    int number = weeklySchedule.size();
    return number;
  }

  public boolean hasWeeklySchedule()
  {
    boolean has = weeklySchedule.size() > 0;
    return has;
  }

  public int indexOfWeeklySchedule(WeeklySchedule aWeeklySchedule)
  {
    int index = weeklySchedule.indexOf(aWeeklySchedule);
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
  public static int minimumNumberOfWeeklySchedule()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public WeeklySchedule addWeeklySchedule(Date aWeekStart, Date aWeekEnd, CarShop aCarShop)
  {
    return new WeeklySchedule(aWeekStart, aWeekEnd, aCarShop, this);
  }

  public boolean addWeeklySchedule(WeeklySchedule aWeeklySchedule)
  {
    boolean wasAdded = false;
    if (weeklySchedule.contains(aWeeklySchedule)) { return false; }
    CarShopInfo existingCarShopInfo = aWeeklySchedule.getCarShopInfo();
    boolean isNewCarShopInfo = existingCarShopInfo != null && !this.equals(existingCarShopInfo);
    if (isNewCarShopInfo)
    {
      aWeeklySchedule.setCarShopInfo(this);
    }
    else
    {
      weeklySchedule.add(aWeeklySchedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWeeklySchedule(WeeklySchedule aWeeklySchedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aWeeklySchedule, as it must always have a carShopInfo
    if (!this.equals(aWeeklySchedule.getCarShopInfo()))
    {
      weeklySchedule.remove(aWeeklySchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWeeklyScheduleAt(WeeklySchedule aWeeklySchedule, int index)
  {  
    boolean wasAdded = false;
    if(addWeeklySchedule(aWeeklySchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeklySchedule()) { index = numberOfWeeklySchedule() - 1; }
      weeklySchedule.remove(aWeeklySchedule);
      weeklySchedule.add(index, aWeeklySchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWeeklyScheduleAt(WeeklySchedule aWeeklySchedule, int index)
  {
    boolean wasAdded = false;
    if(weeklySchedule.contains(aWeeklySchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeklySchedule()) { index = numberOfWeeklySchedule() - 1; }
      weeklySchedule.remove(aWeeklySchedule);
      weeklySchedule.add(index, aWeeklySchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWeeklyScheduleAt(aWeeklySchedule, index);
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
    for(int i=weeklySchedule.size(); i > 0; i--)
    {
      WeeklySchedule aWeeklySchedule = weeklySchedule.get(i - 1);
      aWeeklySchedule.delete();
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