/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.util.*;

// line 108 "../../../../../carshop.ump"
public class ServiceAppointment extends Appointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ServiceAppointment Associations
  private Service service;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ServiceAppointment(CarShop aCarShop, ClientAccount aClientAccount, Service aService)
  {
    super(aCarShop, aClientAccount);
    boolean didAddService = setService(aService);
    if (!didAddService)
    {
      throw new RuntimeException("Unable to create service_appointment due to service. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Service getService()
  {
    return service;
  }
  /* Code from template association_SetOneToMany */
  public boolean setService(Service aService)
  {
    boolean wasSet = false;
    if (aService == null)
    {
      return wasSet;
    }

    Service existingService = service;
    service = aService;
    if (existingService != null && !existingService.equals(aService))
    {
      existingService.removeService_appointment(this);
    }
    service.addService_appointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Service placeholderService = service;
    this.service = null;
    if(placeholderService != null)
    {
      placeholderService.removeService_appointment(this);
    }
    super.delete();
  }

}