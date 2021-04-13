/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.carshop.controller;

// line 10 "../../../../../CarShopTransferObjects.ump"
public class TOGarage
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOGarage Attributes
  private String technicianUsername;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOGarage(String aTechnicianUsername)
  {
    technicianUsername = aTechnicianUsername;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTechnicianUsername(String aTechnicianUsername)
  {
    boolean wasSet = false;
    technicianUsername = aTechnicianUsername;
    wasSet = true;
    return wasSet;
  }

  public String getTechnicianUsername()
  {
    return technicianUsername;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "technicianUsername" + ":" + getTechnicianUsername()+ "]";
  }
}