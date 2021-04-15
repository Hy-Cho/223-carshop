package ca.mcgill.ecse.carshop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;
import ca.mcgill.ecse.carshop.controller.InvalidUserException;
import ca.mcgill.ecse.carshop.controller.TOAppointment;
import ca.mcgill.ecse.carshop.controller.TOBusiness;
import ca.mcgill.ecse.carshop.controller.TOGarage;
import ca.mcgill.ecse.carshop.controller.TOService;

public class OwnerPage extends JFrame {
	private static final long serialVersionUID = 1633915862703837868L;
	
	private JLabel errorMessage;
	private String error = null;
	
	//Business Info setter element
	private JTextField BNameTextField;
	private JTextField BAddressTextField;
	private JTextField BPhoneNumberTextField;
	private JTextField BEmailTextField;
	private JButton setBizInfoButton;
	private JLabel BNameLabel;
	private JLabel BAddressLabel;
	private JLabel BPhoneNumberLabel;
	private JLabel BEmailLabel;
	
	//Business Info displayer element
	private JLabel BInfoPresenter;
	private JLabel BNameDisplayerLabel;
	private JLabel BAddressDisplayerLabel;
	private JLabel BPhoneNumberDisplayerLabel;
	private JLabel BEmailDisplayerLabel;
	private JLabel BNameDisplayerField;
	private JLabel BAddressDisplayerField;
	private JLabel BPhoneNumberDisplayerField;
	private JLabel BEmailDisplayerField;
	
	//Handles the appointment management
	private JComboBox<String> appointmentList;
	private JLabel appointmentListLabel;
	private JButton startButton;
	private JButton endButton;
	private JButton registerNoShow;
	
	//View Appointment By Date
	private JTable overviewTable;
	private JScrollPane overviewScrollPane;
	
	//Change Date and Time
	private JLabel setTime;
	private JLabel setDate;
	private JTextField setTimeField;
	private JTextField setDateField;
	private JButton setTimeButton;
	private JButton setDateButton;
	
	//Add Service 
	private JLabel serviceNameLabel;
	private JTextField serviceNameTextField;
	private JLabel serviceDurationLabel;
	private JTextField serviceDurationTextField;
	private JComboBox<String> garageList;
	private JButton addServiceButton;
	
	// Book Combo
	private JTextField comboNameTextField;
	private JLabel comboNameLabel;
	private JComboBox<String> mainServiceList;
	private JLabel mainServiceLabel;
	private JTextField servicesTextField;
	private JLabel servicesLabel;
	private JTextField mandatoryTextField;
	private JLabel mandatoryLabel;
	private JButton defineComboButton;
	
	private JTable serviceTable;
	private JScrollPane serviceTableScrollPane;
	
	private static final int HEIGHT_SERVICE_TABLE = 100;
	private static final int HEIGHT_OVERVIEW_TABLE = 120;
	
	//Appointment Management
	private HashMap<Integer, TOAppointment> appointments;
	private HashMap<Integer, TOGarage> garages;
	private HashMap<Integer, String> existingServices;

	private DefaultTableModel overviewDtm;
	private String overviewColumnNames[] = {"Bookable", "Customer", "Date", "Services", "Start Times"};
	
	private DefaultTableModel serviceDtm;
	private String serviceColumnNames[] = {"Name", "Duration", "Garage"};
	
	public OwnerPage() {
		this.setPreferredSize(new Dimension(980, 720));
		
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.red);
		
		//elements for BusinessInfoSetter
		BNameTextField = new JTextField();
		BAddressTextField = new JTextField();
		BPhoneNumberTextField = new JTextField();
		BEmailTextField = new JTextField();
		BNameLabel = new JLabel();
		BNameLabel.setText("Name: ");
		BAddressLabel = new JLabel();
		BAddressLabel.setText("Address: ");
		BPhoneNumberLabel = new JLabel();
		BPhoneNumberLabel.setText("Phone Number: ");
		BEmailLabel = new JLabel();
		BEmailLabel.setText("Email: ");
		setBizInfoButton = new JButton();
		setBizInfoButton.setText("Set Business Information");
		
