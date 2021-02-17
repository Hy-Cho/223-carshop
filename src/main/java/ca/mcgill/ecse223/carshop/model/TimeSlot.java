/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;

// line 78 "../../../../../carshop.ump"
public class TimeSlot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TimeSlot Attributes
  private int startTime;
  private int endTime;

  //TimeSlot Associations
  private CarShop carShop;
  private GarageAgenda garage_agenda;
  private Appointment appointment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TimeSlot(int aStartTime, int aEndTime, CarShop aCarShop, GarageAgenda aGarage_agenda, Appointment aAppointment)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    boolean didAddCarShop = setCarShop(aCarShop);
    if (!didAddCarShop)
    {
      throw new RuntimeException("Unable to create timeSlot due to carShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGarage_agenda = setGarage_agenda(aGarage_agenda);
    if (!didAddGarage_agenda)
    {
      throw new RuntimeException("Unable to create occupied_timslot due to garage_agenda. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAppointment = setAppointment(aAppointment);
    if (!didAddAppointment)
    {
      throw new RuntimeException("Unable to create allocated_timeslot due to appointment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(int aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(int aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public int getStartTime()
  {
    return startTime;
  }

  public int getEndTime()
  {
    return endTime;
  }
  /* Code from template association_GetOne */
  public CarShop getCarShop()
  {
    return carShop;
  }
  /* Code from template association_GetOne */
  public GarageAgenda getGarage_agenda()
  {
    return garage_agenda;
  }
  /* Code from template association_GetOne */
  public Appointment getAppointment()
  {
    return appointment;
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
      existingCarShop.removeTimeSlot(this);
    }
    carShop.addTimeSlot(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGarage_agenda(GarageAgenda aGarage_agenda)
  {
    boolean wasSet = false;
    if (aGarage_agenda == null)
    {
      return wasSet;
    }

    GarageAgenda existingGarage_agenda = garage_agenda;
    garage_agenda = aGarage_agenda;
    if (existingGarage_agenda != null && !existingGarage_agenda.equals(aGarage_agenda))
    {
      existingGarage_agenda.removeOccupied_timslot(this);
    }
    garage_agenda.addOccupied_timslot(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAppointment(Appointment aAppointment)
  {
    boolean wasSet = false;
    if (aAppointment == null)
    {
      return wasSet;
    }

    Appointment existingAppointment = appointment;
    appointment = aAppointment;
    if (existingAppointment != null && !existingAppointment.equals(aAppointment))
    {
      existingAppointment.removeAllocated_timeslot(this);
    }
    appointment.addAllocated_timeslot(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    CarShop placeholderCarShop = carShop;
    this.carShop = null;
    if(placeholderCarShop != null)
    {
      placeholderCarShop.removeTimeSlot(this);
    }
    GarageAgenda placeholderGarage_agenda = garage_agenda;
    this.garage_agenda = null;
    if(placeholderGarage_agenda != null)
    {
      placeholderGarage_agenda.removeOccupied_timslot(this);
    }
    Appointment placeholderAppointment = appointment;
    this.appointment = null;
    if(placeholderAppointment != null)
    {
      placeholderAppointment.removeAllocated_timeslot(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "startTime" + ":" + getStartTime()+ "," +
            "endTime" + ":" + getEndTime()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "carShop = "+(getCarShop()!=null?Integer.toHexString(System.identityHashCode(getCarShop())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "garage_agenda = "+(getGarage_agenda()!=null?Integer.toHexString(System.identityHashCode(getGarage_agenda())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "appointment = "+(getAppointment()!=null?Integer.toHexString(System.identityHashCode(getAppointment())):"null");
  }
}