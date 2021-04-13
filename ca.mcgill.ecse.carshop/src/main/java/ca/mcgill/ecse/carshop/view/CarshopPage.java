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
	private JButton SetBizInfoButton;
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
	
	//LoggedInAs element
	private JLabel LoggedInAsLabel;
	private JLabel LoggedInAsField;
	
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
		SetBizInfoButton = new JButton();
		SetBizInfoButton.setText("Set Business Information");
		
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
		
		//elements for LoggedInAs element
		LoggedInAsLabel=new JLabel();
		LoggedInAsLabel.setText("Logged In As: ");
		LoggedInAsField=new JLabel();
		LoggedInAsField.setText("");
		
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
		SetBizInfoButton.addActionListener(new java.awt.event.ActionListener() {
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
										      				.addComponent(signUpPasswordLabel)
										      				.addComponent(BNameLabel)
										      				.addComponent(BAddressLabel)
										      				.addComponent(BEmailLabel)
										      				.addComponent(BPhoneNumberLabel)
										      				.addComponent(LoggedInAsLabel))
										      		.addGroup(layout.createParallelGroup()
										      				.addComponent(signUpUsernameTextField,200,200,400)
										      				.addComponent(signUpPasswordTextField,200,200,400)
										      				.addComponent(signUpForCustomerAccountButton)
										      				.addComponent(BNameTextField)
										      				.addComponent(BAddressTextField)
										      				.addComponent(BEmailTextField)
										      				.addComponent(BPhoneNumberTextField)
										      				.addComponent(SetBizInfoButton)
										      				.addComponent(LoggedInAsField))
										      		.addGroup(layout.createParallelGroup()
												      				.addComponent(logInUsernameLabel)
												      				.addComponent(logInPasswordLabel)
												      				.addComponent(BInfoPresenter)
												      				.addComponent(BNameDisplayerLabel)
												      				.addComponent(BAddressDisplayerLabel)
												      				.addComponent(BEmailDisplayerLabel)
												      				.addComponent(BPhoneNumberDisplayerLabel))
												     .addGroup(layout.createParallelGroup()
												      				.addComponent(logInUsernameTextField,200,200,400)
												      				.addComponent(logInPasswordTextField,200,200,400)
												      				.addComponent(logInAccountButton)
												      				.addComponent(BNameDisplayerField)
												      				.addComponent(BAddressDisplayerField)
												      				.addComponent(BEmailDisplayerField)
												      				.addComponent(BPhoneNumberDisplayerField))));
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {signUpForCustomerAccountButton, signUpPasswordTextField, signUpUsernameTextField});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {logInAccountButton, logInPasswordTextField, logInUsernameTextField});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {SetBizInfoButton, BPhoneNumberTextField, BEmailTextField,BAddressTextField,BNameTextField});

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
					.addGroup(layout.createParallelGroup()
							.addComponent(BNameLabel)
							.addComponent(BNameTextField)
							.addComponent(BInfoPresenter)
							)
					.addGroup(layout.createParallelGroup()
							.addComponent(BAddressLabel)
							.addComponent(BAddressTextField)
							.addComponent(BNameDisplayerLabel)
							.addComponent(BNameDisplayerField))
					.addGroup(layout.createParallelGroup()
							.addComponent(BEmailLabel)
							.addComponent(BEmailTextField)
							.addComponent(BAddressDisplayerLabel)
							.addComponent(BAddressDisplayerField))
					.addGroup(layout.createParallelGroup()
							.addComponent(BPhoneNumberLabel)
							.addComponent(BPhoneNumberTextField)
							.addComponent(BEmailDisplayerLabel)
							.addComponent(BEmailDisplayerField))
					.addGroup(layout.createParallelGroup()
							.addComponent(SetBizInfoButton)
							.addComponent(BPhoneNumberDisplayerLabel)
							.addComponent(BPhoneNumberDisplayerField))
					.addGroup(layout.createParallelGroup()
							.addComponent(LoggedInAsLabel)
							.addComponent(LoggedInAsField))
					
				);
		
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
			LoggedInAsField.setText(logInUsernameTextField.getText());
			
		}
		catch(InvalidInputException e) {
			error=e.getMessage();
		}
		refreshData();
	}
	
	private void SetUpBusinessInfoAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error="";
		if(!BPhoneNumberDisplayerField.getText().equals("")) {
			error="You have already set your business information. You must update it now.";
		}
		else {
			try {
				CarShopController.setBusinessInfo(BNameTextField.getText(), BAddressTextField.getText(), BPhoneNumberTextField.getText(), BEmailTextField.getText());
				BNameDisplayerField.setText( BNameTextField.getText() );
				BAddressDisplayerField.setText(BAddressTextField.getText());
				BPhoneNumberDisplayerField.setText(BPhoneNumberTextField.getText());
				BEmailDisplayerField.setText(BEmailTextField.getText());
			}
			catch(InvalidInputException e) {
				error=e.getMessage();
			}
			catch(InvalidUserException e) {
				error=e.getMessage();
			}
		}
		refreshData();
	}
	
}
