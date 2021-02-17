/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;

// line 16 "carshop.ump"
public class OwnerAccount extends UserAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OwnerAccount Associations
  private CarShopInfo carShopInfo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OwnerAccount(String aUsername, String aPassword, CarShop aCarShop, CarShopInfo aCarShopInfo)
  {
    super(aUsername, aPassword, aCarShop);
    if (aCarShopInfo == null || aCarShopInfo.getOwnerAccount() != null)
    {
      throw new RuntimeException("Unable to create OwnerAccount due to aCarShopInfo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    carShopInfo = aCarShopInfo;
  }

  public OwnerAccount(String aUsername, String aPassword, CarShop aCarShop, String aPhoneNumberForCarShopInfo, String aEmailAddressForCarShopInfo, Address aAddressForCarShopInfo, CarShop aCarShopForCarShopInfo)
  {
    super(aUsername, aPassword, aCarShop);
    carShopInfo = new CarShopInfo(aPhoneNumberForCarShopInfo, aEmailAddressForCarShopInfo, aAddressForCarShopInfo, aCarShopForCarShopInfo, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public CarShopInfo getCarShopInfo()
  {
    return carShopInfo;
  }

  public void delete()
  {
    CarShopInfo existingCarShopInfo = carShopInfo;
    carShopInfo = null;
    if (existingCarShopInfo != null)
    {
      existingCarShopInfo.delete();
    }
    super.delete();
  }

}