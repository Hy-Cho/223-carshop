package ca.mcgill.ecse.carshop.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;
import ca.mcgill.ecse.carshop.controller.TOGarage;
import ca.mcgill.ecse.carshop.model.Garage;


public class CarShopPageHyunbum extends JFrame {
	private static final long serialVersionUID = -5633915762703837868L;
	
	private String error = null;
	private JLabel errorMessage;
	
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
	
	public CarShopPageHyunbum() {
	  CarShopController.testView();
	  initComponents();
      refreshData();
	}
	
	private void initComponents() {
		//initComponents();
		//refreshData();
	  
	  errorMessage = new JLabel();
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
	  GroupLayout layout = new GroupLayout(getContentPane());
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
	
	
	private void refreshData() {
	  // error
      errorMessage.setText(error);
      if (error == null || error.length() == 0) {
        // service name
        serviceNameTextField.setText("");
        // duration
        serviceDurationTextField.setText("");

        
        // toggle sick status
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
//	  refreshAvailableServices();
	  pack();
	}
	
	// cannot get existing services with current way of car shop implementation
	/*
	private void refreshAvailableServices() {
      serviceDtm = new DefaultTableModel(0, 0);
      serviceDtm.setColumnIdentifiers(serviceColumnNames);
      serviceTable.setModel(serviceDtm);
      
      Dimension d = serviceTable.getPreferredSize();
      serviceTableScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_SERVICE_TABLE));
  }
  */
	
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
