package SteamboatSprings.SiteManagementAPI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.microsoft.sqlserver.jdbc.dataclassification.InformationType;
import com.mysql.cj.protocol.Message;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SellingMaterial {

	private JFrame frmRevolutionSystems;
	private JTextField txtFieldTicketNumber;
	private JTextField txtFieldWeightOfMaterial;
	private JTextField txtFieldDate;
	private JTextField txtFieldOtherCustomerName;
	private JTextField txtFieldTime;
	private JTextField txtFieldBOLNumber;
	
//LocalDate and time objects to display system date and time. 
	
			LocalDate date = java.time.LocalDate.now();
			LocalTime time = java.time.LocalTime.now();
			
			DateTimeFormatter timeForamtter = DateTimeFormatter.ofPattern("HH:mm");
			String localTime = time.format(timeForamtter);

			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
			String localDate = date.format(dateFormatter);
			
//End of declaring and initializing System date and time	
			
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
		
		Object objCustomerName,objMaterialType;
		
		double weightInLb, weightInTons;
		
//
	

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
		frmRevolutionSystems.setIconImage(Toolkit.getDefaultToolkit().getImage(SellingMaterial.class.getResource("/windowBuilder/Resources/6cfcb4e9556799a6fcbf983aab9fab19-32bits-16.png")));
		frmRevolutionSystems.setType(Type.POPUP);
		frmRevolutionSystems.setTitle("Revolution Systems");
		frmRevolutionSystems.setBounds(100, 100, 645, 496);
		frmRevolutionSystems.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRevolutionSystems.getContentPane().setLayout(null);
		
		JLabel lblSellingMaterialInformation = new JLabel("Selling Material Information");
		lblSellingMaterialInformation.setFont(new Font("Calibri", Font.BOLD, 20));
		lblSellingMaterialInformation.setBackground(Color.LIGHT_GRAY);
		lblSellingMaterialInformation.setBounds(149, 11, 276, 30);
		frmRevolutionSystems.getContentPane().add(lblSellingMaterialInformation);
		
		JLabel label = new JLabel("Customer Name*");
		label.setToolTipText("Enter the Weight Ticket Number");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Calibri", Font.PLAIN, 16));
		label.setBounds(10, 73, 157, 33);
		frmRevolutionSystems.getContentPane().add(label);
		
		JLabel lblTicketNumber = new JLabel("Weight Ticket Number*");
		lblTicketNumber.setToolTipText("Enter the Weight Ticket Number");
		lblTicketNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTicketNumber.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTicketNumber.setBounds(10, 141, 157, 33);
		frmRevolutionSystems.getContentPane().add(lblTicketNumber);
		
		
		comboBoxCustomerName.setModel(new DefaultComboBoxModel(new String[] {"Sage Recycling", "McKinley Paper", "Other"}));
		comboBoxCustomerName.setToolTipText("Select Customer Name");
		comboBoxCustomerName.setMaximumRowCount(100);
		comboBoxCustomerName.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboBoxCustomerName.setBounds(177, 73, 140, 20);
		frmRevolutionSystems.getContentPane().add(comboBoxCustomerName);
		
		txtFieldTicketNumber = new JTextField();
		txtFieldTicketNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldTicketNumber.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldTicketNumber.setColumns(10);
		txtFieldTicketNumber.setBounds(177, 135, 140, 33);
		frmRevolutionSystems.getContentPane().add(txtFieldTicketNumber);
		
		JLabel label_3 = new JLabel("Weight of Material");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Calibri", Font.PLAIN, 16));
		label_3.setBounds(10, 267, 157, 20);
		frmRevolutionSystems.getContentPane().add(label_3);
		
		txtFieldWeightOfMaterial = new JTextField();
		txtFieldWeightOfMaterial.setToolTipText("Weight of Single Stream in pounds");
		txtFieldWeightOfMaterial.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldWeightOfMaterial.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldWeightOfMaterial.setColumns(10);
		txtFieldWeightOfMaterial.setBounds(177, 255, 140, 33);
		frmRevolutionSystems.getContentPane().add(txtFieldWeightOfMaterial);
		
		
		comboBoxMaterialType.setModel(new DefaultComboBoxModel(new String[] {"OCC", "ONP", "HDPEN", "HDPEC", "PET", "TIN", "UBC"}));
		comboBoxMaterialType.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboBoxMaterialType.setBounds(177, 207, 140, 20);
		frmRevolutionSystems.getContentPane().add(comboBoxMaterialType);
		
		JLabel label_4 = new JLabel("Material Type*");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Calibri", Font.PLAIN, 16));
		label_4.setBounds(10, 207, 157, 20);
		frmRevolutionSystems.getContentPane().add(label_4);
		
		JLabel label_2 = new JLabel("Date*");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Calibri", Font.PLAIN, 16));
		label_2.setBounds(10, 325, 157, 20);
		frmRevolutionSystems.getContentPane().add(label_2);
		
		txtFieldDate = new JTextField();
		txtFieldDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldDate.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldDate.setColumns(10);
		txtFieldDate.setBounds(177, 313, 140, 33);
		frmRevolutionSystems.getContentPane().add(txtFieldDate);
		
		
		JLabel label_1 = new JLabel("Other");
		label_1.setToolTipText("Enter the Weight Ticket Number");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		label_1.setBounds(365, 73, 94, 33);
		frmRevolutionSystems.getContentPane().add(label_1);
		
		txtFieldOtherCustomerName = new JTextField();
		txtFieldOtherCustomerName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldOtherCustomerName.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldOtherCustomerName.setColumns(10);
		txtFieldOtherCustomerName.setBounds(469, 73, 115, 33);
		frmRevolutionSystems.getContentPane().add(txtFieldOtherCustomerName);
		
		JLabel lblTime = new JLabel("Time*");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTime.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTime.setBounds(377, 325, 82, 20);
		frmRevolutionSystems.getContentPane().add(lblTime);
		
		txtFieldTime = new JTextField();
		txtFieldTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldTime.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldTime.setColumns(10);
		txtFieldTime.setBounds(469, 313, 115, 33);
		frmRevolutionSystems.getContentPane().add(txtFieldTime);
		
		JLabel lblBolNumber = new JLabel("BOL Number*");
		lblBolNumber.setToolTipText("Enter the Weight Ticket Number");
		lblBolNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBolNumber.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblBolNumber.setBounds(351, 142, 108, 30);
		frmRevolutionSystems.getContentPane().add(lblBolNumber);
		
		txtFieldBOLNumber = new JTextField();
		txtFieldBOLNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldBOLNumber.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldBOLNumber.setColumns(10);
		txtFieldBOLNumber.setBounds(469, 135, 115, 33);
		frmRevolutionSystems.getContentPane().add(txtFieldBOLNumber);
		
//Action Listener Method for 'Submit' button
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {	
					ConnectToSQL();
					
					if (validation())
					{
						if(review())
						{
						insertSellingMaterial();
						}
					}	
				} 
				
				catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		
//End of Action Listener method. 
		
		button.setBounds(103, 391, 102, 33);
		frmRevolutionSystems.getContentPane().add(button);
		
//Clear button action listener		
		JButton button_1 = new JButton("Clear");
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
		
//End of Action listener
		
		button_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_1.setBounds(230, 391, 102, 33);
		frmRevolutionSystems.getContentPane().add(button_1);

//Exit button action listener
		JButton button_2 = new JButton("Exit");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (reply == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
				else
					txtFieldTicketNumber.requestFocus();	
			}
		});
		
