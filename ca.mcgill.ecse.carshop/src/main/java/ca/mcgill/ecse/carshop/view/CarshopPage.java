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
	
	private String error=null;
	public CarshopPage() {
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
	
}
