/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.sql.Date;
import java.util.*;

// line 71 "../../../../../carshop.ump"
public class GarageAgenda
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Date, GarageAgenda> garageagendasByDate = new HashMap<Date, GarageAgenda>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GarageAgenda Attributes
  private Date date;
  private int openingTime;
  private int closingTime;

  //GarageAgenda Associations
  private List<TimeSlot> occupied_timslots;
  private CarShop carShop;
  private Garage garage;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GarageAgenda(Date aDate, int aOpeningTime, int aClosingTime, CarShop aCarShop, Garage aGarage)
  {
    openingTime = aOpeningTime;
    closingTime = aClosingTime;
    if (!setDate(aDate))
    {
      throw new RuntimeException("Cannot create due to duplicate date. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    occupied_timslots = new ArrayList<TimeSlot>();
    boolean didAddCarShop = setCarShop(aCarShop);
    if (!didAddCarShop)
    {
      throw new RuntimeException("Unable to create garageAgenda due to carShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGarage = setGarage(aGarage);
    if (!didAddGarage)
    {
      throw new RuntimeException("Unable to create daily_agenda due to garage. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    Date anOldDate = getDate();
    if (anOldDate != null && anOldDate.equals(aDate)) {
      return true;
    }
    if (hasWithDate(aDate)) {
      return wasSet;
    }
    date = aDate;
    wasSet = true;
    if (anOldDate != null) {
      garageagendasByDate.remove(anOldDate);
    }
    garageagendasByDate.put(aDate, this);
    return wasSet;
  }

  public boolean setOpeningTime(int aOpeningTime)
  {
    boolean wasSet = false;
    openingTime = aOpeningTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setClosingTime(int aClosingTime)
  {
    boolean wasSet = false;
    closingTime = aClosingTime;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }
  /* Code from template attribute_GetUnique */
  public static GarageAgenda getWithDate(Date aDate)
  {
    return garageagendasByDate.get(aDate);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithDate(Date aDate)
  {
    return getWithDate(aDate) != null;
  }

  public int getOpeningTime()
  {
    return openingTime;
  }

  public int getClosingTime()
  {
    return closingTime;
  }
  /* Code from template association_GetMany */
  public TimeSlot getOccupied_timslot(int index)
  {
    TimeSlot aOccupied_timslot = occupied_timslots.get(index);
    return aOccupied_timslot;
  }

  public List<TimeSlot> getOccupied_timslots()
  {
    List<TimeSlot> newOccupied_timslots = Collections.unmodifiableList(occupied_timslots);
    return newOccupied_timslots;
  }

  public int numberOfOccupied_timslots()
  {
    int number = occupied_timslots.size();
    return number;
  }

  public boolean hasOccupied_timslots()
  {
    boolean has = occupied_timslots.size() > 0;
    return has;
  }

  public int indexOfOccupied_timslot(TimeSlot aOccupied_timslot)
  {
    int index = occupied_timslots.indexOf(aOccupied_timslot);
    return index;
  }
  /* Code from template association_GetOne */
  public CarShop getCarShop()
  {
    return carShop;
  }
  /* Code from template association_GetOne */
  public Garage getGarage()
  {
    return garage;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOccupied_timslots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TimeSlot addOccupied_timslot(int aStartTime, int aEndTime, Service aService_performed, CarShop aCarShop, Appointment aAppointment)
  {
    return new TimeSlot(aStartTime, aEndTime, aService_performed, aCarShop, this, aAppointment);
  }

  public boolean addOccupied_timslot(TimeSlot aOccupied_timslot)
  {
    boolean wasAdded = false;
    if (occupied_timslots.contains(aOccupied_timslot)) { return false; }
    GarageAgenda existingGarage_agenda = aOccupied_timslot.getGarage_agenda();
    boolean isNewGarage_agenda = existingGarage_agenda != null && !this.equals(existingGarage_agenda);
    if (isNewGarage_agenda)
    {
      aOccupied_timslot.setGarage_agenda(this);
    }
    else
    {
      occupied_timslots.add(aOccupied_timslot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOccupied_timslot(TimeSlot aOccupied_timslot)
  {
    boolean wasRemoved = false;
    //Unable to remove aOccupied_timslot, as it must always have a garage_agenda
    if (!this.equals(aOccupied_timslot.getGarage_agenda()))
    {
      occupied_timslots.remove(aOccupied_timslot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOccupied_timslotAt(TimeSlot aOccupied_timslot, int index)
  {  
    boolean wasAdded = false;
    if(addOccupied_timslot(aOccupied_timslot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOccupied_timslots()) { index = numberOfOccupied_timslots() - 1; }
      occupied_timslots.remove(aOccupied_timslot);
      occupied_timslots.add(index, aOccupied_timslot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOccupied_timslotAt(TimeSlot aOccupied_timslot, int index)
  {
    boolean wasAdded = false;
    if(occupied_timslots.contains(aOccupied_timslot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOccupied_timslots()) { index = numberOfOccupied_timslots() - 1; }
      occupied_timslots.remove(aOccupied_timslot);
      occupied_timslots.add(index, aOccupied_timslot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOccupied_timslotAt(aOccupied_timslot, index);
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
      existingCarShop.removeGarageAgenda(this);
    }
    carShop.addGarageAgenda(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGarage(Garage aGarage)
  {
    boolean wasSet = false;
    if (aGarage == null)
    {
      return wasSet;
    }

    Garage existingGarage = garage;
    garage = aGarage;
    if (existingGarage != null && !existingGarage.equals(aGarage))
    {
      existingGarage.removeDaily_agenda(this);
    }
    garage.addDaily_agenda(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    garageagendasByDate.remove(getDate());
    for(int i=occupied_timslots.size(); i > 0; i--)
    {
      TimeSlot aOccupied_timslot = occupied_timslots.get(i - 1);
      aOccupied_timslot.delete();
    }
    CarShop placeholderCarShop = carShop;
    this.carShop = null;
    if(placeholderCarShop != null)
    {
      placeholderCarShop.removeGarageAgenda(this);
    }
    Garage placeholderGarage = garage;
    this.garage = null;
    if(placeholderGarage != null)
    {
      placeholderGarage.removeDaily_agenda(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "openingTime" + ":" + getOpeningTime()+ "," +
            "closingTime" + ":" + getClosingTime()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "carShop = "+(getCarShop()!=null?Integer.toHexString(System.identityHashCode(getCarShop())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "garage = "+(getGarage()!=null?Integer.toHexString(System.identityHashCode(getGarage())):"null");
  }
}