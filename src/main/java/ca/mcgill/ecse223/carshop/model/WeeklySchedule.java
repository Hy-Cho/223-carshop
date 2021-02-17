/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.sql.Date;
import java.util.*;

// line 51 "../../../../../carshop.ump"
public class WeeklySchedule
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Date, WeeklySchedule> weeklyschedulesByWeekStart = new HashMap<Date, WeeklySchedule>();
  private static Map<Date, WeeklySchedule> weeklyschedulesByWeekEnd = new HashMap<Date, WeeklySchedule>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WeeklySchedule Attributes
  private Date weekStart;
  private Date weekEnd;

  //WeeklySchedule Associations
  private List<DaySchedule> days_schedule;
  private CarShop carShop;
  private CarShopInfo car_shop;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WeeklySchedule(Date aWeekStart, Date aWeekEnd, CarShop aCarShop, CarShopInfo aCar_shop)
  {
    if (!setWeekStart(aWeekStart))
    {
      throw new RuntimeException("Cannot create due to duplicate weekStart. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    if (!setWeekEnd(aWeekEnd))
    {
      throw new RuntimeException("Cannot create due to duplicate weekEnd. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    days_schedule = new ArrayList<DaySchedule>();
    boolean didAddCarShop = setCarShop(aCarShop);
    if (!didAddCarShop)
    {
      throw new RuntimeException("Unable to create weeksSchedule due to carShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCar_shop = setCar_shop(aCar_shop);
    if (!didAddCar_shop)
    {
      throw new RuntimeException("Unable to create weeks_schedule due to car_shop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeekStart(Date aWeekStart)
  {
    boolean wasSet = false;
    Date anOldWeekStart = getWeekStart();
    if (anOldWeekStart != null && anOldWeekStart.equals(aWeekStart)) {
      return true;
    }
    if (hasWithWeekStart(aWeekStart)) {
      return wasSet;
    }
    weekStart = aWeekStart;
    wasSet = true;
    if (anOldWeekStart != null) {
      weeklyschedulesByWeekStart.remove(anOldWeekStart);
    }
    weeklyschedulesByWeekStart.put(aWeekStart, this);
    return wasSet;
  }

  public boolean setWeekEnd(Date aWeekEnd)
  {
    boolean wasSet = false;
    Date anOldWeekEnd = getWeekEnd();
    if (anOldWeekEnd != null && anOldWeekEnd.equals(aWeekEnd)) {
      return true;
    }
    if (hasWithWeekEnd(aWeekEnd)) {
      return wasSet;
    }
    weekEnd = aWeekEnd;
    wasSet = true;
    if (anOldWeekEnd != null) {
      weeklyschedulesByWeekEnd.remove(anOldWeekEnd);
    }
    weeklyschedulesByWeekEnd.put(aWeekEnd, this);
    return wasSet;
  }

  public Date getWeekStart()
  {
    return weekStart;
  }
  /* Code from template attribute_GetUnique */
  public static WeeklySchedule getWithWeekStart(Date aWeekStart)
  {
    return weeklyschedulesByWeekStart.get(aWeekStart);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithWeekStart(Date aWeekStart)
  {
    return getWithWeekStart(aWeekStart) != null;
  }

  public Date getWeekEnd()
  {
    return weekEnd;
  }
  /* Code from template attribute_GetUnique */
  public static WeeklySchedule getWithWeekEnd(Date aWeekEnd)
  {
    return weeklyschedulesByWeekEnd.get(aWeekEnd);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithWeekEnd(Date aWeekEnd)
  {
    return getWithWeekEnd(aWeekEnd) != null;
  }
  /* Code from template association_GetMany */
  public DaySchedule getDays_schedule(int index)
  {
    DaySchedule aDays_schedule = days_schedule.get(index);
    return aDays_schedule;
  }

  public List<DaySchedule> getDays_schedule()
  {
    List<DaySchedule> newDays_schedule = Collections.unmodifiableList(days_schedule);
    return newDays_schedule;
  }

  public int numberOfDays_schedule()
  {
    int number = days_schedule.size();
    return number;
  }

  public boolean hasDays_schedule()
  {
    boolean has = days_schedule.size() > 0;
    return has;
  }

  public int indexOfDays_schedule(DaySchedule aDays_schedule)
  {
    int index = days_schedule.indexOf(aDays_schedule);
    return index;
  }
  /* Code from template association_GetOne */
  public CarShop getCarShop()
  {
    return carShop;
  }
  /* Code from template association_GetOne */
  public CarShopInfo getCar_shop()
  {
    return car_shop;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfDays_scheduleValid()
  {
    boolean isValid = numberOfDays_schedule() >= minimumNumberOfDays_schedule() && numberOfDays_schedule() <= maximumNumberOfDays_schedule();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfDays_schedule()
  {
    return 7;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDays_schedule()
  {
    return 7;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfDays_schedule()
  {
    return 7;
  }
  /* Code from template association_AddMNToOnlyOne */
  public DaySchedule addDays_schedule(Date aDate, int aStartTime, int aEndTime, boolean aIsHoliday, boolean aIsBreak, CarShop aCarShop)
  {
    if (numberOfDays_schedule() >= maximumNumberOfDays_schedule())
    {
      return null;
    }
    else
    {
      return new DaySchedule(aDate, aStartTime, aEndTime, aIsHoliday, aIsBreak, aCarShop, this);
    }
  }

  public boolean addDays_schedule(DaySchedule aDays_schedule)
  {
    boolean wasAdded = false;
    if (days_schedule.contains(aDays_schedule)) { return false; }
    if (numberOfDays_schedule() >= maximumNumberOfDays_schedule())
    {
      return wasAdded;
    }

    WeeklySchedule existingWeekly_schedule = aDays_schedule.getWeekly_schedule();
    boolean isNewWeekly_schedule = existingWeekly_schedule != null && !this.equals(existingWeekly_schedule);

    if (isNewWeekly_schedule && existingWeekly_schedule.numberOfDays_schedule() <= minimumNumberOfDays_schedule())
    {
      return wasAdded;
    }

    if (isNewWeekly_schedule)
    {
      aDays_schedule.setWeekly_schedule(this);
    }
    else
    {
      days_schedule.add(aDays_schedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDays_schedule(DaySchedule aDays_schedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aDays_schedule, as it must always have a weekly_schedule
    if (this.equals(aDays_schedule.getWeekly_schedule()))
    {
      return wasRemoved;
    }

    //weekly_schedule already at minimum (7)
    if (numberOfDays_schedule() <= minimumNumberOfDays_schedule())
    {
      return wasRemoved;
    }
    days_schedule.remove(aDays_schedule);
    wasRemoved = true;
    return wasRemoved;
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
      existingCarShop.removeWeeksSchedule(this);
    }
    carShop.addWeeksSchedule(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCar_shop(CarShopInfo aCar_shop)
  {
    boolean wasSet = false;
    if (aCar_shop == null)
    {
      return wasSet;
    }

    CarShopInfo existingCar_shop = car_shop;
    car_shop = aCar_shop;
    if (existingCar_shop != null && !existingCar_shop.equals(aCar_shop))
    {
      existingCar_shop.removeWeeks_schedule(this);
    }
    car_shop.addWeeks_schedule(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    weeklyschedulesByWeekStart.remove(getWeekStart());
    weeklyschedulesByWeekEnd.remove(getWeekEnd());
    for(int i=days_schedule.size(); i > 0; i--)
    {
      DaySchedule aDays_schedule = days_schedule.get(i - 1);
      aDays_schedule.delete();
    }
    CarShop placeholderCarShop = carShop;
    this.carShop = null;
    if(placeholderCarShop != null)
    {
      placeholderCarShop.removeWeeksSchedule(this);
    }
    CarShopInfo placeholderCar_shop = car_shop;
    this.car_shop = null;
    if(placeholderCar_shop != null)
    {
      placeholderCar_shop.removeWeeks_schedule(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "weekStart" + "=" + (getWeekStart() != null ? !getWeekStart().equals(this)  ? getWeekStart().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "weekEnd" + "=" + (getWeekEnd() != null ? !getWeekEnd().equals(this)  ? getWeekEnd().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "carShop = "+(getCarShop()!=null?Integer.toHexString(System.identityHashCode(getCarShop())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "car_shop = "+(getCar_shop()!=null?Integer.toHexString(System.identityHashCode(getCar_shop())):"null");
  }
}