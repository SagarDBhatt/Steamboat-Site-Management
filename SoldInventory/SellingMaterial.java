package SteamboatSprings.SiteManagementAPI.SoldInventory;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import SteamboatSprings.SiteManagementAPI.CurrentInventory.CurrentInventoryCount;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.toedter.calendar.JDateChooser;
import java.awt.FlowLayout;

public class SellingMaterial {

	private JFrame frmRevolutionSystems;
	private JTextField txtFieldTicketNumber;
	private JTextField txtFieldWeightOfMaterial;
	private JTextField txtFieldOtherCustomerName;
	private JTextField txtFieldBOLNumber;


	JDateChooser dateChooser;
	java.sql.Date date;
	
	/*
	 * //LocalDate and time objects to display system date and time.
	 * 
	 * LocalDate date = java.time.LocalDate.now(); LocalTime time =
	 * java.time.LocalTime.now();
	 * 
	 * DateTimeFormatter timeForamtter = DateTimeFormatter.ofPattern("HH:mm");
	 * String localTime = time.format(timeForamtter);
	 * 
	 * DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	 * String localDate = date.format(dateFormatter);
	 * 
	 * //End of declaring and initializing System date and time
	 */
//SQL connection variables define

	String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	String dbURL = "jdbc:sqlserver://FLEXBIZHTAPP\\SQLEXPRESS:1433;databaseName=Steamboat;IntegratedSecurity=true";
	String userName = "CTLIO/Sbhatt";

	Connection aConneciton = null;
	Statement aStatement = null;

//End of connection variables.		

//Objects & other variables initialization

	JComboBox comboBoxMaterialType = new JComboBox();
	JComboBox comboBoxCustomerName = new JComboBox();
	JComboBox comboBoxNumberOfBales = new JComboBox();

	Object objCustomerName, objMaterialType, objNumberOfBales;

	double weightInLb, weightInTons;
	
//Create object of Current Inventory 
	CurrentInventoryCount currentInventoryObject;

//End

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellingMaterial window = new SellingMaterial();
					window.frmRevolutionSystems.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SellingMaterial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRevolutionSystems = new JFrame();
		frmRevolutionSystems.setIconImage(Toolkit.getDefaultToolkit().getImage(SellingMaterial.class
				.getResource("/windowBuilder/Resources/6cfcb4e9556799a6fcbf983aab9fab19-32bits-16.png")));
		frmRevolutionSystems.setType(Type.POPUP);
		frmRevolutionSystems.setTitle("Revolution Systems");
		frmRevolutionSystems.setBounds(100, 100, 602, 316);
		frmRevolutionSystems.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRevolutionSystems.getContentPane().setLayout(null);

