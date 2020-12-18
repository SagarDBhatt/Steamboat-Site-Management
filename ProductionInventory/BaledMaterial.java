package SteamboatSprings.SiteManagementAPI.ProductionInventory;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

import SteamboatSprings.SiteManagementAPI.CurrentInventory.CurrentInventoryCount;

public class BaledMaterial {

	private JFrame frmRevolutionSystems;
	private JTextField txtFieldWeight;

	JComboBox comboBoxBaledMaterial = new JComboBox();
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
//SQL Connection variables

	String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	String dbURL = "jdbc:sqlserver://FLEXBIZHTAPP\\SQLEXPRESS:1433;databaseName=Steamboat;IntegratedSecurity=true";
	String userName = "CTLIO/Sbhatt";

	Connection aConnection = null;
	Statement aStatement = null;

	ResultSet rsInsert = null;

//End of SQL connection Variables

//Application variables to receive data to insert into SQL database. 

	Object objBaledMaterialType, objNumberOfBales = 1;
	double weightOfBales = 0, baleWeightInTons = 0;

//End of Application Variables

//Create Current Inventory Object
	CurrentInventoryCount currentInventoryObject;

//End	
	
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaledMaterial window = new BaledMaterial();
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
	public BaledMaterial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmRevolutionSystems = new JFrame();
		frmRevolutionSystems.setIconImage(Toolkit.getDefaultToolkit().getImage(BaledMaterial.class
				.getResource("/windowBuilder/Resources/6cfcb4e9556799a6fcbf983aab9fab19-32bits-16.png")));
		frmRevolutionSystems.setTitle("Revolution Systems");
		frmRevolutionSystems.setBounds(100, 100, 345, 219);
		frmRevolutionSystems.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRevolutionSystems.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("280px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("113px"),
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("39px"),}));

		JPanel jPanelDataEntry = new JPanel();
		jPanelDataEntry.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Production Inventory Reporting", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frmRevolutionSystems.getContentPane().add(jPanelDataEntry, "2, 2, fill, fill");
		jPanelDataEntry.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

		JLabel lblMaterialName = new JLabel("Select Baled Material");
		jPanelDataEntry.add(lblMaterialName, "2, 2");
		lblMaterialName.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaterialName.setFont(new Font("Calibri", Font.PLAIN, 16));
		jPanelDataEntry.add(comboBoxBaledMaterial, "4, 2, 4, 1, fill, default");

		comboBoxBaledMaterial.setForeground(new Color(0, 0, 0));
		comboBoxBaledMaterial.setToolTipText("Baled Material");
		comboBoxBaledMaterial.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboBoxBaledMaterial.setModel(
				new DefaultComboBoxModel(new String[] {"OCC", "ONP", "HDPEN", "HDPEC", "PET", "TIN", "UBC", "Glass"}));
		comboBoxBaledMaterial.setSelectedIndex(0);

		// End

		JLabel lblWeight = new JLabel("Weight (in lbs)");
		jPanelDataEntry.add(lblWeight, "2, 4");
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeight.setToolTipText("Weight of Baled Material");
		lblWeight.setFont(new Font("Calibri", Font.PLAIN, 16));

		txtFieldWeight = new JTextField();
		jPanelDataEntry.add(txtFieldWeight, "4, 4, 4, 1, fill, default");
		txtFieldWeight.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldWeight.setToolTipText("Weight of Baled Material");
		txtFieldWeight.setColumns(10);

		JLabel lblDate = new JLabel("Date");
		jPanelDataEntry.add(lblDate, "2, 6, center, top");
		lblDate.setToolTipText("");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Calibri", Font.PLAIN, 16));

		dateChooser = new JDateChooser();
		jPanelDataEntry.add(dateChooser, "4, 6, 4, 1, fill, top");

		JPanel panel = new JPanel();
		frmRevolutionSystems.getContentPane().add(panel, "2, 4, fill, top");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnSubmit = new JButton("Submit");
		panel.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {

//ActionListener Event for "Submit" button

			public void actionPerformed(ActionEvent arg0) {

				try {
					ConnectToSql();

					if (validation()) {
						if (review()) {
							InsertToBaledMaterial();
							
							currentInventoryObject = new CurrentInventoryCount();
							currentInventoryObject.connection();
							currentInventoryObject.calcBaledMaterial(objBaledMaterialType.toString(),
									Integer.parseInt(objNumberOfBales.toString()), baleWeightInTons);
							currentInventoryObject.getCurrentInventory();
							currentInventoryObject.updateNewInventory();
						}
					}
					// InsertToTotalEmployeeHours();
				}

				catch (ClassNotFoundException | SQLException e) {

					JOptionPane.showMessageDialog(null, "Class Not Found inside Baled Material!!", "Error!!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSubmit.setFont(new Font("Calibri", Font.PLAIN, 16));

		JButton btnClear = new JButton("Clear");
		panel.add(btnClear);

		// Clear Button, action listener
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				comboBoxBaledMaterial.setSelectedIndex(0);
				//comboBoxNumberOfBales.setSelectedIndex(0);
				txtFieldWeight.setText("");
			}
		});
		// End of Clear Button.

		btnClear.setFont(new Font("Calibri", Font.PLAIN, 16));

		// Exit Button, ActionListener
		JButton btnExit = new JButton("Exit");
		panel.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Confirm Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (reply == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else
					comboBoxBaledMaterial.requestFocus();

			}
		});
		// End of Exit button.

		btnExit.setFont(new Font("Calibri", Font.PLAIN, 16));

		// Selecting Baled Material.

		objBaledMaterialType = comboBoxBaledMaterial.getSelectedItem();

	}// End of Initalize() method.

