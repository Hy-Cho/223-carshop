/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;

// line 21 "carshop.ump"
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

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TechnicianAccount(String aUsername, String aPassword, CarShop aCarShop, TechnicianType aTechnicianType)
  {
    super(aUsername, aPassword, aCarShop);
    technicianType = aTechnicianType;
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

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "technicianType" + "=" + (getTechnicianType() != null ? !getTechnicianType().equals(this)  ? getTechnicianType().toString().replaceAll("  ","    ") : "this" : "null");
  }
}