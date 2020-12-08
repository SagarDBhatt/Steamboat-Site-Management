package SteamboatSprings.SiteManagementAPI.IncomingInventory;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

//import com.mysql.jdbc.ResultSet;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import com.toedter.calendar.JDateChooser;
import java.awt.FlowLayout;

public class IncomingMaterial {

	private JFrame frmRevolutionSystems_Steamboat;
	private JTextField txtFieldTicketNumber;
	private JTextField txtFieldWeightSS;
	private JDateChooser dateChooser;

	JComboBox cmbobxMaterialType = new JComboBox();
	JComboBox comboBoxCustomerName = new JComboBox();

	Object objMaterialType;
	Object objCustomerName;

	public double weightSS = 0, weightOCC = 0, grosseightInLb = 0, grossWeightInTons = 0, trashOutWeightInLb = 0,
			trashOutWeightInTons = 0;
	String ticketNumber;

	/*
	 * //Initiate system date and time to set in Text field
	 * 
	 * LocalDate date = java.time.LocalDate.now(); LocalTime time =
	 * java.time.LocalTime.now();
	 * 
	 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm"); String
	 * localTime = time.format(dtf);
	 * 
	 * DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	 * String localDate = date.format(dateFormatter);
	 */

//End of Date & time. 

	String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
	String dbURL = "jdbc:sqlserver://FLEXBIZHTAPP\\SQLEXPRESS:1433;databaseName=Steamboat;integratedSecurity=true";
	String userName = "CTLIO/Sbhatt";

	Connection aConnection = null;
	private static Statement aStatement = null;

	public static Statement getaStatement() {
		return aStatement;
	}

	public static void setaStatement(Statement aStatement) {
		IncomingMaterial.aStatement = aStatement;
	}

	ResultSet rsInsert = null;

	// SteamboatMethods SteamboatObject = new SteamboatMethods();
	// SteamboatMethods aSteamboatObj;
	private JTextField txtFieldOtherCustomerName;
	private JTextField txtFieldTrashoutWeight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IncomingMaterial window = new IncomingMaterial();
					window.frmRevolutionSystems_Steamboat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IncomingMaterial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRevolutionSystems_Steamboat = new JFrame();
		frmRevolutionSystems_Steamboat.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(IncomingMaterial.class.getResource("/windowBuilder/Resources/RS_logo.jpg")));
		frmRevolutionSystems_Steamboat.setFont(new Font("Calibri", Font.PLAIN, 20));
		frmRevolutionSystems_Steamboat.setForeground(SystemColor.text);
		frmRevolutionSystems_Steamboat.setTitle("Revolution Systems");
		frmRevolutionSystems_Steamboat.setBounds(100, 100, 562, 319);
		frmRevolutionSystems_Steamboat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRevolutionSystems_Steamboat.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Incoming Inventory Reporting", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 531, 203);
		frmRevolutionSystems_Steamboat.getContentPane().add(panel);

