/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.util.*;
import java.sql.Date;

// line 3 "../../../../../carshop.ump"
public class CarShop
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CarShop Associations
  private List<UserAccount> userAccount;
  private Address address;
  private CarShopInfo carShopInfo;
  private List<WeeklySchedule> weeksSchedule;
  private List<DaySchedule> daySchedule;
  private List<Service> services;
  private List<ServiceCombo> serviceCombos;
  private List<Garage> garages;
  private List<GarageAgenda> garageAgendas;
  private List<TimeSlot> timeSlots;
  private List<Appointment> appointments;

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
    weeksSchedule = new ArrayList<WeeklySchedule>();
    daySchedule = new ArrayList<DaySchedule>();
    services = new ArrayList<Service>();
    serviceCombos = new ArrayList<ServiceCombo>();
    garages = new ArrayList<Garage>();
    garageAgendas = new ArrayList<GarageAgenda>();
    timeSlots = new ArrayList<TimeSlot>();
    appointments = new ArrayList<Appointment>();
  }

  public CarShop(String aStreetnameForAddress, int aStreetNumberForAddress, String aPostalCodeForAddress, String aCityForAddress, CarShopInfo aCar_shopForAddress, String aPhoneNumberForCarShopInfo, String aEmailAddressForCarShopInfo, Address aAddressForCarShopInfo, OwnerAccount aOwnerAccountForCarShopInfo)
  {
    userAccount = new ArrayList<UserAccount>();
    address = new Address(aStreetnameForAddress, aStreetNumberForAddress, aPostalCodeForAddress, aCityForAddress, this, aCar_shopForAddress);
    carShopInfo = new CarShopInfo(aPhoneNumberForCarShopInfo, aEmailAddressForCarShopInfo, aAddressForCarShopInfo, this, aOwnerAccountForCarShopInfo);
    weeksSchedule = new ArrayList<WeeklySchedule>();
    daySchedule = new ArrayList<DaySchedule>();
    services = new ArrayList<Service>();
    serviceCombos = new ArrayList<ServiceCombo>();
    garages = new ArrayList<Garage>();
    garageAgendas = new ArrayList<GarageAgenda>();
    timeSlots = new ArrayList<TimeSlot>();
    appointments = new ArrayList<Appointment>();
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
  public WeeklySchedule getWeeksSchedule(int index)
  {
    WeeklySchedule aWeeksSchedule = weeksSchedule.get(index);
    return aWeeksSchedule;
  }

  public List<WeeklySchedule> getWeeksSchedule()
  {
    List<WeeklySchedule> newWeeksSchedule = Collections.unmodifiableList(weeksSchedule);
    return newWeeksSchedule;
  }

  public int numberOfWeeksSchedule()
  {
    int number = weeksSchedule.size();
    return number;
  }

  public boolean hasWeeksSchedule()
  {
    boolean has = weeksSchedule.size() > 0;
    return has;
  }

  public int indexOfWeeksSchedule(WeeklySchedule aWeeksSchedule)
  {
    int index = weeksSchedule.indexOf(aWeeksSchedule);
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
  /* Code from template association_GetMany */
  public Service getService(int index)
  {
    Service aService = services.get(index);
    return aService;
  }

  public List<Service> getServices()
  {
    List<Service> newServices = Collections.unmodifiableList(services);
    return newServices;
  }

  public int numberOfServices()
  {
    int number = services.size();
    return number;
  }

  public boolean hasServices()
  {
    boolean has = services.size() > 0;
    return has;
  }

  public int indexOfService(Service aService)
  {
    int index = services.indexOf(aService);
    return index;
  }
  /* Code from template association_GetMany */
  public ServiceCombo getServiceCombo(int index)
  {
    ServiceCombo aServiceCombo = serviceCombos.get(index);
    return aServiceCombo;
  }

  public List<ServiceCombo> getServiceCombos()
  {
    List<ServiceCombo> newServiceCombos = Collections.unmodifiableList(serviceCombos);
    return newServiceCombos;
  }

  public int numberOfServiceCombos()
  {
    int number = serviceCombos.size();
    return number;
  }

  public boolean hasServiceCombos()
  {
    boolean has = serviceCombos.size() > 0;
    return has;
  }

  public int indexOfServiceCombo(ServiceCombo aServiceCombo)
  {
    int index = serviceCombos.indexOf(aServiceCombo);
    return index;
  }
  /* Code from template association_GetMany */
  public Garage getGarage(int index)
  {
    Garage aGarage = garages.get(index);
    return aGarage;
  }

  public List<Garage> getGarages()
  {
    List<Garage> newGarages = Collections.unmodifiableList(garages);
    return newGarages;
  }

  public int numberOfGarages()
  {
    int number = garages.size();
    return number;
  }

  public boolean hasGarages()
  {
    boolean has = garages.size() > 0;
    return has;
  }

  public int indexOfGarage(Garage aGarage)
  {
    int index = garages.indexOf(aGarage);
    return index;
  }
  /* Code from template association_GetMany */
  public GarageAgenda getGarageAgenda(int index)
  {
    GarageAgenda aGarageAgenda = garageAgendas.get(index);
    return aGarageAgenda;
  }

  public List<GarageAgenda> getGarageAgendas()
  {
    List<GarageAgenda> newGarageAgendas = Collections.unmodifiableList(garageAgendas);
    return newGarageAgendas;
  }

  public int numberOfGarageAgendas()
  {
    int number = garageAgendas.size();
    return number;
  }

  public boolean hasGarageAgendas()
  {
    boolean has = garageAgendas.size() > 0;
    return has;
  }

  public int indexOfGarageAgenda(GarageAgenda aGarageAgenda)
  {
    int index = garageAgendas.indexOf(aGarageAgenda);
    return index;
  }
  /* Code from template association_GetMany */
  public TimeSlot getTimeSlot(int index)
  {
    TimeSlot aTimeSlot = timeSlots.get(index);
    return aTimeSlot;
  }

  public List<TimeSlot> getTimeSlots()
  {
    List<TimeSlot> newTimeSlots = Collections.unmodifiableList(timeSlots);
    return newTimeSlots;
  }

  public int numberOfTimeSlots()
  {
    int number = timeSlots.size();
    return number;
  }

  public boolean hasTimeSlots()
  {
    boolean has = timeSlots.size() > 0;
    return has;
  }

  public int indexOfTimeSlot(TimeSlot aTimeSlot)
  {
    int index = timeSlots.indexOf(aTimeSlot);
    return index;
  }
  /* Code from template association_GetMany */
  public Appointment getAppointment(int index)
  {
    Appointment aAppointment = appointments.get(index);
    return aAppointment;
  }

  public List<Appointment> getAppointments()
  {
    List<Appointment> newAppointments = Collections.unmodifiableList(appointments);
    return newAppointments;
  }

  public int numberOfAppointments()
  {
    int number = appointments.size();
    return number;
  }

  public boolean hasAppointments()
  {
    boolean has = appointments.size() > 0;
    return has;
  }

  public int indexOfAppointment(Appointment aAppointment)
  {
    int index = appointments.indexOf(aAppointment);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserAccount()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


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
  public static int minimumNumberOfWeeksSchedule()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public WeeklySchedule addWeeksSchedule(Date aWeekStart, Date aWeekEnd, CarShopInfo aCar_shop)
  {
    return new WeeklySchedule(aWeekStart, aWeekEnd, this, aCar_shop);
  }

  public boolean addWeeksSchedule(WeeklySchedule aWeeksSchedule)
  {
    boolean wasAdded = false;
    if (weeksSchedule.contains(aWeeksSchedule)) { return false; }
    CarShop existingCarShop = aWeeksSchedule.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aWeeksSchedule.setCarShop(this);
    }
    else
    {
      weeksSchedule.add(aWeeksSchedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWeeksSchedule(WeeklySchedule aWeeksSchedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aWeeksSchedule, as it must always have a carShop
    if (!this.equals(aWeeksSchedule.getCarShop()))
    {
      weeksSchedule.remove(aWeeksSchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWeeksScheduleAt(WeeklySchedule aWeeksSchedule, int index)
  {  
    boolean wasAdded = false;
    if(addWeeksSchedule(aWeeksSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeksSchedule()) { index = numberOfWeeksSchedule() - 1; }
      weeksSchedule.remove(aWeeksSchedule);
      weeksSchedule.add(index, aWeeksSchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWeeksScheduleAt(WeeklySchedule aWeeksSchedule, int index)
  {
    boolean wasAdded = false;
    if(weeksSchedule.contains(aWeeksSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeksSchedule()) { index = numberOfWeeksSchedule() - 1; }
      weeksSchedule.remove(aWeeksSchedule);
      weeksSchedule.add(index, aWeeksSchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWeeksScheduleAt(aWeeksSchedule, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDaySchedule()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public DaySchedule addDaySchedule(Date aDate, int aStartTime, int aEndTime, boolean aIsHoliday, boolean aIsBreak, WeeklySchedule aWeekly_schedule)
  {
    return new DaySchedule(aDate, aStartTime, aEndTime, aIsHoliday, aIsBreak, this, aWeekly_schedule);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Service addService(String aName, int aDuration, Garage aGarage)
  {
    return new Service(aName, aDuration, this, aGarage);
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    CarShop existingCarShop = aService.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aService.setCarShop(this);
    }
    else
    {
      services.add(aService);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeService(Service aService)
  {
    boolean wasRemoved = false;
    //Unable to remove aService, as it must always have a carShop
    if (!this.equals(aService.getCarShop()))
    {
      services.remove(aService);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceAt(Service aService, int index)
  {  
    boolean wasAdded = false;
    if(addService(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceAt(Service aService, int index)
  {
    boolean wasAdded = false;
    if(services.contains(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceAt(aService, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServiceCombos()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ServiceCombo addServiceCombo(ServiceCombo.ComboType aType, String aName, Service aMain_service)
  {
    return new ServiceCombo(aType, aName, aMain_service, this);
  }

  public boolean addServiceCombo(ServiceCombo aServiceCombo)
  {
    boolean wasAdded = false;
    if (serviceCombos.contains(aServiceCombo)) { return false; }
    CarShop existingCarShop = aServiceCombo.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aServiceCombo.setCarShop(this);
    }
    else
    {
      serviceCombos.add(aServiceCombo);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeServiceCombo(ServiceCombo aServiceCombo)
  {
    boolean wasRemoved = false;
    //Unable to remove aServiceCombo, as it must always have a carShop
    if (!this.equals(aServiceCombo.getCarShop()))
    {
      serviceCombos.remove(aServiceCombo);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceComboAt(ServiceCombo aServiceCombo, int index)
  {  
    boolean wasAdded = false;
    if(addServiceCombo(aServiceCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceCombos()) { index = numberOfServiceCombos() - 1; }
      serviceCombos.remove(aServiceCombo);
      serviceCombos.add(index, aServiceCombo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceComboAt(ServiceCombo aServiceCombo, int index)
  {
    boolean wasAdded = false;
    if(serviceCombos.contains(aServiceCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceCombos()) { index = numberOfServiceCombos() - 1; }
      serviceCombos.remove(aServiceCombo);
      serviceCombos.add(index, aServiceCombo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceComboAt(aServiceCombo, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfGaragesValid()
  {
    boolean isValid = numberOfGarages() >= minimumNumberOfGarages() && numberOfGarages() <= maximumNumberOfGarages();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfGarages()
  {
    return 5;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGarages()
  {
    return 5;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfGarages()
  {
    return 5;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Garage addGarage(TechnicianAccount aTechnician)
  {
    if (numberOfGarages() >= maximumNumberOfGarages())
    {
      return null;
    }
    else
    {
      return new Garage(this, aTechnician);
    }
  }

  public boolean addGarage(Garage aGarage)
  {
    boolean wasAdded = false;
    if (garages.contains(aGarage)) { return false; }
    if (numberOfGarages() >= maximumNumberOfGarages())
    {
      return wasAdded;
    }

    CarShop existingCarShop = aGarage.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);

    if (isNewCarShop && existingCarShop.numberOfGarages() <= minimumNumberOfGarages())
    {
      return wasAdded;
    }

    if (isNewCarShop)
    {
      aGarage.setCarShop(this);
    }
    else
    {
      garages.add(aGarage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGarage(Garage aGarage)
  {
    boolean wasRemoved = false;
    //Unable to remove aGarage, as it must always have a carShop
    if (this.equals(aGarage.getCarShop()))
    {
      return wasRemoved;
    }

    //carShop already at minimum (5)
    if (numberOfGarages() <= minimumNumberOfGarages())
    {
      return wasRemoved;
    }
    garages.remove(aGarage);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGarageAgendas()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public GarageAgenda addGarageAgenda(Date aDate, int aOpeningTime, int aClosingTime, Garage aGarage)
  {
    return new GarageAgenda(aDate, aOpeningTime, aClosingTime, this, aGarage);
  }

  public boolean addGarageAgenda(GarageAgenda aGarageAgenda)
  {
    boolean wasAdded = false;
    if (garageAgendas.contains(aGarageAgenda)) { return false; }
    CarShop existingCarShop = aGarageAgenda.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aGarageAgenda.setCarShop(this);
    }
    else
    {
      garageAgendas.add(aGarageAgenda);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGarageAgenda(GarageAgenda aGarageAgenda)
  {
    boolean wasRemoved = false;
    //Unable to remove aGarageAgenda, as it must always have a carShop
    if (!this.equals(aGarageAgenda.getCarShop()))
    {
      garageAgendas.remove(aGarageAgenda);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGarageAgendaAt(GarageAgenda aGarageAgenda, int index)
  {  
    boolean wasAdded = false;
    if(addGarageAgenda(aGarageAgenda))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGarageAgendas()) { index = numberOfGarageAgendas() - 1; }
      garageAgendas.remove(aGarageAgenda);
      garageAgendas.add(index, aGarageAgenda);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGarageAgendaAt(GarageAgenda aGarageAgenda, int index)
  {
    boolean wasAdded = false;
    if(garageAgendas.contains(aGarageAgenda))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGarageAgendas()) { index = numberOfGarageAgendas() - 1; }
      garageAgendas.remove(aGarageAgenda);
      garageAgendas.add(index, aGarageAgenda);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGarageAgendaAt(aGarageAgenda, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeSlots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TimeSlot addTimeSlot(int aStartTime, int aEndTime, GarageAgenda aGarage_agenda, Appointment aAppointment)
  {
    return new TimeSlot(aStartTime, aEndTime, this, aGarage_agenda, aAppointment);
  }

  public boolean addTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasAdded = false;
    if (timeSlots.contains(aTimeSlot)) { return false; }
    CarShop existingCarShop = aTimeSlot.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aTimeSlot.setCarShop(this);
    }
    else
    {
      timeSlots.add(aTimeSlot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasRemoved = false;
    //Unable to remove aTimeSlot, as it must always have a carShop
    if (!this.equals(aTimeSlot.getCarShop()))
    {
      timeSlots.remove(aTimeSlot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTimeSlotAt(TimeSlot aTimeSlot, int index)
  {  
    boolean wasAdded = false;
    if(addTimeSlot(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTimeSlotAt(TimeSlot aTimeSlot, int index)
  {
    boolean wasAdded = false;
    if(timeSlots.contains(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTimeSlotAt(aTimeSlot, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    CarShop existingCarShop = aAppointment.getCarShop();
    boolean isNewCarShop = existingCarShop != null && !this.equals(existingCarShop);
    if (isNewCarShop)
    {
      aAppointment.setCarShop(this);
    }
    else
    {
      appointments.add(aAppointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAppointment, as it must always have a carShop
    if (!this.equals(aAppointment.getCarShop()))
    {
      appointments.remove(aAppointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAppointmentAt(Appointment aAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addAppointment(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAppointmentAt(Appointment aAppointment, int index)
  {
    boolean wasAdded = false;
    if(appointments.contains(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAppointmentAt(aAppointment, index);
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
    while (weeksSchedule.size() > 0)
    {
      WeeklySchedule aWeeksSchedule = weeksSchedule.get(weeksSchedule.size() - 1);
      aWeeksSchedule.delete();
      weeksSchedule.remove(aWeeksSchedule);
    }
    
    while (daySchedule.size() > 0)
    {
      DaySchedule aDaySchedule = daySchedule.get(daySchedule.size() - 1);
      aDaySchedule.delete();
      daySchedule.remove(aDaySchedule);
    }
    
    while (services.size() > 0)
    {
      Service aService = services.get(services.size() - 1);
      aService.delete();
      services.remove(aService);
    }
    
    while (serviceCombos.size() > 0)
    {
      ServiceCombo aServiceCombo = serviceCombos.get(serviceCombos.size() - 1);
      aServiceCombo.delete();
      serviceCombos.remove(aServiceCombo);
    }
    
    while (garages.size() > 0)
    {
      Garage aGarage = garages.get(garages.size() - 1);
      aGarage.delete();
      garages.remove(aGarage);
    }
    
    while (garageAgendas.size() > 0)
    {
      GarageAgenda aGarageAgenda = garageAgendas.get(garageAgendas.size() - 1);
      aGarageAgenda.delete();
      garageAgendas.remove(aGarageAgenda);
    }
    
    while (timeSlots.size() > 0)
    {
      TimeSlot aTimeSlot = timeSlots.get(timeSlots.size() - 1);
      aTimeSlot.delete();
      timeSlots.remove(aTimeSlot);
    }
    
    while (appointments.size() > 0)
    {
      Appointment aAppointment = appointments.get(appointments.size() - 1);
      aAppointment.delete();
      appointments.remove(aAppointment);
    }
    
  }

}