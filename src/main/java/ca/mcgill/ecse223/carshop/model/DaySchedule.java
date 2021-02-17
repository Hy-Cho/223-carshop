/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.sql.Date;

// line 56 "../../../../../carshop.ump"
public class DaySchedule
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DaySchedule Attributes
  private Date date;
  private int startTime;
  private int endTime;
  private boolean isHoliday;
  private boolean isBreak;

  //DaySchedule Associations
  private CarShop carShop;
  private WeeklySchedule weekly_schedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DaySchedule(Date aDate, int aStartTime, int aEndTime, boolean aIsHoliday, boolean aIsBreak, CarShop aCarShop, WeeklySchedule aWeekly_schedule)
  {
    date = aDate;
    startTime = aStartTime;
    endTime = aEndTime;
    isHoliday = aIsHoliday;
    isBreak = aIsBreak;
    boolean didAddCarShop = setCarShop(aCarShop);
    if (!didAddCarShop)
    {
      throw new RuntimeException("Unable to create daySchedule due to carShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddWeekly_schedule = setWeekly_schedule(aWeekly_schedule);
    if (!didAddWeekly_schedule)
    {
      throw new RuntimeException("Unable to create days_schedule due to weekly_schedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

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

  public boolean setIsHoliday(boolean aIsHoliday)
  {
    boolean wasSet = false;
    isHoliday = aIsHoliday;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsBreak(boolean aIsBreak)
  {
    boolean wasSet = false;
    isBreak = aIsBreak;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public int getStartTime()
  {
    return startTime;
  }

  public int getEndTime()
  {
    return endTime;
  }

  public boolean getIsHoliday()
  {
    return isHoliday;
  }

  public boolean getIsBreak()
  {
    return isBreak;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsHoliday()
  {
    return isHoliday;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsBreak()
  {
    return isBreak;
  }
  /* Code from template association_GetOne */
  public CarShop getCarShop()
  {
    return carShop;
  }
  /* Code from template association_GetOne */
  public WeeklySchedule getWeekly_schedule()
  {
    return weekly_schedule;
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
      existingCarShop.removeDaySchedule(this);
    }
    carShop.addDaySchedule(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setWeekly_schedule(WeeklySchedule aWeekly_schedule)
  {
    boolean wasSet = false;
    //Must provide weekly_schedule to days_schedule
    if (aWeekly_schedule == null)
    {
      return wasSet;
    }

    //weekly_schedule already at maximum (7)
    if (aWeekly_schedule.numberOfDays_schedule() >= WeeklySchedule.maximumNumberOfDays_schedule())
    {
      return wasSet;
    }
    
    WeeklySchedule existingWeekly_schedule = weekly_schedule;
    weekly_schedule = aWeekly_schedule;
    if (existingWeekly_schedule != null && !existingWeekly_schedule.equals(aWeekly_schedule))
    {
      boolean didRemove = existingWeekly_schedule.removeDays_schedule(this);
      if (!didRemove)
      {
        weekly_schedule = existingWeekly_schedule;
        return wasSet;
      }
    }
    weekly_schedule.addDays_schedule(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    CarShop placeholderCarShop = carShop;
    this.carShop = null;
    if(placeholderCarShop != null)
    {
      placeholderCarShop.removeDaySchedule(this);
    }
    WeeklySchedule placeholderWeekly_schedule = weekly_schedule;
    this.weekly_schedule = null;
    if(placeholderWeekly_schedule != null)
    {
      placeholderWeekly_schedule.removeDays_schedule(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "startTime" + ":" + getStartTime()+ "," +
            "endTime" + ":" + getEndTime()+ "," +
            "isHoliday" + ":" + getIsHoliday()+ "," +
            "isBreak" + ":" + getIsBreak()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "carShop = "+(getCarShop()!=null?Integer.toHexString(System.identityHashCode(getCarShop())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "weekly_schedule = "+(getWeekly_schedule()!=null?Integer.toHexString(System.identityHashCode(getWeekly_schedule())):"null");
  }
}