//Connect to SQL Server. 

	public void ConnectToSql() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		aConnection = DriverManager.getConnection(dbURL, userName, "");
		aStatement = aConnection.createStatement();
		// JOptionPane.showMessageDialog(null, "Connection Successful!!!");

	}// End of method ConnectToSql().

//Executing query to insert material to Baled Material table in SQL. 

	public void InsertToBaledMaterial() throws SQLException {
		objBaledMaterialType = comboBoxBaledMaterial.getSelectedItem();
		// System.out.println(objBaledMaterialType);

		date = new java.sql.Date(dateChooser.getDate().getTime());
		
		//objNumberOfBales = comboBoxNumberOfBales.getSelectedItem();
		// System.out.println(objNumberOfBales);

		weightOfBales = Double.parseDouble(txtFieldWeight.getText());
		baleWeightInTons = weightOfBales / 2000.0;

		String qryInsert = "Insert into BaledMaterial (BaledMaterialId,MaterialType,NumberOfBales,Weight,Date,Time,GrossWeightInTons)\r\n"
				+ "	Values( (Select ISNULL(Max(BaledMaterialId) + 1,0) from BaledMaterial), ' " + objBaledMaterialType
				+ " ' , 1 , " + weightOfBales + " , '" + date + "' , '00:00', " + baleWeightInTons + " )";

		aStatement.executeUpdate(qryInsert);
		JOptionPane.showMessageDialog(null, "Baled Material entered successfully");

		comboBoxBaledMaterial.setSelectedIndex(0);
		// comboBoxNumberOfBales.setSelectedIndex(0);
		txtFieldWeight.setText("");

	}// End of InsertToBaledMaterial().

	public boolean validation() {

		if(dateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Please enter valid date", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if(comboBoxBaledMaterial.getSelectedIndex() == 7) {
			//System.out.println("Selecte material = " + comboBoxBaledMaterial.getSelectedItem());
			txtFieldWeight.setText("850");
		}
		
		SimpleDateFormat sdfo = new SimpleDateFormat("yyyy/MM/dd");
		String dtChooser = sdfo.format(dateChooser.getDate());
		String todaysDate = sdfo.format(new java.util.Date());
		
		if(dtChooser.compareTo(todaysDate)>0) {
			JOptionPane.showMessageDialog(null, "Date can not be a future date", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (txtFieldWeight.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please insert Weight");
			return false;
		} else {
			return true;
		}

	}// End of Validation()

	public boolean review() {

		int reply = JOptionPane.showConfirmDialog(null,
				"Review entered data : \n" + "\n Material Type : " + comboBoxBaledMaterial.getSelectedItem()
						+ "\n Date : " + new java.sql.Date(dateChooser.getDate().getTime()) + "\n Weight : "
						+ txtFieldWeight.getText() + " lbs ",
				"Confirm Submit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		if (reply == JOptionPane.YES_OPTION) {
			return true;
		} else
			return false;

	}// End of review()
}// End of class.
