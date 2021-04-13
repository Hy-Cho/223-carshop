package ca.mcgill.ecse.carshop.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JFrame;


import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;
import ca.mcgill.ecse.carshop.controller.InvalidUserException;
import ca.mcgill.ecse.carshop.controller.TOGarage;

public class CarshopPage extends JFrame {
	private static final long serialVersionUID = -5633915762703837868L;
	private JLabel errorMessage;
	
	//signUp
	private JTextField signUpUsernameTextField;
	private JTextField signUpPasswordTextField;
	private JButton signUpForCustomerAccountButton;
	private JLabel signUpUsernameLabel;
	private JLabel signUpPasswordLabel;
	
	//logIn
	private JTextField logInUsernameTextField;
	private JTextField logInPasswordTextField;
	private JButton logInAccountButton;
	private JLabel logInUsernameLabel;
	private JLabel logInPasswordLabel;
	
	//Business Info setter element
	private JTextField BNameTextField;
	private JTextField BAddressTextField;
	private JTextField BPhoneNumberTextField;
	private JTextField BEmailTextField;
	private JButton SetBizInfo;
	private JLabel BNameLabel;
	private JLabel BAddressLabel;
	private JLabel BPhoneNumberLabel;
	private JLabel BEmailLabel;
	
	// add service
    private JLabel serviceNameLabel;
    private JTextField serviceNameTextField;
    private JLabel serviceDurationLabel;
    private JTextField serviceDurationTextField;
    private JComboBox<String> garageList;
    private JButton addServiceButton;
    private JTable serviceTable;
    private JScrollPane serviceTableScrollPane;
    private DefaultTableModel serviceDtm;
    private String serviceColumnNames[] = {"Name", "Duration"};
    private static final int HEIGHT_SERVICE_TABLE = 250;
    // garages to add service
    private HashMap<Integer, TOGarage> garages;
	
	private String error=null;
	public CarshopPage() {
	  CarShopController.testView();
	  initComponents();
	  refreshData();
	}
	
	private void refreshData() {
		errorMessage.setText(error);
		if(error == null || error.length() ==0) {
			signUpUsernameTextField.setText("");
			signUpPasswordTextField.setText("");
			logInUsernameTextField.setText("");
			logInPasswordTextField.setText("");
			BNameTextField.setText("");;
			BAddressTextField.setText("");;
			BPhoneNumberTextField.setText("");;
			BEmailTextField.setText("");
			// service name
	        serviceNameTextField.setText("");
	        // duration
	        serviceDurationTextField.setText("");

	        garages = new HashMap<Integer, TOGarage>();
	        garageList.removeAllItems();
	        Integer index = 0;
	        for (TOGarage garage : CarShopController.getGarages()) {
	          garages.put(index, garage);
	          garageList.addItem(garage.getTechnicianUsername() + "'s garage");
	            index++;
	        };
	        garageList.setSelectedIndex(-1);
		}
		pack();
	}
	
