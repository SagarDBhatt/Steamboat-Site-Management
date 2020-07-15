package SteamboatSprings.SiteManagementAPI.ProductionInventory;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class BaledMaterial {

	private JFrame frmRevolutionSystems;
	private JTextField txtFieldWeight;
	private JTextField txtFieldDate;
	private JTextField txtFieldTime;
	
	JComboBox comboBoxBaledMaterial = new JComboBox();
	JComboBox comboBoxNumberOfBales = new JComboBox();
	
//LocalDate and time objects to display system date and time. 
	
	LocalDate date = java.time.LocalDate.now();
	LocalTime time = java.time.LocalTime.now();
	
	DateTimeFormatter timeForamtter = DateTimeFormatter.ofPattern("HH:mm");
	String localTime = time.format(timeForamtter);

	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	String localDate = date.format(dateFormatter);
	
//End of declaring and initializing System date and time	
	
	
//SQL Connection variables
	
	String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	String dbURL = "jdbc:sqlserver://FLEXBIZHTAPP\\SQLEXPRESS:1433;databaseName=Steamboat;IntegratedSecurity=true";
	String userName = "CTLIO/Sbhatt";
	
	Connection aConnection = null;
	Statement aStatement = null;

	ResultSet rsInsert = null;
	
//End of SQL connection Variables
	
//Application variables to receive data to insert into SQL database. 
	
	Object objBaledMaterialType, objNumberOfBales;
	double weightOfBales = 0, baleWeightInTons = 0; 
	
//End of Application Variables
	
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
		frmRevolutionSystems.setIconImage(Toolkit.getDefaultToolkit().getImage(BaledMaterial.class.getResource("/windowBuilder/Resources/6cfcb4e9556799a6fcbf983aab9fab19-32bits-16.png")));
		frmRevolutionSystems.setTitle("Revolution Systems");
		frmRevolutionSystems.setBounds(100, 100, 627, 365);
		frmRevolutionSystems.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRevolutionSystems.getContentPane().setLayout(null);
		
		JLabel lblMaterialName = new JLabel("Select Baled Material");
		lblMaterialName.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaterialName.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblMaterialName.setBounds(27, 70, 150, 25);
		frmRevolutionSystems.getContentPane().add(lblMaterialName);
		
		
		comboBoxBaledMaterial.setForeground(new Color(0, 0, 0));
		comboBoxBaledMaterial.setToolTipText("Baled Material");
		comboBoxBaledMaterial.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboBoxBaledMaterial.setModel(new DefaultComboBoxModel(new String[] {"OCC", "ONP", "HDPEN", "HDPEC", "PET", "TIN", "UBC"}));
		comboBoxBaledMaterial.setSelectedIndex(0);
		comboBoxBaledMaterial.setBounds(187, 71, 120, 23);
		frmRevolutionSystems.getContentPane().add(comboBoxBaledMaterial);
		
		//Selecting Baled Material. 
		
			objBaledMaterialType = comboBoxBaledMaterial.getSelectedItem();
			
		//End
			
		JLabel lblNewLabel = new JLabel("Baled Material Information");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(124, 11, 289, 30);
		frmRevolutionSystems.getContentPane().add(lblNewLabel);
		
		JLabel lblNumberOfBales = new JLabel("Number of Bales");
		lblNumberOfBales.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfBales.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNumberOfBales.setBounds(331, 70, 120, 25);
		frmRevolutionSystems.getContentPane().add(lblNumberOfBales);
		
		
		comboBoxNumberOfBales.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		comboBoxNumberOfBales.setMaximumRowCount(50);
		comboBoxNumberOfBales.setToolTipText("Number of Bales");
		comboBoxNumberOfBales.setForeground(Color.BLACK);
		comboBoxNumberOfBales.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboBoxNumberOfBales.setBounds(461, 71, 120, 23);
		frmRevolutionSystems.getContentPane().add(comboBoxNumberOfBales);
		
		//Selecting Baled Material. 
		
			objNumberOfBales = comboBoxNumberOfBales.getSelectedItem();
		
		//End
		
		JLabel lblWeight = new JLabel("Weight (in lbs)");
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeight.setToolTipText("Weight of Baled Material");
		lblWeight.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblWeight.setBounds(27, 138, 120, 25);
		frmRevolutionSystems.getContentPane().add(lblWeight);
		
		txtFieldWeight = new JTextField();
		txtFieldWeight.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldWeight.setToolTipText("Weight of Baled Material");
		txtFieldWeight.setBounds(187, 139, 120, 23);
		frmRevolutionSystems.getContentPane().add(txtFieldWeight);
		txtFieldWeight.setColumns(10);
		
		JButton btnMeasureWeight = new JButton("Measure Weight");
		btnMeasureWeight.setBounds(327, 139, 150, 23);
		frmRevolutionSystems.getContentPane().add(btnMeasureWeight);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setToolTipText("");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblDate.setBounds(27, 188, 120, 25);
		frmRevolutionSystems.getContentPane().add(lblDate);
		
		txtFieldDate = new JTextField();
		txtFieldDate.setToolTipText("Weight of Baled Material");
		txtFieldDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldDate.setColumns(10);
		txtFieldDate.setBounds(187, 189, 120, 23);
		frmRevolutionSystems.getContentPane().add(txtFieldDate);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setToolTipText("");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTime.setBounds(27, 236, 120, 25);
		frmRevolutionSystems.getContentPane().add(lblTime);
		
		txtFieldTime = new JTextField();
		txtFieldTime.setToolTipText("Weight of Baled Material");
		txtFieldTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldTime.setColumns(10);
		txtFieldTime.setBounds(187, 237, 120, 23);
		frmRevolutionSystems.getContentPane().add(txtFieldTime);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {

//ActionListener Event for "Submit" button
			
			public void actionPerformed(ActionEvent arg0) {
				
				try 
				{
					ConnectToSql();
					
					if(validation()) 
					{
						if(review()) 
						{
							InsertToBaledMaterial();
						}
					}
					//InsertToTotalEmployeeHours();	
				} 
				
				catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnSubmit.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnSubmit.setBounds(29, 284, 150, 32);
		frmRevolutionSystems.getContentPane().add(btnSubmit);
		
		JButton btnClear = new JButton("Clear");

//Clear Button, action listener		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxBaledMaterial.setSelectedIndex(0);
				comboBoxNumberOfBales.setSelectedIndex(0);
				txtFieldWeight.setText("");
			}
		});
