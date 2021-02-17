/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.util.*;

// line 82 "../../../../../carshop.ump"
public class Service
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  private String name;
  private int duration;

  //Service Associations
  private CarShop carShop;
  private Garage garage;
  private List<ServiceAppointment> service_appointment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(String aName, int aDuration, CarShop aCarShop, Garage aGarage)
  {
    name = aName;
    duration = aDuration;
    boolean didAddCarShop = setCarShop(aCarShop);
    if (!didAddCarShop)
    {
      throw new RuntimeException("Unable to create service due to carShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGarage = setGarage(aGarage);
    if (!didAddGarage)
    {
      throw new RuntimeException("Unable to create service due to garage. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    service_appointment = new ArrayList<ServiceAppointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getDuration()
  {
    return duration;
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
  /* Code from template association_GetMany */
  public ServiceAppointment getService_appointment(int index)
  {
    ServiceAppointment aService_appointment = service_appointment.get(index);
    return aService_appointment;
  }

  public List<ServiceAppointment> getService_appointment()
  {
    List<ServiceAppointment> newService_appointment = Collections.unmodifiableList(service_appointment);
    return newService_appointment;
  }

  public int numberOfService_appointment()
  {
    int number = service_appointment.size();
    return number;
  }

  public boolean hasService_appointment()
  {
    boolean has = service_appointment.size() > 0;
    return has;
  }

  public int indexOfService_appointment(ServiceAppointment aService_appointment)
  {
    int index = service_appointment.indexOf(aService_appointment);
    return index;
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
      existingCarShop.removeService(this);
    }
    carShop.addService(this);
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
      existingGarage.removeService(this);
    }
    garage.addService(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfService_appointment()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ServiceAppointment addService_appointment(CarShop aCarShop, ClientAccount aClientAccount)
  {
    return new ServiceAppointment(aCarShop, aClientAccount, this);
  }

  public boolean addService_appointment(ServiceAppointment aService_appointment)
  {
    boolean wasAdded = false;
    if (service_appointment.contains(aService_appointment)) { return false; }
    Service existingService = aService_appointment.getService();
    boolean isNewService = existingService != null && !this.equals(existingService);
    if (isNewService)
    {
      aService_appointment.setService(this);
    }
    else
    {
      service_appointment.add(aService_appointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeService_appointment(ServiceAppointment aService_appointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aService_appointment, as it must always have a service
    if (!this.equals(aService_appointment.getService()))
    {
      service_appointment.remove(aService_appointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addService_appointmentAt(ServiceAppointment aService_appointment, int index)
  {  
    boolean wasAdded = false;
    if(addService_appointment(aService_appointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfService_appointment()) { index = numberOfService_appointment() - 1; }
      service_appointment.remove(aService_appointment);
      service_appointment.add(index, aService_appointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveService_appointmentAt(ServiceAppointment aService_appointment, int index)
  {
    boolean wasAdded = false;
    if(service_appointment.contains(aService_appointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfService_appointment()) { index = numberOfService_appointment() - 1; }
      service_appointment.remove(aService_appointment);
      service_appointment.add(index, aService_appointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addService_appointmentAt(aService_appointment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    CarShop placeholderCarShop = carShop;
    this.carShop = null;
    if(placeholderCarShop != null)
    {
      placeholderCarShop.removeService(this);
    }
    Garage placeholderGarage = garage;
    this.garage = null;
    if(placeholderGarage != null)
    {
      placeholderGarage.removeService(this);
    }
    for(int i=service_appointment.size(); i > 0; i--)
    {
      ServiceAppointment aService_appointment = service_appointment.get(i - 1);
      aService_appointment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "carShop = "+(getCarShop()!=null?Integer.toHexString(System.identityHashCode(getCarShop())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "garage = "+(getGarage()!=null?Integer.toHexString(System.identityHashCode(getGarage())):"null");
  }
}