		//elements for BusinessInfoDisplayer
		BInfoPresenter=new JLabel();
		BInfoPresenter.setText("Business Information: ");
		BNameDisplayerLabel=new JLabel();
		BNameDisplayerLabel.setText("Name: ");
		BAddressDisplayerLabel=new JLabel();
		BAddressDisplayerLabel.setText("Address: ");
		BPhoneNumberDisplayerLabel=new JLabel();
		BPhoneNumberDisplayerLabel.setText("Phone Number: ");
		BEmailDisplayerLabel= new JLabel();
		BEmailDisplayerLabel.setText("Email: ");
		BNameDisplayerField= new JLabel();
		BNameDisplayerField.setText( "" );
		BAddressDisplayerField= new JLabel();
		BAddressDisplayerField.setText("");
		BPhoneNumberDisplayerField= new JLabel();
		BPhoneNumberDisplayerField.setText("");
		BEmailDisplayerField= new JLabel();
		BEmailDisplayerField.setText("");
		
		//Settings the time
		setTimeField = new JTextField();
		setDateField = new JTextField();
		
		//Elements for Owner Appointment Management.
		appointmentList = new JComboBox<>(new String[0]);
		appointmentListLabel = new JLabel("Appointments: ");
		
		startButton = new JButton();
		startButton.setText("Start Appointment");
		endButton = new JButton();
		endButton.setText("End Button");
		registerNoShow = new JButton();
		registerNoShow.setText("No Show");
		
		overviewTable = new JTable();
		overviewScrollPane = new JScrollPane(overviewTable);
		this.add(overviewScrollPane);
		Dimension d = overviewTable.getPreferredSize();
		overviewScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
		overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		// elements for add service
		serviceNameLabel = new JLabel();
		serviceNameLabel.setText("Name: ");
		serviceNameTextField = new JTextField();
		serviceDurationLabel = new JLabel();
		serviceDurationLabel.setText("Duration: ");
		serviceDurationTextField = new JTextField();
		garageList = new JComboBox<String>(new String[0]);
		addServiceButton = new JButton();
		addServiceButton.setText("Add Service");
		  
		serviceTable = new JTable();
		serviceTableScrollPane = new JScrollPane(serviceTable);
		this.add(serviceTableScrollPane);
		Dimension d1 = serviceTable.getPreferredSize();
		serviceTableScrollPane.setPreferredSize(new Dimension(d1.width, HEIGHT_SERVICE_TABLE));
		serviceTableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		// elements for combo booking
		comboNameTextField = new JTextField();
		comboNameLabel = new JLabel();
		comboNameLabel.setText("Enter Combo Name: ");
		
		mainServiceList = new JComboBox<String>(new String[0]);
		mainServiceLabel = new JLabel();
		mainServiceLabel.setText("Select Main Service: ");
		
		servicesTextField = new JTextField();
		servicesLabel = new JLabel();
		servicesLabel.setText("Enter Combo Services (separated by ','): ");
		
		mandatoryTextField = new JTextField();
		mandatoryLabel = new JLabel();
		mandatoryLabel.setText("Enter Mandatory Settings (separated by ','): ");
		
