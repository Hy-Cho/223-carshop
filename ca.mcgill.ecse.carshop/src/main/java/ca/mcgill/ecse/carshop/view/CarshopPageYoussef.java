package ca.mcgill.ecse.carshop.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;

public class CarshopPageYoussef extends JFrame {
	private static final long serialVersionUID = -5633915762703837868L;

	// UI elements
	private JLabel errorMessage;
	
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
	
	// data elements
	private String error = null;
	
	// Book Combo
	private HashMap<Integer, String> existingServices;
	
	public CarshopPageYoussef() {
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
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
		mandatoryLabel.setText("Enter Mandatory Settings for Previous Services (separated by ','): ");
		
		defineComboButton = new JButton();
		defineComboButton.setText("Add New Service Combo");
	
		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Car Shop System");
		
		// listeners for define Combo
				defineComboButton.addActionListener(new java.awt.event.ActionListener() {
				    public void actionPerformed(java.awt.event.ActionEvent evt) {
				        defineComboButtonActionPerformed(evt);
				    }
				});
				
		// horizontal line elements
		JSeparator horizontalLineTop = new JSeparator();
		
		// layout
				GroupLayout layout = new GroupLayout(getContentPane());
				getContentPane().setLayout(layout);
				layout.setAutoCreateGaps(true);
				layout.setAutoCreateContainerGaps(true);
				layout.setHorizontalGroup(
						layout.createParallelGroup()
						.addComponent(errorMessage)
						.addComponent(horizontalLineTop)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(comboNameLabel)
										.addComponent(mainServiceLabel)
										.addComponent(servicesLabel)
										.addComponent(mandatoryLabel))
								.addGroup(layout.createParallelGroup()	
										.addComponent(comboNameTextField, 200, 200, 400)
										.addComponent(mainServiceList)
										.addComponent(servicesTextField)
										.addComponent(mandatoryTextField)
										.addComponent(defineComboButton)))
						);
				layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {defineComboButton, comboNameTextField});
				layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {defineComboButton, mainServiceList});
				layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {defineComboButton, servicesTextField});
				layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {defineComboButton, mandatoryTextField});
				
				layout.setVerticalGroup(
						layout.createSequentialGroup()
						.addComponent(errorMessage)
						.addGroup(layout.createParallelGroup()
								.addComponent(comboNameLabel)
								.addComponent(comboNameTextField))
						.addGroup(layout.createParallelGroup()
								.addComponent(mainServiceLabel)
								.addComponent(mainServiceList))
						.addGroup(layout.createParallelGroup()
								.addComponent(servicesLabel)
								.addComponent(servicesTextField))
						.addGroup(layout.createParallelGroup()
								.addComponent(mandatoryLabel)
								.addComponent(mandatoryTextField))
						.addGroup(layout.createParallelGroup()
								.addComponent(horizontalLineTop)
								.addComponent(defineComboButton)));
				pack();
	}
	
	private void refreshData() {
		  // error
	    errorMessage.setText(error);
	    if (error == null || error.length() == 0) {
	        // populate page with data
	    	// comboName
	    	comboNameTextField.setText("");
	    	// services
	    	servicesTextField.setText("");
	    	// mandatory
	    	mandatoryTextField.setText("");
	    	
//	    	// define combo - main service
//	    	existingServices = new HashMap<Integer, String>();
//	    	mainServiceList.removeAllItems();
//	    	Integer index = 0;
//	    	for (String service : CarShopController.getServices()) {
//	    		existingServices.put(index,service);
//	    		mainServiceList.addItem(service);
//	    		index++;		
//	    	}
	    	mainServiceList.setSelectedIndex(-1);
	}
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
}