		JPanel jPanelEntry = new JPanel();
		jPanelEntry.setBorder(
				new TitledBorder(null, "Sold Inventory Reporting", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jPanelEntry.setBounds(1, 11, 583, 218);
		frmRevolutionSystems.getContentPane().add(jPanelEntry);
		jPanelEntry.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

		JLabel label = new JLabel("Customer Name*");
		jPanelEntry.add(label, "2, 2");
		label.setToolTipText("Enter the Weight Ticket Number");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Calibri", Font.PLAIN, 16));
		jPanelEntry.add(comboBoxCustomerName, "4, 2");

		comboBoxCustomerName.setModel(new DefaultComboBoxModel(new String[] {"Sage Recycling", "McKinley Paper", "Republic Papers", "Other"}));
		comboBoxCustomerName.setToolTipText("Select Customer Name");
		comboBoxCustomerName.setMaximumRowCount(100);
		comboBoxCustomerName.setFont(new Font("Calibri", Font.PLAIN, 16));

		JLabel label_1 = new JLabel("Other");
		jPanelEntry.add(label_1, "6, 2, center, center");
		label_1.setToolTipText("Enter the Weight Ticket Number");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Calibri", Font.PLAIN, 16));

		txtFieldOtherCustomerName = new JTextField();
		jPanelEntry.add(txtFieldOtherCustomerName, "8, 2");
		txtFieldOtherCustomerName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldOtherCustomerName.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldOtherCustomerName.setColumns(10);

		JLabel lblTicketNumber = new JLabel("Weight Ticket Number*");
		jPanelEntry.add(lblTicketNumber, "2, 4");
		lblTicketNumber.setToolTipText("Enter the Weight Ticket Number");
		lblTicketNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTicketNumber.setFont(new Font("Calibri", Font.PLAIN, 16));

		txtFieldTicketNumber = new JTextField();
		jPanelEntry.add(txtFieldTicketNumber, "4, 4");
		txtFieldTicketNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldTicketNumber.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldTicketNumber.setColumns(10);

		JLabel lblBolNumber = new JLabel("BOL Number*");
		jPanelEntry.add(lblBolNumber, "2, 6");
		lblBolNumber.setToolTipText("Enter the Weight Ticket Number");
		lblBolNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBolNumber.setFont(new Font("Calibri", Font.PLAIN, 16));

		txtFieldBOLNumber = new JTextField();
		jPanelEntry.add(txtFieldBOLNumber, "4, 6");
		txtFieldBOLNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldBOLNumber.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldBOLNumber.setColumns(10);

		JLabel label_4 = new JLabel("Material Type*");
		jPanelEntry.add(label_4, "2, 8");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Calibri", Font.PLAIN, 16));
		jPanelEntry.add(comboBoxMaterialType, "4, 8");

		comboBoxMaterialType.setModel(
				new DefaultComboBoxModel(new String[] { "OCC", "ONP", "HDPEN", "HDPEC", "PET", "TIN", "UBC" }));
		comboBoxMaterialType.setFont(new Font("Calibri", Font.PLAIN, 16));

		JLabel numberOfBales = new JLabel("Number of Bales*\r\n");
		jPanelEntry.add(numberOfBales, "6, 8");
		numberOfBales.setHorizontalAlignment(SwingConstants.RIGHT);
		numberOfBales.setFont(new Font("Calibri", Font.PLAIN, 16));
		jPanelEntry.add(comboBoxNumberOfBales, "8, 8");

		comboBoxNumberOfBales.setMaximumRowCount(20);
		comboBoxNumberOfBales.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"}));

		JLabel lblSoldInventoryWeight = new JLabel("Sold Inventory weight (lbs)");
		jPanelEntry.add(lblSoldInventoryWeight, "2, 10");
		lblSoldInventoryWeight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoldInventoryWeight.setFont(new Font("Calibri", Font.PLAIN, 16));

		txtFieldWeightOfMaterial = new JTextField();
		jPanelEntry.add(txtFieldWeightOfMaterial, "4, 10");
		txtFieldWeightOfMaterial.setToolTipText("Weight of Single Stream in pounds");
		txtFieldWeightOfMaterial.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldWeightOfMaterial.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldWeightOfMaterial.setColumns(10);

		JLabel label_2 = new JLabel("Date*");
		jPanelEntry.add(label_2, "2, 12, right, top");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Calibri", Font.PLAIN, 16));

		dateChooser = new JDateChooser();
		jPanelEntry.add(dateChooser, "4, 12, fill, top");

		JPanel panel = new JPanel();
		panel.setBounds(7, 232, 576, 41);
		frmRevolutionSystems.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Action Listener Method for 'Submit' button

		JButton button = new JButton("Submit");
		button.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		panel.add(button);

		// Clear button action listener
		JButton button_1 = new JButton("Clear");
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				comboBoxCustomerName.setSelectedIndex(0);
				comboBoxMaterialType.setSelectedIndex(0);
				txtFieldOtherCustomerName.setText("");
				txtFieldTicketNumber.setText("");
				txtFieldBOLNumber.setText("");
				txtFieldWeightOfMaterial.setText("");
			}
		});

		// End of Action listener

		button_1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));

		// Exit button action listener
		JButton button_2 = new JButton("Exit");
		panel.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int reply = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Confirm Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (reply == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else
					txtFieldTicketNumber.requestFocus();
			}
		});

		// End of Exit button
		button_2.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ConnectToSQL();

					if (validation()) {
						if (review()) {
							insertSellingMaterial();
							
							currentInventoryObject = new CurrentInventoryCount();
							
							currentInventoryObject.connection();
							currentInventoryObject.calcSoldMaterial(objMaterialType.toString(),
									Integer.parseInt(objNumberOfBales.toString()), weightInTons);
							currentInventoryObject.getCurrentInventory();
							currentInventoryObject.updateNewInventory();
						}
					}
				}

				catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "Class Not Found inside Selling Material!!", "Error!!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

//End of Local Date and Time		

	}// End of Initialize()