//end of setting up Date and Time. 

		JLabel lblCustomerName = new JLabel("Customer Name*");
		lblCustomerName.setBounds(33, 26, 112, 20);
		lblCustomerName.setToolTipText("Enter the Weight Ticket Number");
		lblCustomerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomerName.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboBoxCustomerName.setBounds(168, 23, 163, 26);

		comboBoxCustomerName.setToolTipText("Select Customer Name");
		comboBoxCustomerName
				.setModel(new DefaultComboBoxModel(new String[] {"Twin Enviro", "Waste Management", "Republic Services", "Other"}));
		comboBoxCustomerName.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboBoxCustomerName.setMaximumRowCount(100);

		JLabel lblTicketNumber = new JLabel("Enter Ticket Number*");
		lblTicketNumber.setBounds(19, 56, 140, 20);
		lblTicketNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTicketNumber.setToolTipText("Enter the Weight Ticket Number");
		lblTicketNumber.setFont(new Font("Calibri", Font.PLAIN, 16));

		txtFieldTicketNumber = new JTextField();
		txtFieldTicketNumber.setBounds(168, 53, 163, 26);
		txtFieldTicketNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldTicketNumber.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldTicketNumber.setColumns(10);
		panel.setLayout(null);
		panel.add(lblCustomerName);
		panel.add(comboBoxCustomerName);

		JLabel lblOtherCustomerName = new JLabel("Other");
		lblOtherCustomerName.setBounds(335, 23, 37, 20);
		lblOtherCustomerName.setToolTipText("Enter the Weight Ticket Number");
		lblOtherCustomerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOtherCustomerName.setFont(new Font("Calibri", Font.PLAIN, 16));
		panel.add(lblOtherCustomerName);

		// End of objCustomerName

		txtFieldOtherCustomerName = new JTextField();
		txtFieldOtherCustomerName.setBounds(376, 23, 119, 26);
		txtFieldOtherCustomerName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldOtherCustomerName.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldOtherCustomerName.setColumns(10);
		panel.add(txtFieldOtherCustomerName);
		panel.add(lblTicketNumber);
		panel.add(txtFieldTicketNumber);

		JLabel lblDate = new JLabel("Date*");
		lblDate.setBounds(69, 83, 39, 20);
		panel.add(lblDate);
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setFont(new Font("Calibri", Font.PLAIN, 16));

		JLabel lblMaterialType = new JLabel("Material Type*");
		lblMaterialType.setBounds(13, 110, 98, 20);
		panel.add(lblMaterialType);
		lblMaterialType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaterialType.setFont(new Font("Calibri", Font.PLAIN, 16));
		cmbobxMaterialType.setBounds(168, 107, 129, 26);
		panel.add(cmbobxMaterialType);

		cmbobxMaterialType.setModel(new DefaultComboBoxModel(new String[] {"Select Material", "Single Stream", "Trash out", "OCC"}));
		cmbobxMaterialType.setFont(new Font("Calibri", Font.PLAIN, 16));

		JLabel lblWeightSS = new JLabel("Weight (SS / OCC)");
		lblWeightSS.setBounds(13, 140, 132, 20);
		panel.add(lblWeightSS);
		lblWeightSS.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWeightSS.setFont(new Font("Calibri", Font.PLAIN, 16));

		txtFieldWeightSS = new JTextField();
		txtFieldWeightSS.setBounds(168, 137, 126, 26);
		panel.add(txtFieldWeightSS);
		txtFieldWeightSS.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldWeightSS.setToolTipText("Weight of Single Stream in pounds");
		txtFieldWeightSS.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldWeightSS.setColumns(10);

		JLabel lblTrashOutWeight = new JLabel("Trash out weight");
		lblTrashOutWeight.setBounds(13, 170, 129, 20);
		panel.add(lblTrashOutWeight);
		lblTrashOutWeight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTrashOutWeight.setFont(new Font("Calibri", Font.PLAIN, 16));

		txtFieldTrashoutWeight = new JTextField();
		txtFieldTrashoutWeight.setBounds(168, 167, 126, 26);
		panel.add(txtFieldTrashoutWeight);
		txtFieldTrashoutWeight.setToolTipText("Weight of Cardboard in pounds");
		txtFieldTrashoutWeight.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldTrashoutWeight.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldTrashoutWeight.setColumns(10);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(168, 83, 163, 20);
		panel.add(dateChooser);
		
		JLabel lblLbs = new JLabel("LBs");
		lblLbs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLbs.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblLbs.setBounds(304, 143, 27, 20);
		panel.add(lblLbs);
		
		JLabel label = new JLabel("LBs");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Calibri", Font.PLAIN, 16));
		label.setBounds(304, 170, 27, 20);
		panel.add(label);
		
		JPanel jPanelButtons = new JPanel();
		jPanelButtons.setBounds(20, 225, 515, 46);
		frmRevolutionSystems_Steamboat.getContentPane().add(jPanelButtons);
		jPanelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		jPanelButtons.add(btnSubmit);

		JButton btnClear = new JButton("Clear");
		jPanelButtons.add(btnClear);

//Action Listener method for button Clear. 

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtFieldOtherCustomerName.setText("");
				txtFieldTicketNumber.setText("");
				txtFieldWeightSS.setText("");
				txtFieldTrashoutWeight.setText("");
				cmbobxMaterialType.setSelectedIndex(0);
				comboBoxCustomerName.setSelectedIndex(0);
			}
		});

		btnClear.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));

		JButton btnExit = new JButton("Exit");
		jPanelButtons.add(btnExit);

