/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.util.*;

// line 89 "../../../../../carshop.ump"
public class ServiceCombo
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum ComboType { RequiredCombo, OptionalCombo }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ServiceCombo Attributes
  private ComboType type;
  private String name;

  //ServiceCombo Associations
  private Service main_service;
  private List<Service> side_services;
  private CarShop carShop;
  private List<ComboAppointment> combo_appointment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ServiceCombo(ComboType aType, String aName, Service aMain_service, CarShop aCarShop)
  {
    type = aType;
    name = aName;
    if (!setMain_service(aMain_service))
    {
      throw new RuntimeException("Unable to create ServiceCombo due to aMain_service. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    side_services = new ArrayList<Service>();
    boolean didAddCarShop = setCarShop(aCarShop);
    if (!didAddCarShop)
    {
      throw new RuntimeException("Unable to create serviceCombo due to carShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    combo_appointment = new ArrayList<ComboAppointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(ComboType aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public ComboType getType()
  {
    return type;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetOne */
  public Service getMain_service()
  {
    return main_service;
  }
  /* Code from template association_GetMany */
  public Service getSide_service(int index)
  {
    Service aSide_service = side_services.get(index);
    return aSide_service;
  }

  public List<Service> getSide_services()
  {
    List<Service> newSide_services = Collections.unmodifiableList(side_services);
    return newSide_services;
  }

  public int numberOfSide_services()
  {
    int number = side_services.size();
    return number;
  }

  public boolean hasSide_services()
  {
    boolean has = side_services.size() > 0;
    return has;
  }

  public int indexOfSide_service(Service aSide_service)
  {
    int index = side_services.indexOf(aSide_service);
    return index;
  }
  /* Code from template association_GetOne */
  public CarShop getCarShop()
  {
    return carShop;
  }
  /* Code from template association_GetMany */
  public ComboAppointment getCombo_appointment(int index)
  {
    ComboAppointment aCombo_appointment = combo_appointment.get(index);
    return aCombo_appointment;
  }

  public List<ComboAppointment> getCombo_appointment()
  {
    List<ComboAppointment> newCombo_appointment = Collections.unmodifiableList(combo_appointment);
    return newCombo_appointment;
  }

  public int numberOfCombo_appointment()
  {
    int number = combo_appointment.size();
    return number;
  }

  public boolean hasCombo_appointment()
  {
    boolean has = combo_appointment.size() > 0;
    return has;
  }

  public int indexOfCombo_appointment(ComboAppointment aCombo_appointment)
  {
    int index = combo_appointment.indexOf(aCombo_appointment);
    return index;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setMain_service(Service aNewMain_service)
  {
    boolean wasSet = false;
    if (aNewMain_service != null)
    {
      main_service = aNewMain_service;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSide_services()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addSide_service(Service aSide_service)
  {
    boolean wasAdded = false;
    if (side_services.contains(aSide_service)) { return false; }
    side_services.add(aSide_service);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSide_service(Service aSide_service)
  {
    boolean wasRemoved = false;
    if (side_services.contains(aSide_service))
    {
      side_services.remove(aSide_service);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSide_serviceAt(Service aSide_service, int index)
  {  
    boolean wasAdded = false;
    if(addSide_service(aSide_service))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSide_services()) { index = numberOfSide_services() - 1; }
      side_services.remove(aSide_service);
      side_services.add(index, aSide_service);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSide_serviceAt(Service aSide_service, int index)
  {
    boolean wasAdded = false;
    if(side_services.contains(aSide_service))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSide_services()) { index = numberOfSide_services() - 1; }
      side_services.remove(aSide_service);
      side_services.add(index, aSide_service);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSide_serviceAt(aSide_service, index);
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
      existingCarShop.removeServiceCombo(this);
    }
    carShop.addServiceCombo(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCombo_appointment()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ComboAppointment addCombo_appointment(CarShop aCarShop, ClientAccount aClientAccount)
  {
    return new ComboAppointment(aCarShop, aClientAccount, this);
  }

  public boolean addCombo_appointment(ComboAppointment aCombo_appointment)
  {
    boolean wasAdded = false;
    if (combo_appointment.contains(aCombo_appointment)) { return false; }
    ServiceCombo existingService_combo = aCombo_appointment.getService_combo();
    boolean isNewService_combo = existingService_combo != null && !this.equals(existingService_combo);
    if (isNewService_combo)
    {
      aCombo_appointment.setService_combo(this);
    }
    else
    {
      combo_appointment.add(aCombo_appointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCombo_appointment(ComboAppointment aCombo_appointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aCombo_appointment, as it must always have a service_combo
    if (!this.equals(aCombo_appointment.getService_combo()))
    {
      combo_appointment.remove(aCombo_appointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCombo_appointmentAt(ComboAppointment aCombo_appointment, int index)
  {  
    boolean wasAdded = false;
    if(addCombo_appointment(aCombo_appointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCombo_appointment()) { index = numberOfCombo_appointment() - 1; }
      combo_appointment.remove(aCombo_appointment);
      combo_appointment.add(index, aCombo_appointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCombo_appointmentAt(ComboAppointment aCombo_appointment, int index)
  {
    boolean wasAdded = false;
    if(combo_appointment.contains(aCombo_appointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCombo_appointment()) { index = numberOfCombo_appointment() - 1; }
      combo_appointment.remove(aCombo_appointment);
      combo_appointment.add(index, aCombo_appointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCombo_appointmentAt(aCombo_appointment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    main_service = null;
    side_services.clear();
    CarShop placeholderCarShop = carShop;
    this.carShop = null;
    if(placeholderCarShop != null)
    {
      placeholderCarShop.removeServiceCombo(this);
    }
    for(int i=combo_appointment.size(); i > 0; i--)
    {
      ComboAppointment aCombo_appointment = combo_appointment.get(i - 1);
      aCombo_appointment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "main_service = "+(getMain_service()!=null?Integer.toHexString(System.identityHashCode(getMain_service())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "carShop = "+(getCarShop()!=null?Integer.toHexString(System.identityHashCode(getCarShop())):"null");
  }
}