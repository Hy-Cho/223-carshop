/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/
package ca.mcgill.ecse223.carshop.model;


// line 26 "carshop.ump"
public class Address
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Address Attributes
  private String streetname;
  private int streetNumber;
  private String postalCode;
  private String city;

  //Address Associations
  private CarShop carShop;
  private CarShopInfo carShopInfo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address(String aStreetname, int aStreetNumber, String aPostalCode, String aCity, CarShop aCarShop, CarShopInfo aCarShopInfo)
  {
    streetname = aStreetname;
    streetNumber = aStreetNumber;
    postalCode = aPostalCode;
    city = aCity;
    if (aCarShop == null || aCarShop.getAddress() != null)
    {
      throw new RuntimeException("Unable to create Address due to aCarShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    carShop = aCarShop;
    if (aCarShopInfo == null || aCarShopInfo.getAddress() != null)
    {
      throw new RuntimeException("Unable to create Address due to aCarShopInfo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    carShopInfo = aCarShopInfo;
  }

  public Address(String aStreetname, int aStreetNumber, String aPostalCode, String aCity, CarShopInfo aCarShopInfoForCarShop, String aPhoneNumberForCarShopInfo, String aEmailAddressForCarShopInfo, CarShop aCarShopForCarShopInfo, OwnerAccount aOwnerAccountForCarShopInfo)
  {
    streetname = aStreetname;
    streetNumber = aStreetNumber;
    postalCode = aPostalCode;
    city = aCity;
    carShop = new CarShop(this, aCarShopInfoForCarShop);
    carShopInfo = new CarShopInfo(aPhoneNumberForCarShopInfo, aEmailAddressForCarShopInfo, this, aCarShopForCarShopInfo, aOwnerAccountForCarShopInfo);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStreetname(String aStreetname)
  {
    boolean wasSet = false;
    streetname = aStreetname;
    wasSet = true;
    return wasSet;
  }

  public boolean setStreetNumber(int aStreetNumber)
  {
    boolean wasSet = false;
    streetNumber = aStreetNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setPostalCode(String aPostalCode)
  {
    boolean wasSet = false;
    postalCode = aPostalCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

  public String getStreetname()
  {
    return streetname;
  }

  public int getStreetNumber()
  {
    return streetNumber;
  }

  public String getPostalCode()
  {
    return postalCode;
  }

  public String getCity()
  {
    return city;
  }
  /* Code from template association_GetOne */
  public CarShop getCarShop()
  {
    return carShop;
  }
  /* Code from template association_GetOne */
  public CarShopInfo getCarShopInfo()
  {
    return carShopInfo;
  }

  public void delete()
  {
    CarShop existingCarShop = carShop;
    carShop = null;
    if (existingCarShop != null)
    {
      existingCarShop.delete();
    }
    CarShopInfo existingCarShopInfo = carShopInfo;
    carShopInfo = null;
    if (existingCarShopInfo != null)
    {
      existingCarShopInfo.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "streetname" + ":" + getStreetname()+ "," +
            "streetNumber" + ":" + getStreetNumber()+ "," +
            "postalCode" + ":" + getPostalCode()+ "," +
            "city" + ":" + getCity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "carShop = "+(getCarShop()!=null?Integer.toHexString(System.identityHashCode(getCarShop())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "carShopInfo = "+(getCarShopInfo()!=null?Integer.toHexString(System.identityHashCode(getCarShopInfo())):"null");
  }
}