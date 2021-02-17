/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.util.*;

// line 32 "../../../../../carshop.ump"
public class TechnicianAccount extends UserAccount
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TechnicianType { TiresTechnician, EngineTechnician, TransmissionTechnician, ElectronicsTechnician, FluidsTechnician }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TechnicianAccount Attributes
  private TechnicianType technicianType;

  //TechnicianAccount Associations
  private Garage garage;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TechnicianAccount(String aUsername, String aPassword, CarShop aCarShop, TechnicianType aTechnicianType, Garage aGarage)
  {
    super(aUsername, aPassword, aCarShop);
    technicianType = aTechnicianType;
    if (aGarage == null || aGarage.getTechnician() != null)
    {
      throw new RuntimeException("Unable to create TechnicianAccount due to aGarage. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    garage = aGarage;
  }

  public TechnicianAccount(String aUsername, String aPassword, CarShop aCarShop, TechnicianType aTechnicianType, CarShop aCarShopForGarage)
  {
    super(aUsername, aPassword, aCarShop);
    technicianType = aTechnicianType;
    garage = new Garage(aCarShopForGarage, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTechnicianType(TechnicianType aTechnicianType)
  {
    boolean wasSet = false;
    technicianType = aTechnicianType;
    wasSet = true;
    return wasSet;
  }

  public TechnicianType getTechnicianType()
  {
    return technicianType;
  }
  /* Code from template association_GetOne */
  public Garage getGarage()
  {
    return garage;
  }

  public void delete()
  {
    Garage existingGarage = garage;
    garage = null;
    if (existingGarage != null)
    {
      existingGarage.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "technicianType" + "=" + (getTechnicianType() != null ? !getTechnicianType().equals(this)  ? getTechnicianType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "garage = "+(getGarage()!=null?Integer.toHexString(System.identityHashCode(getGarage())):"null");
  }
}