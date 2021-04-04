/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.carshop.model;
import java.io.Serializable;
import java.sql.Time;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import java.util.*;

// line 80 "../../../../../carshopPersistence.ump"
// line 1 "../../../../../carshopStates.ump"
// line 145 "../../../../../carshop.ump"
public class Appointment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment State Machines
  public enum States { Booking, Final, AppointmentInProgress }
  private States states;

  //Appointment Associations
  private Customer customer;
  private BookableService bookableService;
  private List<ServiceBooking> serviceBookings;
  private CarShop carShop;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(Customer aCustomer, BookableService aBookableService, CarShop aCarShop)
  {
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create appointment due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBookableService = setBookableService(aBookableService);
    if (!didAddBookableService)
    {
      throw new RuntimeException("Unable to create appointment due to bookableService. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    serviceBookings = new ArrayList<ServiceBooking>();
    boolean didAddCarShop = setCarShop(aCarShop);
    if (!didAddCarShop)
    {
      throw new RuntimeException("Unable to create appointment due to carShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setStates(States.Booking);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getStatesFullName()
  {
    String answer = states.toString();
    return answer;
  }

  public States getStates()
  {
    return states;
  }

  public boolean UpdateAppointment()
  {
    boolean wasEventProcessed = false;
    
    States aStates = states;
    switch (aStates)
    {
      case Booking:
        if (moreThan1DayRemaining()&&isInBusinessHour(newTimes)&&!(isHoliday(newTimes))&&!(isVacation(newTimes))&&checkAvailableSlot(newTimes))
        {
        // line 6 "../../../../../carshopStates.ump"
          updateAppointment(realName,optionalName,date,startTimes);
          setStates(States.Booking);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean No_Show()
  {
    boolean wasEventProcessed = false;
    
    States aStates = states;
    switch (aStates)
    {
      case Booking:
        if ((getCurTimes().after(getEndTimes())||getCurTimes().equals(getEndTimes())))
        {
        // line 8 "../../../../../carshopStates.ump"
          delete(); customer.incrNoShow();
          setStates(States.Final);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean CancelAppointment()
  {
    boolean wasEventProcessed = false;
    
    States aStates = states;
    switch (aStates)
    {
      case Booking:
        if (moreThan1DayRemaining())
        {
        // line 10 "../../../../../carshopStates.ump"
          delete();
          setStates(States.Final);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean Start()
  {
    boolean wasEventProcessed = false;
    
    States aStates = states;
    switch (aStates)
    {
      case Booking:
        if ((getStartTimes().before(getCurTimes())||getStartTimes().equals(getCurTimes())))
        {
          setStates(States.AppointmentInProgress);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean End()
  {
    boolean wasEventProcessed = false;
    
    States aStates = states;
    switch (aStates)
    {
      case AppointmentInProgress:
        if (getCurTimes().equals(getEndTimes()))
        {
        // line 15 "../../../../../carshopStates.ump"
          delete();
          setStates(States.Final);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean UpdateServiceCombo()
  {
    boolean wasEventProcessed = false;
    
    States aStates = states;
    switch (aStates)
    {
      case AppointmentInProgress:
        if (technicianIsAvailable()&&isInBusinessHour(newTimes)&&isHoliday(newTimes)&&!(isVacation(newTimes))&&checkAvailableSlot(newTimes))
        {
        // line 18 "../../../../../carshopStates.ump"
          delete(); updateCombo(newCombo); CarShopController.makeAppointmentCombo(realName,date,startTimes);
          setStates(States.AppointmentInProgress);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setStates(States aStates)
  {
    states = aStates;

    // entry actions and do activities
    switch(states)
    {
      case Final:
        delete();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public BookableService getBookableService()
  {
    return bookableService;
  }
  /* Code from template association_GetMany */
  public ServiceBooking getServiceBooking(int index)
  {
    ServiceBooking aServiceBooking = serviceBookings.get(index);
    return aServiceBooking;
  }

  public List<ServiceBooking> getServiceBookings()
  {
    List<ServiceBooking> newServiceBookings = Collections.unmodifiableList(serviceBookings);
    return newServiceBookings;
  }

  public int numberOfServiceBookings()
  {
    int number = serviceBookings.size();
    return number;
  }

  public boolean hasServiceBookings()
  {
    boolean has = serviceBookings.size() > 0;
    return has;
  }

  public int indexOfServiceBooking(ServiceBooking aServiceBooking)
  {
    int index = serviceBookings.indexOf(aServiceBooking);
    return index;
  }
  /* Code from template association_GetOne */
  public CarShop getCarShop()
  {
    return carShop;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeAppointment(this);
    }
    customer.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBookableService(BookableService aBookableService)
  {
    boolean wasSet = false;
    if (aBookableService == null)
    {
      return wasSet;
    }

    BookableService existingBookableService = bookableService;
    bookableService = aBookableService;
    if (existingBookableService != null && !existingBookableService.equals(aBookableService))
    {
      existingBookableService.removeAppointment(this);
    }
    bookableService.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServiceBookings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ServiceBooking addServiceBooking(Service aService, TimeSlot aTimeSlot)
  {
    return new ServiceBooking(aService, aTimeSlot, this);
  }

  public boolean addServiceBooking(ServiceBooking aServiceBooking)
  {
    boolean wasAdded = false;
    if (serviceBookings.contains(aServiceBooking)) { return false; }
    Appointment existingAppointment = aServiceBooking.getAppointment();
    boolean isNewAppointment = existingAppointment != null && !this.equals(existingAppointment);
    if (isNewAppointment)
    {
      aServiceBooking.setAppointment(this);
    }
    else
    {
      serviceBookings.add(aServiceBooking);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeServiceBooking(ServiceBooking aServiceBooking)
  {
    boolean wasRemoved = false;
    //Unable to remove aServiceBooking, as it must always have a appointment
    if (!this.equals(aServiceBooking.getAppointment()))
    {
      serviceBookings.remove(aServiceBooking);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceBookingAt(ServiceBooking aServiceBooking, int index)
  {  
    boolean wasAdded = false;
    if(addServiceBooking(aServiceBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceBookings()) { index = numberOfServiceBookings() - 1; }
      serviceBookings.remove(aServiceBooking);
      serviceBookings.add(index, aServiceBooking);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceBookingAt(ServiceBooking aServiceBooking, int index)
  {
    boolean wasAdded = false;
    if(serviceBookings.contains(aServiceBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceBookings()) { index = numberOfServiceBookings() - 1; }
      serviceBookings.remove(aServiceBooking);
      serviceBookings.add(index, aServiceBooking);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceBookingAt(aServiceBooking, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCarShop(CarShop aCarShop)
  {
    boolean wasSet = false;
    if (aCarShop == null)
    {
      return wasSet;
    }

    CarShop existingCarShop = carShop;
    carShop = aCarShop;
    if (existingCarShop != null && !existingCarShop.equals(aCarShop))
    {
      existingCarShop.removeAppointment(this);
    }
    carShop.addAppointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeAppointment(this);
    }
    BookableService placeholderBookableService = bookableService;
    this.bookableService = null;
    if(placeholderBookableService != null)
    {
      placeholderBookableService.removeAppointment(this);
    }
    while (serviceBookings.size() > 0)
    {
      ServiceBooking aServiceBooking = serviceBookings.get(serviceBookings.size() - 1);
      aServiceBooking.delete();
      serviceBookings.remove(aServiceBooking);
    }
    
    CarShop placeholderCarShop = carShop;
    this.carShop = null;
    if(placeholderCarShop != null)
    {
      placeholderCarShop.removeAppointment(this);
    }
  }

  // line 23 "../../../../../carshopStates.ump"
   private void updateAppointment(String realName, String optionalName, String date, String startTimes){
    delete();
  	if(bookableService instanceof ServiceCombo){
      	CarShopController.makeAppointmentCombo(realName,optionalName,date,startTimes);
     }
    else{
	   	CarShopController.makeAppointmentService(realName,date,startTimes);
    }
  }

  // line 34 "../../../../../carshopStates.ump"
   private Time getStartTimes(){
    ServiceBooking firstBooking = this.getServiceBookings().get(0);
	return firstBooking.getTimeSlot().getStartTime();
  }

  // line 41 "../../../../../carshopStates.ump"
   private Time getEndTimes(){
    int length = this.getServiceBookings().size();
	ServiceBooking lastBooking = this.getServiceBookings().get(length-1);
	return lastBooking.getTimeSlot().getEndTime();
  }

  // line 48 "../../../../../carshopStates.ump"
   private Time getCurTimes(){
    return CarShopController.getCurrentTimes();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 83 "../../../../../carshopPersistence.ump"
  private static final long serialVersionUID = 1943199919927718071L ;

  
}