		defineComboButton = new JButton();
		defineComboButton.setText("Add New Service Combo");
	
		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Car Shop System");
		
		
		//add listener to setup business info
		setBizInfoButton.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		        SetUpBusinessInfoAccountButtonActionPerformed(evt);
		    }
		});
		
		startButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startButtonActionPerformed(evt);
			}
		});
		
		endButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				endButtonActionPerformed(evt);
			}
		});
		
		registerNoShow.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				registerNoShow(evt);
			}
		});
		
		// action listeners for service
		addServiceButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
	          addServiceButtonActionPerformed(evt);
	        }
	    });
		
		// listeners for define Combo
		defineComboButton.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		        defineComboButtonActionPerformed(evt);
		    }
		});
		
		JSeparator line1 = new JSeparator();
		JSeparator line2 = new JSeparator();
		JSeparator line3 = new JSeparator();
		JSeparator line4 = new JSeparator();
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
			.addComponent(errorMessage)
			.addComponent(line1)
			.addComponent(line2)
			.addComponent(line3)
			.addComponent(line4)
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(BNameLabel)
      				.addComponent(BAddressLabel)
      				.addComponent(BEmailLabel)
      				.addComponent(BPhoneNumberLabel)
					.addComponent(serviceNameLabel)
	                .addComponent(serviceDurationLabel)
	      		)
				.addGroup(layout.createParallelGroup()
					.addComponent(BNameTextField, 200, 200, 200)
      				.addComponent(BAddressTextField)
      				.addComponent(BEmailTextField)
      				.addComponent(BPhoneNumberTextField)
      				.addComponent(setBizInfoButton)
      				
					.addComponent(serviceNameTextField)
                    .addComponent(serviceDurationTextField)
                    .addComponent(garageList)
                    .addComponent(addServiceButton)	
	      		)
				.addGroup(layout.createParallelGroup()
					.addComponent(BInfoPresenter)
      				.addComponent(BNameDisplayerLabel)
      				.addComponent(BAddressDisplayerLabel)
      				.addComponent(BEmailDisplayerLabel)
      				.addComponent(BPhoneNumberDisplayerLabel)
      				
					.addComponent(comboNameLabel)
					.addComponent(mainServiceLabel)
					.addComponent(servicesLabel)
					.addComponent(mandatoryLabel)
	      		)
				.addGroup(layout.createParallelGroup()
					.addComponent(BNameDisplayerField)
      				.addComponent(BAddressDisplayerField)
      				.addComponent(BEmailDisplayerField)
      				.addComponent(BPhoneNumberDisplayerField)
      				
					.addComponent(comboNameTextField, 200, 200, 200)
					.addComponent(mainServiceList)
					.addComponent(servicesTextField)
					.addComponent(mandatoryTextField)
					.addComponent(defineComboButton)
	      		)
			)
			.addComponent(serviceTableScrollPane)
			.addGroup(
				layout.createSequentialGroup()
				.addComponent(appointmentListLabel)
				.addGroup(
					layout.createParallelGroup()
					.addComponent(appointmentList)
					.addGroup(
						layout.createSequentialGroup()
						.addComponent(startButton)
						.addComponent(endButton)
						.addComponent(registerNoShow)		
					)	
				)
			)
			.addComponent(overviewScrollPane)
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {setBizInfoButton, BPhoneNumberTextField, BEmailTextField,BAddressTextField,BNameTextField, serviceNameTextField, serviceDurationTextField, addServiceButton, garageList});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {defineComboButton, comboNameTextField, mainServiceList, servicesTextField, mandatoryTextField});
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
			.addComponent(errorMessage)
			.addGroup(
				layout.createParallelGroup()
				.addComponent(BNameLabel)
				.addComponent(BNameTextField)
				.addComponent(BInfoPresenter)
			)
			.addGroup(
				layout.createParallelGroup()
				.addComponent(BAddressLabel)
				.addComponent(BAddressTextField)
				.addComponent(BNameDisplayerLabel)
				.addComponent(BNameDisplayerField)
			)
			.addGroup(
				layout.createParallelGroup()
				.addComponent(BEmailLabel)
				.addComponent(BEmailTextField)
				.addComponent(BAddressDisplayerLabel)
				.addComponent(BAddressDisplayerField)
			)
			.addGroup(
				layout.createParallelGroup()
				.addComponent(BPhoneNumberLabel)
				.addComponent(BPhoneNumberTextField)
				.addComponent(BEmailDisplayerLabel)
				.addComponent(BEmailDisplayerField)
			)
			.addGroup(
				layout.createParallelGroup()
				.addComponent(setBizInfoButton)
				.addComponent(BPhoneNumberDisplayerLabel)
				.addComponent(BPhoneNumberDisplayerField)
			)
			.addComponent(line1)
			.addGroup(
				layout.createParallelGroup()
				.addComponent(serviceNameLabel)
				.addComponent(serviceNameTextField)
				.addComponent(comboNameLabel)
				.addComponent(comboNameTextField)
			)
			.addGroup(
				layout.createParallelGroup()
				.addComponent(serviceDurationLabel)
				.addComponent(serviceDurationTextField)
				.addComponent(mainServiceLabel)
				.addComponent(mainServiceList)
			)
			.addGroup(
				layout.createParallelGroup()
				.addComponent(garageList)
				.addComponent(servicesLabel)
				.addComponent(servicesTextField)
			)
			.addGroup(
				layout.createParallelGroup()
				.addComponent(addServiceButton)
				.addComponent(mandatoryLabel)
				.addComponent(mandatoryTextField)
			)
			.addComponent(defineComboButton)
			.addComponent(line4)
			.addComponent(serviceTableScrollPane)
			.addComponent(line3)
			.addGroup(
				layout.createParallelGroup()
				.addComponent(appointmentListLabel)
				.addComponent(appointmentList)
			)
			.addGroup(
				layout.createParallelGroup()
				.addComponent(startButton)
				.addComponent(endButton)
				.addComponent(registerNoShow)
			)
			.addComponent(line2)
			.addComponent(overviewScrollPane)
		);
		
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {setBizInfoButton, BPhoneNumberTextField, BEmailTextField,BAddressTextField,BNameTextField, serviceNameTextField, serviceDurationTextField, addServiceButton, garageList});
		
		pack();
	}
	
	private void refreshData() {
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			
			setTimeField.setText("");
			setDateField.setText("");
			
			//Appointments List
			appointments = new HashMap<Integer, TOAppointment>();
			appointmentList.removeAllItems();
			int index = 0;
			
			List<TOAppointment> sortAppointments = CarShopController.getAppointments();
			sortedAppointments(sortAppointments);
			Collections.reverse(sortAppointments);
			
			for(TOAppointment app: sortAppointments) {
				appointments.put(index, app);
				appointmentList.addItem(app.getUsername() + ": " + app.getName() +", "+ app.getDate()+ ", "+app.getStartTimes().get(0));
				index++;
			}
			
			appointmentList.setSelectedIndex(-1);
			
			// service name
	        serviceNameTextField.setText("");
	        // duration
	        serviceDurationTextField.setText("");

	        garages = new HashMap<Integer, TOGarage>();
	        garageList.removeAllItems();
	        index = 0;
	        for (TOGarage garage : CarShopController.getGarages()) {
	          garages.put(index, garage);
	          garageList.addItem(garage.getTechnicianUsername() + "'s garage (" + garage.getTechnicianType() + " technician)");
	            index++;
	        };
	        garageList.setSelectedIndex(-1);
			
	        // populate page with data
	    	// comboName
	    	comboNameTextField.setText("");
	    	// services
	    	servicesTextField.setText("");
	    	// mandatory
	    	mandatoryTextField.setText("");
	    	
	    	// define combo - main service
	    	existingServices = new HashMap<Integer, String>();
	    	mainServiceList.removeAllItems();
	    	index = 0;
	    	
	    	for (TOService service : CarShopController.getServices()) {
	    		existingServices.put(index,service.getName());
	    		mainServiceList.addItem(service.getName());
	    		index++;		
	    	}
	    	mainServiceList.setSelectedIndex(-1);
			    
			refreshAppointmentsList();
			refreshDataBusinessInfo();
			refreshAvailableServices();
		}
	}
	
	private void refreshDataBusinessInfo(){
		TOBusiness biz = CarShopController.getBusiness();
		if(biz==null) {
			BNameDisplayerField.setText("");;
			BAddressDisplayerField.setText("");
			BPhoneNumberDisplayerField.setText("");;
			BEmailDisplayerField.setText("");
		}
		else {
			BNameDisplayerField.setText(biz.getName());;
			BAddressDisplayerField.setText(biz.getAddress());
			BPhoneNumberDisplayerField.setText(biz.getPhoneNumber());;
			BEmailDisplayerField.setText(biz.getEmail());
		}
	}
	
	private void refreshAppointmentsList() {
		overviewDtm = new DefaultTableModel(0, 0);
		overviewDtm.setColumnIdentifiers(overviewColumnNames);
		overviewTable.setModel(overviewDtm);
		
		for(TOAppointment to: CarShopController.getAppointments()) {
			String bookable = to.getName();
			Date d = to.getDate();
			
			String services = "";
			for(int i = 0; i < to.getServices().size(); i++) {
				if(i == to.getServices().size()) {
					services += to.getServices().get(i);
				}
				else {
					services += to.getServices().get(i) + ",";
				}
			}
			
			String startTimesStr = "";
			for(int j = 0; j < to.getStartTimes().size(); j++) {
				if(j == to.getStartTimes().size() - 1) {
					startTimesStr += to.getStartTimes().get(j);
				}
				else {
					startTimesStr += to.getStartTimes().get(j) + ",";
				}
			}
			
			Object[] obj = {bookable, to.getUsername(), d, services, startTimesStr};
			overviewDtm.addRow(obj);
		}
		
		Dimension d = overviewTable.getPreferredSize();
		overviewScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
	}
	
	private void refreshAvailableServices() {
	      serviceDtm = new DefaultTableModel(0, 0);
	      serviceDtm.setColumnIdentifiers(serviceColumnNames);
	      serviceTable.setModel(serviceDtm);
	      for (TOService service : CarShopController.getServices()) {
	        String serviceName = service.getName();
	        int duration = service.getDuration();
	        TOGarage garage = service.getGarage();
	        String garageName =  garage.getTechnicianUsername() + "'s garage";
	        Object[] obj = {serviceName, duration, garageName};
	        serviceDtm.addRow(obj);
	    }
		  
	    Dimension d = serviceTable.getPreferredSize();
		serviceTableScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_SERVICE_TABLE));
	}
	
	private void SetUpBusinessInfoAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error="";
		try {
			if(CarShopController.getBusiness() == null) {
				CarShopController.setBusinessInfo(BNameTextField.getText(), BAddressTextField.getText(), BPhoneNumberTextField.getText(), BEmailTextField.getText());

			}
			else {
				CarShopController.updateBusinessInfo(BNameTextField.getText(), BAddressTextField.getText(), BPhoneNumberTextField.getText(), BEmailTextField.getText());
			}			
		}
		catch(InvalidInputException e) {
			error=e.getMessage();
		}
		catch(InvalidUserException e) {
			error=e.getMessage();
		}
		//create TO business info 
		refreshData();
	}
	
	private void startButtonActionPerformed(ActionEvent evt) {
		error = null;
		
		int selectedAppointment = appointmentList.getSelectedIndex();
		if(selectedAppointment < 0) {
			error = "Appointment need to be selected to be started";
		}
		
		if(error == null) {
			CarShopController.startAppointmentTO(appointments.get(selectedAppointment));
		}
		
		refreshData();
	}
	
	private void endButtonActionPerformed(ActionEvent evt) {
		error = null;
		
		int selectedAppointment = appointmentList.getSelectedIndex();
		if(selectedAppointment < 0) {
			error = "Appointment need to be selected to be ended";
		}
		
		if(error == null) {
			CarShopController.endApponitmentTO(appointments.get(selectedAppointment));
		}
		
		refreshData();
	}
	
	private void registerNoShow(ActionEvent evt) {
		error = null;
		
		int selectedAppointment = appointmentList.getSelectedIndex();
		if(selectedAppointment < 0) {
			error = "Appointment need to be selected to be registered no-show";
		}
		
		if(error == null) {
			CarShopController.registerNoShowTO(appointments.get(selectedAppointment));
		}
		
		refreshData();
	}
	
	private void addServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {
      // clear error message
      error = "";
	      
      int selectedGarage = garageList.getSelectedIndex();
      if (selectedGarage < 0) {
        error = "Garage has to be selected to add a service!";
      }
      if (error.length() == 0) {
        try {
          TOGarage garage = garages.get(selectedGarage);
          String technicianUsername = garage.getTechnicianUsername();
          CarShopController.createService(serviceNameTextField.getText(), Integer.parseInt(serviceDurationTextField.getText()), CarShopController.getGarageFromTechnicianType(technicianUsername));
        } catch (InvalidInputException e) {
            error = e.getMessage();
        } catch (RuntimeException e) {
          error = e.getMessage();
        } 
      }        
      // update visuals
      refreshData();
	}
	
	private void defineComboButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedService = mainServiceList.getSelectedIndex();
		if (selectedService < 0)
			error = "Service needs to be selected for definition! ";
		String comboName = comboNameTextField.getText();
		if (comboName.equals(""))
			error = error + "Combo Name canot be empty! ";
		String services = servicesTextField.getText();
		if (services.equals(""))
			error = error + "Services canot be empty! ";
		String mandatorySettings = mandatoryTextField.getText();
		if (mandatorySettings.equals(""))
			error = error + "Mandatory settings canot be empty! ";
		List<String> servicesList = new ArrayList<String>(Arrays.asList(servicesTextField.getText().split(",")));
		List<String> mandatorySettingsList = new ArrayList<String>(Arrays.asList(mandatoryTextField.getText().split(",")));
		int lastMandatoryIndex = mandatorySettingsList.size() - 1;
		List<Boolean> mandatorySettingsBoolean = new ArrayList<Boolean>();
		for(int i=0; i==lastMandatoryIndex; i++) {
			String currentSetting = mandatorySettingsList.get(i);
				Boolean respectiveBoolean = Boolean.parseBoolean(currentSetting);
				mandatorySettingsBoolean.add(respectiveBoolean);
		};
		error = error.trim();
		if (error.length() == 0) {
			// call the controller
			try { 
				CarShopController.defineCombo(comboName, existingServices.get(selectedService), servicesList, mandatorySettingsBoolean);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		// update visuals
		refreshData();		
	}		
	
	private void sortedAppointments(List<TOAppointment> tos) {
		for(int i = 0; i < tos.size(); i++) {
			int min_index = i;
			
			for(int j = i+ 1; j < tos.size(); j++) {
				Date currentDate = tos.get(j).getDate();
				
				if(currentDate.before(tos.get(min_index).getDate())) {
					min_index = j;
				}
			}
			
			TOAppointment temp = tos.get(min_index);
			tos.set(min_index, tos.get(i));
			tos.set(i, temp);
		} 
	}
}
