/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.carshop.model;
import java.util.*;

// line 22 "../../../../../carshop.ump"
public class ClientAccount extends UserAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClientAccount Associations
  private List<Appointment> client_appointments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClientAccount(String aUsername, String aPassword, CarShop aCarShop)
  {
    super(aUsername, aPassword, aCarShop);
    client_appointments = new ArrayList<Appointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Appointment getClient_appointment(int index)
  {
    Appointment aClient_appointment = client_appointments.get(index);
    return aClient_appointment;
  }

  public List<Appointment> getClient_appointments()
  {
    List<Appointment> newClient_appointments = Collections.unmodifiableList(client_appointments);
    return newClient_appointments;
  }

  public int numberOfClient_appointments()
  {
    int number = client_appointments.size();
    return number;
  }

  public boolean hasClient_appointments()
  {
    boolean has = client_appointments.size() > 0;
    return has;
  }

  public int indexOfClient_appointment(Appointment aClient_appointment)
  {
    int index = client_appointments.indexOf(aClient_appointment);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClient_appointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addClient_appointment(Appointment aClient_appointment)
  {
    boolean wasAdded = false;
    if (client_appointments.contains(aClient_appointment)) { return false; }
    ClientAccount existingClientAccount = aClient_appointment.getClientAccount();
    boolean isNewClientAccount = existingClientAccount != null && !this.equals(existingClientAccount);
    if (isNewClientAccount)
    {
      aClient_appointment.setClientAccount(this);
    }
    else
    {
      client_appointments.add(aClient_appointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeClient_appointment(Appointment aClient_appointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aClient_appointment, as it must always have a clientAccount
    if (!this.equals(aClient_appointment.getClientAccount()))
    {
      client_appointments.remove(aClient_appointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClient_appointmentAt(Appointment aClient_appointment, int index)
  {  
    boolean wasAdded = false;
    if(addClient_appointment(aClient_appointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClient_appointments()) { index = numberOfClient_appointments() - 1; }
      client_appointments.remove(aClient_appointment);
      client_appointments.add(index, aClient_appointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClient_appointmentAt(Appointment aClient_appointment, int index)
  {
    boolean wasAdded = false;
    if(client_appointments.contains(aClient_appointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClient_appointments()) { index = numberOfClient_appointments() - 1; }
      client_appointments.remove(aClient_appointment);
      client_appointments.add(index, aClient_appointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClient_appointmentAt(aClient_appointment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (client_appointments.size() > 0)
    {
      Appointment aClient_appointment = client_appointments.get(client_appointments.size() - 1);
      aClient_appointment.delete();
      client_appointments.remove(aClient_appointment);
    }
    
    super.delete();
  }

}