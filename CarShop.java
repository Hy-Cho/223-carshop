/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.util.*;
import java.sql.Date;

// line 1 "carshop.ump"
public class CarShop
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CarShop Associations
  private List<UserAccount> userAccount;
  private Address address;
  private CarShopInfo carShopInfo;
  private List<WeeklySchedule> weeklySchedule;
  private List<DaySchedule> daySchedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CarShop(Address aAddress, CarShopInfo aCarShopInfo)
  {
    userAccount = new ArrayList<UserAccount>();
    if (aAddress == null || aAddress.getCarShop() != null)
    {
      throw new RuntimeException("Unable to create CarShop due to aAddress. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    address = aAddress;
    if (aCarShopInfo == null || aCarShopInfo.getCarShop() != null)
    {
      throw new RuntimeException("Unable to create CarShop due to aCarShopInfo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    carShopInfo = aCarShopInfo;
    weeklySchedule = new ArrayList<WeeklySchedule>();
    daySchedule = new ArrayList<DaySchedule>();
  }

  public CarShop(String aStreetnameForAddress, int aStreetNumberForAddress, String aPostalCodeForAddress, String aCityForAddress, CarShopInfo aCarShopInfoForAddress, String aPhoneNumberForCarShopInfo, String aEmailAddressForCarShopInfo, Address aAddressForCarShopInfo, OwnerAccount aOwnerAccountForCarShopInfo)
  {
    userAccount = new ArrayList<UserAccount>();
    address = new Address(aStreetnameForAddress, aStreetNumberForAddress, aPostalCodeForAddress, aCityForAddress, this, aCarShopInfoForAddress);
    carShopInfo = new CarShopInfo(aPhoneNumberForCarShopInfo, aEmailAddressForCarShopInfo, aAddressForCarShopInfo, this, aOwnerAccountForCarShopInfo);
    weeklySchedule = new ArrayList<WeeklySchedule>();
    daySchedule = new ArrayList<DaySchedule>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public UserAccount getUserAccount(int index)
  {
    UserAccount aUserAccount = userAccount.get(index);
    return aUserAccount;
  }

  public List<UserAccount> getUserAccount()
  {
    List<UserAccount> newUserAccount = Collections.unmodifiableList(userAccount);
    return newUserAccount;
  }

  public int numberOfUserAccount()
  {
    int number = userAccount.size();
    return number;
  }

  public boolean hasUserAccount()
  {
    boolean has = userAccount.size() > 0;
    return has;
  }

  public int indexOfUserAccount(UserAccount aUserAccount)
  {
    int index = userAccount.indexOf(aUserAccount);
    return index;
  }
  /* Code from template association_GetOne */
  public Address getAddress()
  {
    return address;
  }
  /* Code from template association_GetOne */
  public CarShopInfo getCarShopInfo()
  {
    return carShopInfo;
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
  /* Code from template association_GetMany */
  public DaySchedule getDaySchedule(int index)
  {
    DaySchedule aDaySchedule = daySchedule.get(index);
    return aDaySchedule;
  }

  public List<DaySchedule> getDaySchedule()
  {
    List<DaySchedule> newDaySchedule = Collections.unmodifiableList(daySchedule);
    return newDaySchedule;
  }

  public int numberOfDaySchedule()
  {
    int number = daySchedule.size();
    return number;
  }

  public boolean hasDaySchedule()
  {
    boolean has = daySchedule.size() > 0;
    return has;
  }

  public int indexOfDaySchedule(DaySchedule aDaySchedule)
  {
    int index = daySchedule.indexOf(aDaySchedule);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserAccount()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public UserAccount addUserAccount(String aUsername, String aPassword)
  {
    return new UserAccount(aUsername, aPassword, this);
  }

  public boolean addUserAccount(UserAccount aUserAccount)
  {
    boolean wasAdded = false;
    if (userAccount.contains(aUserAccount)) { return false; }
    CarShop existingCarShop = aUserAccount.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aUserAccount.setCarShop(this);
    }
    else
    {
      userAccount.add(aUserAccount);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUserAccount(UserAccount aUserAccount)
  {
    boolean wasRemoved = false;
    //Unable to remove aUserAccount, as it must always have a carShop
    if (!this.equals(aUserAccount.getCarShop()))
    {
      userAccount.remove(aUserAccount);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAccountAt(UserAccount aUserAccount, int index)
  {  
    boolean wasAdded = false;
    if(addUserAccount(aUserAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserAccount()) { index = numberOfUserAccount() - 1; }
      userAccount.remove(aUserAccount);
      userAccount.add(index, aUserAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAccountAt(UserAccount aUserAccount, int index)
  {
    boolean wasAdded = false;
    if(userAccount.contains(aUserAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserAccount()) { index = numberOfUserAccount() - 1; }
      userAccount.remove(aUserAccount);
      userAccount.add(index, aUserAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAccountAt(aUserAccount, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWeeklySchedule()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public WeeklySchedule addWeeklySchedule(Date aWeekStart, Date aWeekEnd, CarShopInfo aCarShopInfo)
  {
    return new WeeklySchedule(aWeekStart, aWeekEnd, this, aCarShopInfo);
  }

  public boolean addWeeklySchedule(WeeklySchedule aWeeklySchedule)
  {
    boolean wasAdded = false;
    if (weeklySchedule.contains(aWeeklySchedule)) { return false; }
    CarShop existingCarShop = aWeeklySchedule.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aWeeklySchedule.setCarShop(this);
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
    //Unable to remove aWeeklySchedule, as it must always have a carShop
    if (!this.equals(aWeeklySchedule.getCarShop()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDaySchedule()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public DaySchedule addDaySchedule(Date aDate, int aStartTime, int aEndTime, boolean aIsHoliday, boolean aIsBreak, WeeklySchedule aWeeklySchedule)
  {
    return new DaySchedule(aDate, aStartTime, aEndTime, aIsHoliday, aIsBreak, this, aWeeklySchedule);
  }

  public boolean addDaySchedule(DaySchedule aDaySchedule)
  {
    boolean wasAdded = false;
    if (daySchedule.contains(aDaySchedule)) { return false; }
    CarShop existingCarShop = aDaySchedule.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aDaySchedule.setCarShop(this);
    }
    else
    {
      daySchedule.add(aDaySchedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDaySchedule(DaySchedule aDaySchedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aDaySchedule, as it must always have a carShop
    if (!this.equals(aDaySchedule.getCarShop()))
    {
      daySchedule.remove(aDaySchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDayScheduleAt(DaySchedule aDaySchedule, int index)
  {  
    boolean wasAdded = false;
    if(addDaySchedule(aDaySchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDaySchedule()) { index = numberOfDaySchedule() - 1; }
      daySchedule.remove(aDaySchedule);
      daySchedule.add(index, aDaySchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDayScheduleAt(DaySchedule aDaySchedule, int index)
  {
    boolean wasAdded = false;
    if(daySchedule.contains(aDaySchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDaySchedule()) { index = numberOfDaySchedule() - 1; }
      daySchedule.remove(aDaySchedule);
      daySchedule.add(index, aDaySchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDayScheduleAt(aDaySchedule, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (userAccount.size() > 0)
    {
      UserAccount aUserAccount = userAccount.get(userAccount.size() - 1);
      aUserAccount.delete();
      userAccount.remove(aUserAccount);
    }
    
    Address existingAddress = address;
    address = null;
    if (existingAddress != null)
    {
      existingAddress.delete();
    }
    CarShopInfo existingCarShopInfo = carShopInfo;
    carShopInfo = null;
    if (existingCarShopInfo != null)
    {
      existingCarShopInfo.delete();
    }
    while (weeklySchedule.size() > 0)
    {
      WeeklySchedule aWeeklySchedule = weeklySchedule.get(weeklySchedule.size() - 1);
      aWeeklySchedule.delete();
      weeklySchedule.remove(aWeeklySchedule);
    }
    
    while (daySchedule.size() > 0)
    {
      DaySchedule aDaySchedule = daySchedule.get(daySchedule.size() - 1);
      aDaySchedule.delete();
      daySchedule.remove(aDaySchedule);
    }
    
  }

}