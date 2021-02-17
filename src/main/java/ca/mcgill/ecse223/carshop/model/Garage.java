/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import ca.mcgill.ecse223.carshop.model.TechnicianAccount.TechnicianType;
import java.util.*;
import java.sql.Date;

// line 64 "../../../../../carshop.ump"
public class Garage
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Garage Associations
  private List<GarageAgenda> daily_agendas;
  private List<Service> services;
  private CarShop carShop;
  private TechnicianAccount technician;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Garage(CarShop aCarShop, TechnicianAccount aTechnician)
  {
    daily_agendas = new ArrayList<GarageAgenda>();
    services = new ArrayList<Service>();
    boolean didAddCarShop = setCarShop(aCarShop);
    if (!didAddCarShop)
    {
      throw new RuntimeException("Unable to create garage due to carShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aTechnician == null || aTechnician.getGarage() != null)
    {
      throw new RuntimeException("Unable to create Garage due to aTechnician. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    technician = aTechnician;
  }

  public Garage(CarShop aCarShop, String aUsernameForTechnician, String aPasswordForTechnician, CarShop aCarShopForTechnician, TechnicianType aTechnicianTypeForTechnician)
  {
    daily_agendas = new ArrayList<GarageAgenda>();
    services = new ArrayList<Service>();
    boolean didAddCarShop = setCarShop(aCarShop);
    if (!didAddCarShop)
    {
      throw new RuntimeException("Unable to create garage due to carShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    technician = new TechnicianAccount(aUsernameForTechnician, aPasswordForTechnician, aCarShopForTechnician, aTechnicianTypeForTechnician, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public GarageAgenda getDaily_agenda(int index)
  {
    GarageAgenda aDaily_agenda = daily_agendas.get(index);
    return aDaily_agenda;
  }

  public List<GarageAgenda> getDaily_agendas()
  {
    List<GarageAgenda> newDaily_agendas = Collections.unmodifiableList(daily_agendas);
    return newDaily_agendas;
  }

  public int numberOfDaily_agendas()
  {
    int number = daily_agendas.size();
    return number;
  }

  public boolean hasDaily_agendas()
  {
    boolean has = daily_agendas.size() > 0;
    return has;
  }

  public int indexOfDaily_agenda(GarageAgenda aDaily_agenda)
  {
    int index = daily_agendas.indexOf(aDaily_agenda);
    return index;
  }
  /* Code from template association_GetMany */
  public Service getService(int index)
  {
    Service aService = services.get(index);
    return aService;
  }

  public List<Service> getServices()
  {
    List<Service> newServices = Collections.unmodifiableList(services);
    return newServices;
  }

  public int numberOfServices()
  {
    int number = services.size();
    return number;
  }

  public boolean hasServices()
  {
    boolean has = services.size() > 0;
    return has;
  }

  public int indexOfService(Service aService)
  {
    int index = services.indexOf(aService);
    return index;
  }
  /* Code from template association_GetOne */
  public CarShop getCarShop()
  {
    return carShop;
  }
  /* Code from template association_GetOne */
  public TechnicianAccount getTechnician()
  {
    return technician;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDaily_agendas()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public GarageAgenda addDaily_agenda(Date aDate, int aOpeningTime, int aClosingTime, CarShop aCarShop)
  {
    return new GarageAgenda(aDate, aOpeningTime, aClosingTime, aCarShop, this);
  }

  public boolean addDaily_agenda(GarageAgenda aDaily_agenda)
  {
    boolean wasAdded = false;
    if (daily_agendas.contains(aDaily_agenda)) { return false; }
    Garage existingGarage = aDaily_agenda.getGarage();
    boolean isNewGarage = existingGarage != null && !this.equals(existingGarage);
    if (isNewGarage)
    {
      aDaily_agenda.setGarage(this);
    }
    else
    {
      daily_agendas.add(aDaily_agenda);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDaily_agenda(GarageAgenda aDaily_agenda)
  {
    boolean wasRemoved = false;
    //Unable to remove aDaily_agenda, as it must always have a garage
    if (!this.equals(aDaily_agenda.getGarage()))
    {
      daily_agendas.remove(aDaily_agenda);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDaily_agendaAt(GarageAgenda aDaily_agenda, int index)
  {  
    boolean wasAdded = false;
    if(addDaily_agenda(aDaily_agenda))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDaily_agendas()) { index = numberOfDaily_agendas() - 1; }
      daily_agendas.remove(aDaily_agenda);
      daily_agendas.add(index, aDaily_agenda);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDaily_agendaAt(GarageAgenda aDaily_agenda, int index)
  {
    boolean wasAdded = false;
    if(daily_agendas.contains(aDaily_agenda))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDaily_agendas()) { index = numberOfDaily_agendas() - 1; }
      daily_agendas.remove(aDaily_agenda);
      daily_agendas.add(index, aDaily_agenda);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDaily_agendaAt(aDaily_agenda, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Service addService(String aName, int aDuration, CarShop aCarShop)
  {
    return new Service(aName, aDuration, aCarShop, this);
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    Garage existingGarage = aService.getGarage();
    boolean isNewGarage = existingGarage != null && !this.equals(existingGarage);
    if (isNewGarage)
    {
      aService.setGarage(this);
    }
    else
    {
      services.add(aService);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeService(Service aService)
  {
    boolean wasRemoved = false;
    //Unable to remove aService, as it must always have a garage
    if (!this.equals(aService.getGarage()))
    {
      services.remove(aService);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceAt(Service aService, int index)
  {  
    boolean wasAdded = false;
    if(addService(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceAt(Service aService, int index)
  {
    boolean wasAdded = false;
    if(services.contains(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceAt(aService, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setCarShop(CarShop aCarShop)
  {
    boolean wasSet = false;
    //Must provide carShop to garage
    if (aCarShop == null)
    {
      return wasSet;
    }

    //carShop already at maximum (5)
    if (aCarShop.numberOfGarages() >= CarShop.maximumNumberOfGarages())
    {
      return wasSet;
    }
    
    CarShop existingCarShop = carShop;
    carShop = aCarShop;
    if (existingCarShop != null && !existingCarShop.equals(aCarShop))
    {
      boolean didRemove = existingCarShop.removeGarage(this);
      if (!didRemove)
      {
        carShop = existingCarShop;
        return wasSet;
      }
    }
    carShop.addGarage(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=daily_agendas.size(); i > 0; i--)
    {
      GarageAgenda aDaily_agenda = daily_agendas.get(i - 1);
      aDaily_agenda.delete();
    }
    for(int i=services.size(); i > 0; i--)
    {
      Service aService = services.get(i - 1);
      aService.delete();
    }
    CarShop placeholderCarShop = carShop;
    this.carShop = null;
    if(placeholderCarShop != null)
    {
      placeholderCarShop.removeGarage(this);
    }
    TechnicianAccount existingTechnician = technician;
    technician = null;
    if (existingTechnician != null)
    {
      existingTechnician.delete();
    }
  }

}