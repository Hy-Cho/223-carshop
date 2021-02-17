/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.sql.Date;
import java.util.*;

// line 38 "carshop.ump"
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
  private List<DaySchedule> daySchedule;
  private CarShop carShop;
  private CarShopInfo carShopInfo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WeeklySchedule(Date aWeekStart, Date aWeekEnd, CarShop aCarShop, CarShopInfo aCarShopInfo)
  {
    if (!setWeekStart(aWeekStart))
    {
      throw new RuntimeException("Cannot create due to duplicate weekStart. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    if (!setWeekEnd(aWeekEnd))
    {
      throw new RuntimeException("Cannot create due to duplicate weekEnd. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    daySchedule = new ArrayList<DaySchedule>();
    boolean didAddCarShop = setCarShop(aCarShop);
    if (!didAddCarShop)
    {
      throw new RuntimeException("Unable to create weeklySchedule due to carShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCarShopInfo = setCarShopInfo(aCarShopInfo);
    if (!didAddCarShopInfo)
    {
      throw new RuntimeException("Unable to create weeklySchedule due to carShopInfo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  /* Code from template association_GetOne */
  public CarShop getCarShop()
  {
    return carShop;
  }
  /* Code from template association_GetOne */
  public CarShopInfo getCarShopInfo()
  {
    return carShopInfo;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfDayScheduleValid()
  {
    boolean isValid = numberOfDaySchedule() >= minimumNumberOfDaySchedule() && numberOfDaySchedule() <= maximumNumberOfDaySchedule();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfDaySchedule()
  {
    return 7;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDaySchedule()
  {
    return 7;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfDaySchedule()
  {
    return 7;
  }
  /* Code from template association_AddMNToOnlyOne */
  public DaySchedule addDaySchedule(Date aDate, int aStartTime, int aEndTime, boolean aIsHoliday, boolean aIsBreak, CarShop aCarShop)
  {
    if (numberOfDaySchedule() >= maximumNumberOfDaySchedule())
    {
      return null;
    }
    else
    {
      return new DaySchedule(aDate, aStartTime, aEndTime, aIsHoliday, aIsBreak, aCarShop, this);
    }
  }

  public boolean addDaySchedule(DaySchedule aDaySchedule)
  {
    boolean wasAdded = false;
    if (daySchedule.contains(aDaySchedule)) { return false; }
    if (numberOfDaySchedule() >= maximumNumberOfDaySchedule())
    {
      return wasAdded;
    }

    WeeklySchedule existingWeeklySchedule = aDaySchedule.getWeeklySchedule();
    boolean isNewWeeklySchedule = existingWeeklySchedule != null && !this.equals(existingWeeklySchedule);

    if (isNewWeeklySchedule && existingWeeklySchedule.numberOfDaySchedule() <= minimumNumberOfDaySchedule())
    {
      return wasAdded;
    }

    if (isNewWeeklySchedule)
    {
      aDaySchedule.setWeeklySchedule(this);
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
    //Unable to remove aDaySchedule, as it must always have a weeklySchedule
    if (this.equals(aDaySchedule.getWeeklySchedule()))
    {
      return wasRemoved;
    }

    //weeklySchedule already at minimum (7)
    if (numberOfDaySchedule() <= minimumNumberOfDaySchedule())
    {
      return wasRemoved;
    }
    daySchedule.remove(aDaySchedule);
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
      existingCarShop.removeWeeklySchedule(this);
    }
    carShop.addWeeklySchedule(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCarShopInfo(CarShopInfo aCarShopInfo)
  {
    boolean wasSet = false;
    if (aCarShopInfo == null)
    {
      return wasSet;
    }

    CarShopInfo existingCarShopInfo = carShopInfo;
    carShopInfo = aCarShopInfo;
    if (existingCarShopInfo != null && !existingCarShopInfo.equals(aCarShopInfo))
    {
      existingCarShopInfo.removeWeeklySchedule(this);
    }
    carShopInfo.addWeeklySchedule(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    weeklyschedulesByWeekStart.remove(getWeekStart());
    weeklyschedulesByWeekEnd.remove(getWeekEnd());
    for(int i=daySchedule.size(); i > 0; i--)
    {
      DaySchedule aDaySchedule = daySchedule.get(i - 1);
      aDaySchedule.delete();
    }
    CarShop placeholderCarShop = carShop;
    this.carShop = null;
    if(placeholderCarShop != null)
    {
      placeholderCarShop.removeWeeklySchedule(this);
    }
    CarShopInfo placeholderCarShopInfo = carShopInfo;
    this.carShopInfo = null;
    if(placeholderCarShopInfo != null)
    {
      placeholderCarShopInfo.removeWeeklySchedule(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "weekStart" + "=" + (getWeekStart() != null ? !getWeekStart().equals(this)  ? getWeekStart().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "weekEnd" + "=" + (getWeekEnd() != null ? !getWeekEnd().equals(this)  ? getWeekEnd().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "carShop = "+(getCarShop()!=null?Integer.toHexString(System.identityHashCode(getCarShop())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "carShopInfo = "+(getCarShopInfo()!=null?Integer.toHexString(System.identityHashCode(getCarShopInfo())):"null");
  }
}