	private void initComponents() {
		errorMessage=new JLabel();
		errorMessage.setForeground(Color.RED);
		
		//elements for signUpCustomerAccount
		signUpUsernameTextField = new JTextField();
		signUpPasswordTextField = new JTextField();
		signUpUsernameLabel = new JLabel();
		signUpUsernameLabel.setText("Username: ");
		signUpPasswordLabel = new JLabel();
		signUpPasswordLabel.setText("Password: ");
		signUpForCustomerAccountButton = new JButton();
		signUpForCustomerAccountButton.setText("Sign Up for Customer Account");
		
		//elements for logIn
		logInUsernameTextField = new JTextField();
		logInPasswordTextField = new JTextField();
		logInUsernameLabel = new JLabel();
		logInUsernameLabel.setText("Username: ");
		logInPasswordLabel = new JLabel();
		logInPasswordLabel.setText("Password: ");
		logInAccountButton = new JButton();
		logInAccountButton.setText("Log In");
		
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
		SetBizInfo = new JButton();
		SetBizInfo.setText("Set Business Information");
		
		//add listeners to sign up as a customer
		signUpForCustomerAccountButton.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	signUpForCustomerAccountActionPerformed(evt);
		    }
		});
		
		//add listeners to log in
		logInAccountButton.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		        logInAccountButtonActionPerformed(evt);
		    }
		});
		
		//add listener to setup business info
		SetBizInfo.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		        SetUpBusinessInfoAccountButtonActionPerformed(evt);
		    }
		});
		
		//layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
								  	.addComponent(errorMessage)
								  	.addGroup(layout.createSequentialGroup()
										      		.addGroup(layout.createParallelGroup()
										      				.addComponent(signUpUsernameLabel)
										      				.addComponent(signUpPasswordLabel))
										      		.addGroup(layout.createParallelGroup())
										      				.addComponent(signUpUsernameTextField,200,200,400)
										      				.addComponent(signUpPasswordTextField,200,200,400)
										      				.addComponent(signUpForCustomerAccountButton)
										      		.addGroup(layout.createParallelGroup()
												      				.addComponent(logInUsernameLabel)
												      				.addComponent(logInPasswordLabel))
												     .addGroup(layout.createParallelGroup())
												      				.addComponent(logInUsernameTextField,200,200,400)
												      				.addComponent(logInPasswordTextField,200,200,400)
												      				.addComponent(logInAccountButton)));
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {signUpForCustomerAccountButton, signUpPasswordTextField, signUpUsernameTextField});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {logInAccountButton, logInPasswordTextField, logInUsernameTextField});
		
		layout.setVerticalGroup(
					layout.createSequentialGroup()
					.addComponent(errorMessage)
					.addGroup(layout.createParallelGroup()
									.addComponent(signUpUsernameLabel)
									.addComponent(signUpUsernameTextField)
									.addComponent(logInUsernameLabel)
									.addComponent(logInUsernameTextField))
					.addGroup(layout.createParallelGroup()
								.addComponent(signUpPasswordLabel)
								.addComponent(signUpPasswordTextField)
								.addComponent(logInPasswordLabel)
								.addComponent(logInPasswordTextField))
					.addGroup(layout.createParallelGroup()
									.addComponent(signUpForCustomerAccountButton)
									.addComponent(logInAccountButton))
				);
		
		//layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {signUpForCustomerAccountButton, signUpPasswordTextField, signUpUsernameTextField});
		//layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {logInAccountButton, logInPasswordTextField, logInUsernameTextField});
		
				
		pack();
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
	      Dimension d = serviceTable.getPreferredSize();
	      serviceTableScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_SERVICE_TABLE));
	      serviceTableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	      
	      
	      // action listeners for service
	      addServiceButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	          addServiceButtonActionPerformed(evt);
	        }
	      });
	      
	      // layout
//	      GroupLayout layout = new GroupLayout(getContentPane());
	      getContentPane().setLayout(layout);
	      layout.setAutoCreateGaps(true);
	      layout.setAutoCreateContainerGaps(true);
	      layout.setHorizontalGroup(
	          layout.createParallelGroup()
	          .addGroup(layout.createSequentialGroup()
	              .addGroup(layout.createParallelGroup()
	                  .addComponent(serviceNameLabel)
	                  .addComponent(serviceDurationLabel)
	                  )
	              .addGroup(layout.createParallelGroup()
	                  .addComponent(serviceNameTextField)
	                  .addComponent(serviceDurationTextField)
	                  .addComponent(garageList)
	                  .addComponent(addServiceButton)
	                  )
	              )
	          .addComponent(errorMessage)
	          );

	      layout.setVerticalGroup(
	          layout.createSequentialGroup()
	          .addGroup(layout.createParallelGroup()
	              .addComponent(serviceNameLabel)
	              .addComponent(serviceNameTextField)
	              )
	          .addGroup(layout.createParallelGroup()
	              .addComponent(serviceDurationLabel)
	              .addComponent(serviceDurationTextField)
	              )
	          .addComponent(garageList)
	          .addComponent(addServiceButton)
	          .addComponent(errorMessage)
	          );
	      
	      layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {serviceNameTextField, serviceDurationTextField});
	      layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {addServiceButton, garageList});
	      layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {serviceNameTextField, serviceDurationTextField, addServiceButton, garageList});
	}
	
	private void signUpForCustomerAccountActionPerformed(java.awt.event.ActionEvent evt) {
		error="";
		try {
			CarShopController.signUpCustomerAccount(signUpUsernameTextField.getText(), signUpPasswordTextField.getText());
			
		}
		catch(InvalidInputException e) {
			error=e.getMessage();
		}
		refreshData();
	}
	
	private void logInAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error="";
		try {
			CarShopController.logIn(logInUsernameTextField.getText(), logInPasswordTextField.getText());
			
		}
		catch(InvalidInputException e) {
			error=e.getMessage();
		}
		refreshData();
	}
	
	private void SetUpBusinessInfoAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error="";
		try {
			CarShopController.setBusinessInfo(BNameTextField.getText(), BAddressTextField.getText(), BPhoneNumberTextField.getText(), BEmailTextField.getText());
		}
		catch(InvalidInputException e) {
			error=e.getMessage();
		}
		catch(InvalidUserException e) {
			error=e.getMessage();
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
	
}