//Connect to SQL Server

	public void ConnectToSQL() throws ClassNotFoundException, SQLException {

		Class.forName(JDBC_DRIVER);
		aConneciton = DriverManager.getConnection(dbURL, userName, "");
		aStatement = aConneciton.createStatement();
		// JOptionPane.showMessageDialog(null, "Database connection successful");

	}// End of ConnectToSQL() method

//Insert Selling Material to SQL

	public void insertSellingMaterial() throws SQLException {

		weightInLb = Double.parseDouble(txtFieldWeightOfMaterial.getText());
		weightInTons = weightInLb / 2000.0;
		date = new java.sql.Date(dateChooser.getDate().getTime());
		
		String qryInsertSellingMaterial = "Insert into SoldMaterial "
				+ "(CustomerID, CustomerName,WeightTicketNumber,BOLNumber,MaterialType,"
				+ "[WeightOfMaterial (in lb)],[WeightOfMaterial (in tons)],NumberOfBales,Date,Time)\r\n"
				+ "Values ( (Select ISNULL(Max(CustomerID) + 1,0) from SoldMaterial), '" + objCustomerName + "', "
				+ txtFieldTicketNumber.getText() + " , " + txtFieldBOLNumber.getText() + " , ' " + objMaterialType
				+ " ', " + txtFieldWeightOfMaterial.getText() + " , " + weightInTons + ", " + objNumberOfBales + " ,'"
				+ date + " ' , ' 0:00 ')";

		aStatement.executeUpdate(qryInsertSellingMaterial);

		JOptionPane.showMessageDialog(null, "Sold Inventory reported successfully");

		comboBoxCustomerName.setSelectedIndex(0);
		comboBoxMaterialType.setSelectedIndex(0);
		txtFieldOtherCustomerName.setText("");
		txtFieldTicketNumber.setText("");
		txtFieldBOLNumber.setText("");
		txtFieldWeightOfMaterial.setText("");

	}// End of Insert Method

//Validation method	
	public boolean validation() {

		objCustomerName = comboBoxCustomerName.getSelectedItem();
		objMaterialType = comboBoxMaterialType.getSelectedItem();
		objNumberOfBales = comboBoxNumberOfBales.getSelectedItem();

		SimpleDateFormat sdfo = new SimpleDateFormat("yyyy/MM/dd");

		String dtChooser = sdfo.format(dateChooser.getDate());
		String todaysDate = sdfo.format(new Date());
		
		
		if(dtChooser.compareTo(todaysDate)>0) {
			JOptionPane.showMessageDialog(null, "Date can not be future date", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		
		if (comboBoxCustomerName.getSelectedIndex() == 3) {

			if (!txtFieldOtherCustomerName.getText().matches("[a-zA-Z]+")) {
				JOptionPane.showMessageDialog(null, "Please Enter Customer Name in Other", "No null values allowed",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			objCustomerName = txtFieldOtherCustomerName.getText();
		}

		if (!txtFieldTicketNumber.getText().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Please Enter valid Weight Ticket number in digits.", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		else if (!txtFieldBOLNumber.getText().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Please Enter valid BOL Ticket number in digits.", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		else if (!txtFieldWeightOfMaterial.getText().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Please Enter valid Weight of material in lbs", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		else
			return true;

	}// End of Validation()

	public boolean review() {
		// grosseightInLb = Double.parseDouble(txtFieldWeightOCC.getText() +
		// txtFieldWeightSS.getText());

		if (comboBoxCustomerName.getSelectedIndex() == 3) {
			objCustomerName = txtFieldOtherCustomerName.getText();
		}

		int reply = JOptionPane.showConfirmDialog(null,
				"Review entered data : \n\n" + "Customer Name : " + objCustomerName + "\n Weight Ticket Number : "
						+ txtFieldTicketNumber.getText() + "\n BOL number : " + txtFieldBOLNumber.getText()
						+ "\n Material Type : " + comboBoxMaterialType.getSelectedItem() + "\n Number of Bales : "
						+ comboBoxNumberOfBales.getSelectedIndex() + "\n Date : " + new java.sql.Date(dateChooser.getDate().getTime())
						+"\n Weight : " + txtFieldWeightOfMaterial.getText(),
				"Confirm Submit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		if (reply == JOptionPane.YES_OPTION) {
			return true;
		} else
			return false;

	}// End of Review()
}// End of class