//End of Exit button 		
		button_2.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_2.setBounds(357, 391, 102, 33);
		frmRevolutionSystems.getContentPane().add(button_2);
		
//Setting Date and Time in the textfield
		
		txtFieldDate.setText(localDate);
		txtFieldTime.setText(localTime);
		
		JLabel lblInPounds = new JLabel("(in lbs)");
		lblInPounds.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInPounds.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblInPounds.setBounds(327, 261, 45, 20);
		frmRevolutionSystems.getContentPane().add(lblInPounds);
		
		JLabel numberOfBales = new JLabel("Number of Bales*\r\n");
		numberOfBales.setHorizontalAlignment(SwingConstants.RIGHT);
		numberOfBales.setFont(new Font("Calibri", Font.PLAIN, 16));
		numberOfBales.setBounds(304, 207, 157, 20);
		frmRevolutionSystems.getContentPane().add(numberOfBales);
		
		JComboBox comboBoxNumberOfBales = new JComboBox();
		comboBoxNumberOfBales.setMaximumRowCount(20);
		comboBoxNumberOfBales.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"}));
		comboBoxNumberOfBales.setBounds(469, 207, 53, 20);
		frmRevolutionSystems.getContentPane().add(comboBoxNumberOfBales);

//End of Local Date and Time		
		
	}//End of Initialize() 

