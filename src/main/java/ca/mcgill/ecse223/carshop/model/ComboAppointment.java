/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.util.*;

// line 101 "../../../../../carshop.ump"
public class ComboAppointment extends Appointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ComboAppointment Associations
  private ServiceCombo service_combo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ComboAppointment(CarShop aCarShop, ClientAccount aClientAccount, ServiceCombo aService_combo)
  {
    super(aCarShop, aClientAccount);
    boolean didAddService_combo = setService_combo(aService_combo);
    if (!didAddService_combo)
    {
      throw new RuntimeException("Unable to create combo_appointment due to service_combo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public ServiceCombo getService_combo()
  {
    return service_combo;
  }
  /* Code from template association_SetOneToMany */
  public boolean setService_combo(ServiceCombo aService_combo)
  {
    boolean wasSet = false;
    if (aService_combo == null)
    {
      return wasSet;
    }

    ServiceCombo existingService_combo = service_combo;
    service_combo = aService_combo;
    if (existingService_combo != null && !existingService_combo.equals(aService_combo))
    {
      existingService_combo.removeCombo_appointment(this);
    }
    service_combo.addCombo_appointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ServiceCombo placeholderService_combo = service_combo;
    this.service_combo = null;
    if(placeholderService_combo != null)
    {
      placeholderService_combo.removeCombo_appointment(this);
    }
    super.delete();
  }

}