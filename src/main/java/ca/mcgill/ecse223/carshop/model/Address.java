/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;

// line 39 "../../../../../carshop.ump"
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
  private CarShopInfo car_shop;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address(String aStreetname, int aStreetNumber, String aPostalCode, String aCity, CarShop aCarShop, CarShopInfo aCar_shop)
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
    if (aCar_shop == null || aCar_shop.getAddress() != null)
    {
      throw new RuntimeException("Unable to create Address due to aCar_shop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    car_shop = aCar_shop;
  }

  public Address(String aStreetname, int aStreetNumber, String aPostalCode, String aCity, CarShopInfo aCarShopInfoForCarShop, String aPhoneNumberForCar_shop, String aEmailAddressForCar_shop, CarShop aCarShopForCar_shop, OwnerAccount aOwnerAccountForCar_shop)
  {
    streetname = aStreetname;
    streetNumber = aStreetNumber;
    postalCode = aPostalCode;
    city = aCity;
    carShop = new CarShop(this, aCarShopInfoForCarShop);
    car_shop = new CarShopInfo(aPhoneNumberForCar_shop, aEmailAddressForCar_shop, this, aCarShopForCar_shop, aOwnerAccountForCar_shop);
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
  public CarShopInfo getCar_shop()
  {
    return car_shop;
  }

  public void delete()
  {
    CarShop existingCarShop = carShop;
    carShop = null;
    if (existingCarShop != null)
    {
      existingCarShop.delete();
    }
    CarShopInfo existingCar_shop = car_shop;
    car_shop = null;
    if (existingCar_shop != null)
    {
      existingCar_shop.delete();
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
            "  " + "car_shop = "+(getCar_shop()!=null?Integer.toHexString(System.identityHashCode(getCar_shop())):"null");
  }
}