//Action Listener method for Exit button

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Confirm Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (reply == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else
					txtFieldTicketNumber.requestFocus();
			}
		});
		btnExit.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		////////////////////////
//Action Listener Method for Submit Button.
		///////////////////

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					// Methods in button click action listener events.

					ConnectionToSql();

					if (validation()) {
						if (review()) {
							inputData(); // Calling Insertion Method to insert Validated inputs into SQL.
							InsertMaterialData();
						}
					}
				}

				catch (ClassNotFoundException | SQLException e1) {

					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Select Customer Name

		objCustomerName = comboBoxCustomerName.getSelectedItem();

		//////////////////////
		// Get the Selected value of Material.
		///////////////////////

		//////////////////////////////////////
		// Condition to disabled unnecessary Text inputs.
		//////////////////////////////////////

	}// End of Initialize Constructor

	public void ConnectionToSql() throws ClassNotFoundException, SQLException {

		try {
			Class.forName(JDBC_Driver);
			aConnection = DriverManager.getConnection(dbURL, userName, "");
			aStatement = aConnection.createStatement();

			/*
			 * JOptionPane.showMessageDialog(null, "Connected To SQL Database", "Success",
			 * JOptionPane.INFORMATION_MESSAGE);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// End of ConnectionToSql method

	public boolean validation() throws SQLException, ParseException {
		objCustomerName = comboBoxCustomerName.getSelectedItem();
		objMaterialType = cmbobxMaterialType.getSelectedItem();
		ticketNumber = txtFieldTicketNumber.getText();
		
		SimpleDateFormat sdfo = new SimpleDateFormat("yyyy/MM/dd");

		String dtChooser = sdfo.format(dateChooser.getDate());
		String todaysDate = sdfo.format(new Date());
		
		
		if(dtChooser.compareTo(todaysDate)>0) {
			JOptionPane.showMessageDialog(null, "Date can not be a future date", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (comboBoxCustomerName.getSelectedIndex() == 3) {
			if (!txtFieldOtherCustomerName.getText().matches("[a-zA-Z0-9]+")) {
				JOptionPane.showMessageDialog(null, "Please Enter Customer Name in Other", "No null values allowed",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}

		if (!ticketNumber.matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Please Enter valid Ticket number in digits.", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		else if (objMaterialType.toString().equals("Select Material")) {
			JOptionPane.showMessageDialog(null, "Please Select Valid Material", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		else if ((cmbobxMaterialType.getSelectedIndex() == 1)) {
			if (!txtFieldWeightSS.getText().matches("[0-9]+")) {
				JOptionPane.showMessageDialog(null, "Please enter only digits for the Weight of Single Stream", "Warning Message",
						JOptionPane.WARNING_MESSAGE);
				weightOCC = 0;
				return false;
			}
		}

		else if ((cmbobxMaterialType.getSelectedIndex() == 2)) {
			if (!txtFieldTrashoutWeight.getText().matches("[0-9]+")) {
				JOptionPane.showMessageDialog(null, "Please enter only digits for the Weight of Trash", "Warning Message",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		
		else if ((cmbobxMaterialType.getSelectedIndex() == 3)) {
			if (!txtFieldWeightSS.getText().matches("[0-9]+")) {
				JOptionPane.showMessageDialog(null, "Please enter only digits for the Weight of OCC", "Warning Message",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		
		else {
			return true;
		}

		return true;

	}// End of method Validation. It calls in Submit button action event.

	public void inputData() {

		if ((cmbobxMaterialType.getSelectedIndex() == 1)) {
			weightSS = Double.parseDouble(txtFieldWeightSS.getText());
			weightOCC = 0;
			trashOutWeightInLb = 0;
			trashOutWeightInTons = 0;
			grosseightInLb = weightSS;
			grossWeightInTons = grosseightInLb / 2000.0;
		}

		if ((cmbobxMaterialType.getSelectedIndex() == 2)) {
			//weightOCC = Double.parseDouble(txtFieldWeightOCC.getText());
			trashOutWeightInLb = Double.parseDouble(txtFieldTrashoutWeight.getText());
			trashOutWeightInTons = trashOutWeightInLb / 2000.0;
			weightSS = 0;
			weightOCC = 0;
			grosseightInLb = 0;
			grossWeightInTons = 0;
		}
		
		if ((cmbobxMaterialType.getSelectedIndex() == 3)) {
			weightOCC = Double.parseDouble(txtFieldWeightSS.getText());
			trashOutWeightInLb = 0;
			trashOutWeightInTons = 0.0;
			weightSS = 0;
			grosseightInLb = weightOCC;
			grossWeightInTons = grosseightInLb/2000.0;
		}

		/*
		 * else if ((cmbobxMaterialType.getSelectedIndex() == 3)) { weightSS =
		 * Double.parseDouble(txtFieldWeightSS.getText()); //weightOCC =
		 * Double.parseDouble(txtFieldWeightOCC.getText()); grosseightInLb = weightSS +
		 * weightOCC; grossWeightInTons = grosseightInLb / 2000.0;
		 * 
		 * trashOutWeightInLb = 0; trashOutWeightInTons = 0; }
		 * 
		 * else { trashOutWeightInLb =
		 * Double.parseDouble(txtFieldTrashoutWeight.getText()); trashOutWeightInTons =
		 * trashOutWeightInLb / 2000.0; weightSS = 0; weightOCC = 0; grosseightInLb = 0;
		 * grossWeightInTons = 0; }
		 */
		
		if (comboBoxCustomerName.getSelectedIndex() == 3) {
			objCustomerName = txtFieldOtherCustomerName.getText();
		}

	}// End of Input data()

	public void InsertMaterialData() throws SQLException {
		
		java.sql.Date date = new java.sql.Date(dateChooser.getDate().getTime());

		String qryInsert = "  Insert into [Steamboat].[dbo].[IncomingMaterial] (CustomerName,WeightTicketNumber,Date,Time,MatrialType,[WeightSS(lb)],[WeightOCC(lb)],[GrossWeight(lb)],[GrossWeight(tons)],[TrashOutWeight(lb)],[TrashOutWeight(tons)])\r\n"
				+ "  Values ('" + objCustomerName + "'," + ticketNumber + ",'" + date + "','0:00', ' " + objMaterialType + " ', " + weightSS + " ," + weightOCC + " , "
				+ grosseightInLb + " , " + grossWeightInTons + ", " + trashOutWeightInLb + ", " + trashOutWeightInTons
				+ ") ";

		aStatement.executeUpdate(qryInsert);
		JOptionPane.showMessageDialog(null, "Data entered successfully");

		txtFieldOtherCustomerName.setText("");
		txtFieldTicketNumber.setText("");
		txtFieldWeightSS.setText("");
		//txtFieldWeightOCC.setText("");
		txtFieldTrashoutWeight.setText("");
		cmbobxMaterialType.setSelectedIndex(0);
		comboBoxCustomerName.setSelectedIndex(0);

	}// End of Method SQL Exception

//Review entered data. 

	public boolean review() {

		java.sql.Date date = new java.sql.Date(dateChooser.getDate().getTime());

		if (cmbobxMaterialType.getSelectedIndex() == 1) {
			grosseightInLb = Double.parseDouble(txtFieldWeightSS.getText());
		}

		if (cmbobxMaterialType.getSelectedIndex() == 2) {
			grosseightInLb = Double.parseDouble(txtFieldTrashoutWeight.getText());
		}
		
		if (cmbobxMaterialType.getSelectedIndex() == 3) {
			grosseightInLb = Double.parseDouble(txtFieldWeightSS.getText());
		}
		
		if (comboBoxCustomerName.getSelectedIndex() == 3) {
			objCustomerName = txtFieldOtherCustomerName.getText();
		}

		int reply = JOptionPane.showConfirmDialog(null,
				"Review entered data : \n\n" + " Weight Ticket Number : " + txtFieldTicketNumber.getText()
						+ "\n Customer Name : " + objCustomerName + "\n Material Type : "
						+ cmbobxMaterialType.getSelectedItem() + "\n Date : " + date + "\n Weight : " + grosseightInLb,
				"Confirm Submit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		if (reply == JOptionPane.YES_OPTION) {
			return true;
		} else
			return false;

	}
}// End of class