//End of Clear Button.		

		btnClear.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnClear.setBounds(205, 284, 150, 32);
		frmRevolutionSystems.getContentPane().add(btnClear);

//Exit Button, ActionListener
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					
					if (reply == JOptionPane.YES_OPTION)
					{
						System.exit(0);
					}
					else
						comboBoxBaledMaterial.requestFocus();
		
			}
		});
//End of Exit button. 
		
		
		btnExit.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnExit.setBounds(381, 284, 150, 32);
		frmRevolutionSystems.getContentPane().add(btnExit);
		
//Default system date and time in textFields of Date and Time. 
		
		txtFieldDate.setText(localDate);
		txtFieldTime.setText(localTime);
		
	}//End of Initalize() method. 
	
//Connect to SQL Server. 
	
	public void ConnectToSql() throws ClassNotFoundException, SQLException
	{
		Class.forName(JDBC_DRIVER);
		aConnection = DriverManager.getConnection(dbURL, userName, "");
		aStatement = aConnection.createStatement();
		//JOptionPane.showMessageDialog(null, "Connection Successful!!!");
		
	}//End of method ConnectToSql(). 

//Executing query to insert material to Baled Material table in SQL. 
	
	public void InsertToBaledMaterial() throws SQLException
	{
		objBaledMaterialType = comboBoxBaledMaterial.getSelectedItem();
		//System.out.println(objBaledMaterialType);
		
		objNumberOfBales = comboBoxNumberOfBales.getSelectedItem();
		//System.out.println(objNumberOfBales);
		
		weightOfBales = Double.parseDouble(txtFieldWeight.getText());
		baleWeightInTons = weightOfBales / 2000.0;
		
		String qryInsert = "Insert into BaledMaterial (BaledMaterialId,MaterialType,NumberOfBales,Weight,Date,Time,GrossWeightInTons)\r\n" + 
				"	Values( (Select ISNULL(Max(BaledMaterialId) + 1,0) from BaledMaterial), ' "+objBaledMaterialType+" ' , "+ objNumberOfBales+" , "+ weightOfBales +" , '"+txtFieldDate.getText()+"' , '"+txtFieldTime.getText()+"', "+ baleWeightInTons +" )";
		
		aStatement.executeUpdate(qryInsert);
		JOptionPane.showMessageDialog(null, "Baled Material entered successfully");
		
		comboBoxBaledMaterial.setSelectedIndex(0);
		comboBoxNumberOfBales.setSelectedIndex(0);
		txtFieldWeight.setText("");
		
		
	}//End of InsertToBaledMaterial(). 
	
	public boolean validation()
	{
		
		if(txtFieldWeight.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Please insert Weight");
			return false;	
		}
		else {
			return true;
		}
			
	}//End of Validation()
	
	
	public boolean review() {
		
		int reply = JOptionPane.showConfirmDialog(null, "Review entered data : \n" + 
														"\n Material Type : " + comboBoxBaledMaterial.getSelectedItem() +
														"\n Date : " + txtFieldDate.getText() +
														"\n Time : " + txtFieldTime.getText() + 
														"\n Weight : " + txtFieldWeight.getText() + " lbs " , "Confirm Submit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if (reply == JOptionPane.YES_OPTION)
		{
			return true;
		}
		else
			return false;
		
	}//End of review()
	
}//End of class. 
