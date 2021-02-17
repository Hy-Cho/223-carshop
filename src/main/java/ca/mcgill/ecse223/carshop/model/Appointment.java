/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.util.*;

// line 96 "../../../../../carshop.ump"
public abstract class Appointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment Associations
  private List<TimeSlot> allocated_timeslots;
  private CarShop carShop;
  private ClientAccount clientAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(CarShop aCarShop, ClientAccount aClientAccount)
  {
    allocated_timeslots = new ArrayList<TimeSlot>();
    boolean didAddCarShop = setCarShop(aCarShop);
    if (!didAddCarShop)
    {
      throw new RuntimeException("Unable to create appointment due to carShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClientAccount = setClientAccount(aClientAccount);
    if (!didAddClientAccount)
    {
      throw new RuntimeException("Unable to create client_appointment due to clientAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public TimeSlot getAllocated_timeslot(int index)
  {
    TimeSlot aAllocated_timeslot = allocated_timeslots.get(index);
    return aAllocated_timeslot;
  }

  public List<TimeSlot> getAllocated_timeslots()
  {
    List<TimeSlot> newAllocated_timeslots = Collections.unmodifiableList(allocated_timeslots);
    return newAllocated_timeslots;
  }

  public int numberOfAllocated_timeslots()
  {
    int number = allocated_timeslots.size();
    return number;
  }

  public boolean hasAllocated_timeslots()
  {
    boolean has = allocated_timeslots.size() > 0;
    return has;
  }

  public int indexOfAllocated_timeslot(TimeSlot aAllocated_timeslot)
  {
    int index = allocated_timeslots.indexOf(aAllocated_timeslot);
    return index;
  }
  /* Code from template association_GetOne */
  public CarShop getCarShop()
  {
    return carShop;
  }
  /* Code from template association_GetOne */
  public ClientAccount getClientAccount()
  {
    return clientAccount;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAllocated_timeslots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TimeSlot addAllocated_timeslot(int aStartTime, int aEndTime, CarShop aCarShop, GarageAgenda aGarage_agenda)
  {
    return new TimeSlot(aStartTime, aEndTime, aCarShop, aGarage_agenda, this);
  }

  public boolean addAllocated_timeslot(TimeSlot aAllocated_timeslot)
  {
    boolean wasAdded = false;
    if (allocated_timeslots.contains(aAllocated_timeslot)) { return false; }
    Appointment existingAppointment = aAllocated_timeslot.getAppointment();
    boolean isNewAppointment = existingAppointment != null && !this.equals(existingAppointment);
    if (isNewAppointment)
    {
      aAllocated_timeslot.setAppointment(this);
    }
    else
    {
      allocated_timeslots.add(aAllocated_timeslot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAllocated_timeslot(TimeSlot aAllocated_timeslot)
  {
    boolean wasRemoved = false;
    //Unable to remove aAllocated_timeslot, as it must always have a appointment
    if (!this.equals(aAllocated_timeslot.getAppointment()))
    {
      allocated_timeslots.remove(aAllocated_timeslot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAllocated_timeslotAt(TimeSlot aAllocated_timeslot, int index)
  {  
    boolean wasAdded = false;
    if(addAllocated_timeslot(aAllocated_timeslot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllocated_timeslots()) { index = numberOfAllocated_timeslots() - 1; }
      allocated_timeslots.remove(aAllocated_timeslot);
      allocated_timeslots.add(index, aAllocated_timeslot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAllocated_timeslotAt(TimeSlot aAllocated_timeslot, int index)
  {
    boolean wasAdded = false;
    if(allocated_timeslots.contains(aAllocated_timeslot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllocated_timeslots()) { index = numberOfAllocated_timeslots() - 1; }
      allocated_timeslots.remove(aAllocated_timeslot);
      allocated_timeslots.add(index, aAllocated_timeslot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAllocated_timeslotAt(aAllocated_timeslot, index);
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
  /* Code from template association_SetOneToMany */
  public boolean setClientAccount(ClientAccount aClientAccount)
  {
    boolean wasSet = false;
    if (aClientAccount == null)
    {
      return wasSet;
    }

    ClientAccount existingClientAccount = clientAccount;
    clientAccount = aClientAccount;
    if (existingClientAccount != null && !existingClientAccount.equals(aClientAccount))
    {
      existingClientAccount.removeClient_appointment(this);
    }
    clientAccount.addClient_appointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=allocated_timeslots.size(); i > 0; i--)
    {
      TimeSlot aAllocated_timeslot = allocated_timeslots.get(i - 1);
      aAllocated_timeslot.delete();
    }
    CarShop placeholderCarShop = carShop;
    this.carShop = null;
    if(placeholderCarShop != null)
    {
      placeholderCarShop.removeAppointment(this);
    }
    ClientAccount placeholderClientAccount = clientAccount;
    this.clientAccount = null;
    if(placeholderClientAccount != null)
    {
      placeholderClientAccount.removeClient_appointment(this);
    }
  }

}