//Connect to SQL Server
	
	public void ConnectToSQL() throws ClassNotFoundException, SQLException {
		
		Class.forName(JDBC_DRIVER);
		aConneciton = DriverManager.getConnection(dbURL, userName, "");
		aStatement = aConneciton.createStatement();
		//JOptionPane.showMessageDialog(null, "Database connection successful");
		
	}//End of ConnectToSQL() method
	
//Insert Selling Material to SQL
	
	public void insertSellingMaterial() throws SQLException {

		weightInLb = Double.parseDouble(txtFieldWeightOfMaterial.getText());
		weightInTons = weightInLb / 2000.0;
		
		String qryInsertSellingMaterial = "Insert into SellingMaterial (CustomerID, CustomerName,WeightTicketNumber,BOLNumber,MaterialType,[WeightOfMaterial (in lb)],[WeightOfMaterial (in tons)],Date,Time)\r\n" + 
				"Values ( (Select ISNULL(Max(CustomerID) + 1,0) from SellingMaterial), '"+ objCustomerName +"', "+txtFieldTicketNumber.getText()+" , "+ txtFieldBOLNumber.getText() +" , ' "+ objMaterialType +" ', "+ txtFieldWeightOfMaterial.getText() +" , "+ weightInTons +",' "+ txtFieldDate.getText() +" ' , ' "+txtFieldTime.getText()+" ')";
		
		aStatement.executeUpdate(qryInsertSellingMaterial);
		
		JOptionPane.showMessageDialog(null, "Selling Material entered successfully");
		
		comboBoxCustomerName.setSelectedIndex(0);
		comboBoxMaterialType.setSelectedIndex(0);
		txtFieldOtherCustomerName.setText("");
		txtFieldTicketNumber.setText("");
		txtFieldBOLNumber.setText("");
		txtFieldWeightOfMaterial.setText("");
		
	}//End of Insert Method
	
//Validation method	
	public boolean validation() {
		
		objCustomerName = comboBoxCustomerName.getSelectedItem();
		objMaterialType = comboBoxMaterialType.getSelectedItem();
		
		if (comboBoxCustomerName.getSelectedIndex() == 2) {
			
			if (!txtFieldOtherCustomerName.getText().matches("[a-zA-Z]+")) {
				JOptionPane.showMessageDialog(null,"Please Enter Customer Name in Other", "No null values allowed",JOptionPane.WARNING_MESSAGE);
				return false;
			}
			objCustomerName = txtFieldOtherCustomerName.getText();
		}
		
		if(!txtFieldTicketNumber.getText().matches("[0-9]+"))
		{
			JOptionPane.showMessageDialog(null,"Please Enter valid Weight Ticket number in digits.","Warning Message",JOptionPane.WARNING_MESSAGE); 
			return false;
		}
		
		else if(!txtFieldBOLNumber.getText().matches("[0-9]+"))
		{
			JOptionPane.showMessageDialog(null,"Please Enter valid BOL Ticket number in digits.","Warning Message",JOptionPane.WARNING_MESSAGE); 
			return false;
		}
		
		else if (!txtFieldWeightOfMaterial.getText().matches("[0-9]+"))
		{
			JOptionPane.showMessageDialog(null,"Please Enter valid Weight of material in lbs","Warning Message",JOptionPane.WARNING_MESSAGE); 
			return false;
		}
		
		else 
			return true;
		
	}//End of Validation()
	
	public boolean review() {
		//grosseightInLb = Double.parseDouble(txtFieldWeightOCC.getText() + txtFieldWeightSS.getText());
		
		if (comboBoxCustomerName.getSelectedIndex() == 2) {
			objCustomerName = txtFieldOtherCustomerName.getText();
		}
		
		int reply = JOptionPane.showConfirmDialog(null, "Review entered data : \n\n" + 
														"Customer Name : " + objCustomerName +
														"\n Weight Ticket Number : " + txtFieldTicketNumber.getText() +
														"\n BOL number : " + txtFieldBOLNumber.getText() +
														"\n Material Type : " + comboBoxMaterialType.getSelectedItem() +
														"\n Date : " + txtFieldDate.getText() +
														"\n Time : " + txtFieldTime.getText() + 
														"\n Weight : " + txtFieldWeightOfMaterial.getText(), "Confirm Submit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if (reply == JOptionPane.YES_OPTION)
		{
			return true;
		}
		else
			return false;
		
	}//End of Review()
}//End of class
