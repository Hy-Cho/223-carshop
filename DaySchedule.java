/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/
package ca.mcgill.ecse223.carshop.model;

import java.sql.Date;

// line 43 "carshop.ump"
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
  private WeeklySchedule weeklySchedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DaySchedule(Date aDate, int aStartTime, int aEndTime, boolean aIsHoliday, boolean aIsBreak, CarShop aCarShop, WeeklySchedule aWeeklySchedule)
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
    boolean didAddWeeklySchedule = setWeeklySchedule(aWeeklySchedule);
    if (!didAddWeeklySchedule)
    {
      throw new RuntimeException("Unable to create daySchedule due to weeklySchedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  public WeeklySchedule getWeeklySchedule()
  {
    return weeklySchedule;
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
  public boolean setWeeklySchedule(WeeklySchedule aWeeklySchedule)
  {
    boolean wasSet = false;
    //Must provide weeklySchedule to daySchedule
    if (aWeeklySchedule == null)
    {
      return wasSet;
    }

    //weeklySchedule already at maximum (7)
    if (aWeeklySchedule.numberOfDaySchedule() >= WeeklySchedule.maximumNumberOfDaySchedule())
    {
      return wasSet;
    }
    
    WeeklySchedule existingWeeklySchedule = weeklySchedule;
    weeklySchedule = aWeeklySchedule;
    if (existingWeeklySchedule != null && !existingWeeklySchedule.equals(aWeeklySchedule))
    {
      boolean didRemove = existingWeeklySchedule.removeDaySchedule(this);
      if (!didRemove)
      {
        weeklySchedule = existingWeeklySchedule;
        return wasSet;
      }
    }
    weeklySchedule.addDaySchedule(this);
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
    WeeklySchedule placeholderWeeklySchedule = weeklySchedule;
    this.weeklySchedule = null;
    if(placeholderWeeklySchedule != null)
    {
      placeholderWeeklySchedule.removeDaySchedule(this);
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
            "  " + "weeklySchedule = "+(getWeeklySchedule()!=null?Integer.toHexString(System.identityHashCode(getWeeklySchedule())):"null");
  }
}