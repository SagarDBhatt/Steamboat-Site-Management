package SteamboatSprings.SiteManagementAPI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

//import com.mysql.jdbc.ResultSet;

import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;

public class IncomingMaterial {

	private JFrame frmRevolutionSystems_Steamboat;
	private JTextField txtFieldTicketNumber;
	private JTextField txtFieldDate;
	private JTextField txtFieldTime;
	private JTextField txtFieldWeightSS;
	private JTextField txtFieldWeightOCC;

	JComboBox cmbobxMaterialType = new JComboBox();
	JComboBox comboBoxCustomerName = new JComboBox();

	Object objMaterialType;
	Object objCustomerName;

	public double weightSS=0, weightOCC=0, grosseightInLb=0, grossWeightInTons=0, trashOutWeightInLb=0, trashOutWeightInTons=0;
	String ticketNumber;

//Initiate system date and time to set in Text field
	
	LocalDate date = java.time.LocalDate.now();
	LocalTime time = java.time.LocalTime.now();
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
	String localTime = time.format(dtf); 
	
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	String localDate = date.format(dateFormatter);

//End of Date & time. 
	
	
	String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
	String dbURL = "jdbc:sqlserver://FLEXBIZHTAPP\\SQLEXPRESS:1433;databaseName=Steamboat;integratedSecurity=true";
	String userName = "CTLIO/Sbhatt";

	Connection aConnection = null;
	Statement aStatement = null;

	ResultSet rsInsert =null; 

	//SteamboatMethods SteamboatObject = new SteamboatMethods();
	//SteamboatMethods aSteamboatObj;
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
		frmRevolutionSystems_Steamboat.setIconImage(Toolkit.getDefaultToolkit().getImage(IncomingMaterial.class.getResource("/windowBuilder/Resources/RS_logo.jpg")));
		frmRevolutionSystems_Steamboat.setFont(new Font("Calibri", Font.PLAIN, 20));
		frmRevolutionSystems_Steamboat.setForeground(SystemColor.text);
		frmRevolutionSystems_Steamboat.setTitle("Revolution Systems");
		frmRevolutionSystems_Steamboat.setBounds(100, 100, 898, 495);
		frmRevolutionSystems_Steamboat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRevolutionSystems_Steamboat.getContentPane().setLayout(null);

		JLabel lblTicketNumber = new JLabel("Enter Ticket Number*");
		lblTicketNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTicketNumber.setToolTipText("Enter the Weight Ticket Number");
		lblTicketNumber.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTicketNumber.setBounds(10, 133, 157, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(lblTicketNumber);

		txtFieldTicketNumber = new JTextField();
		txtFieldTicketNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldTicketNumber.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldTicketNumber.setBounds(195, 133, 220, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(txtFieldTicketNumber);
		txtFieldTicketNumber.setColumns(10);

		JLabel lblDate = new JLabel("Date*");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblDate.setBounds(10, 201, 157, 20);
		frmRevolutionSystems_Steamboat.getContentPane().add(lblDate);

		txtFieldDate = new JTextField();
		txtFieldDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldDate.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldDate.setColumns(10);
		txtFieldDate.setBounds(195, 195, 220, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(txtFieldDate);

		JLabel lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTime.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTime.setBounds(543, 201, 82, 20);
		frmRevolutionSystems_Steamboat.getContentPane().add(lblTime);

		txtFieldTime = new JTextField();
		txtFieldTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldTime.setToolTipText("Enter the time of the Ticket");
		txtFieldTime.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldTime.setColumns(10);
		txtFieldTime.setBounds(635, 195, 220, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(txtFieldTime);

		JLabel lblMaterialType = new JLabel("Material Type*");
		lblMaterialType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaterialType.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblMaterialType.setBounds(10, 255, 157, 20);
		frmRevolutionSystems_Steamboat.getContentPane().add(lblMaterialType);

		JLabel lblWeightSS = new JLabel("Weight SS (in lb)");
		lblWeightSS.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWeightSS.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblWeightSS.setBounds(10, 315, 157, 20);
		frmRevolutionSystems_Steamboat.getContentPane().add(lblWeightSS);

		txtFieldWeightSS = new JTextField();
		txtFieldWeightSS.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldWeightSS.setToolTipText("Weight of Single Stream in pounds");
		txtFieldWeightSS.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldWeightSS.setColumns(10);
		txtFieldWeightSS.setBounds(195, 309, 220, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(txtFieldWeightSS);

		JLabel lblWeightOCC = new JLabel("Weight OCC (in lb)");
		lblWeightOCC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWeightOCC.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblWeightOCC.setBounds(473, 315, 152, 20);
		frmRevolutionSystems_Steamboat.getContentPane().add(lblWeightOCC);

		txtFieldWeightOCC = new JTextField();
		txtFieldWeightOCC.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldWeightOCC.setToolTipText("Weight of Cardboard in pounds");
		txtFieldWeightOCC.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldWeightOCC.setColumns(10);
		txtFieldWeightOCC.setBounds(635, 309, 220, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(txtFieldWeightOCC);

//Set Local machine Date and time in TextField. 
		
		txtFieldDate.setText(localDate);
		txtFieldTime.setText(localTime);
		
//end of setting up Date and Time. 
		
		
		JLabel lblCustomerName = new JLabel("Customer Name*");
		lblCustomerName.setToolTipText("Enter the Weight Ticket Number");
		lblCustomerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomerName.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblCustomerName.setBounds(10, 65, 157, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(lblCustomerName);

		JLabel lblOtherCustomerName = new JLabel("Other");
		lblOtherCustomerName.setToolTipText("Enter the Weight Ticket Number");
		lblOtherCustomerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOtherCustomerName.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblOtherCustomerName.setBounds(531, 65, 94, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(lblOtherCustomerName);


		comboBoxCustomerName.setToolTipText("Select Customer Name");
		comboBoxCustomerName.setModel(new DefaultComboBoxModel(new String[] {"Twin Enviro", "Waste Management", "Other"}));
		comboBoxCustomerName.setFont(new Font("Calibri", Font.PLAIN, 16));
		comboBoxCustomerName.setMaximumRowCount(100);
		comboBoxCustomerName.setBounds(195, 71, 220, 20);
		frmRevolutionSystems_Steamboat.getContentPane().add(comboBoxCustomerName);

	//Select Customer Name
	
		objCustomerName = comboBoxCustomerName.getSelectedItem();
	
	//End of objCustomerName
	
		txtFieldOtherCustomerName = new JTextField();
		txtFieldOtherCustomerName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldOtherCustomerName.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldOtherCustomerName.setColumns(10);
		txtFieldOtherCustomerName.setBounds(635, 65, 220, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(txtFieldOtherCustomerName);

		JButton btnSubmit = new JButton("Submit");
		////////////////////////
//Action Listener Method for Submit Button.
		///////////////////
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					//Methods in button click action listener events. 

					ConnectionToSql(); 

					if (validation())
					{
						if(review()) 
						{
							inputData(); 	// Calling Insertion Method to insert Validated inputs into SQL.
							InsertMaterialData();
						}
					}	
				} 

				catch (ClassNotFoundException | SQLException e1) {

					e1.printStackTrace();
				}
			}
		});


		btnSubmit.setBounds(124, 396, 102, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(btnSubmit);

		JButton btnClear = new JButton("Clear");
		
//Action Listener method for button Clear. 
		
		btnClear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				txtFieldOtherCustomerName.setText("");
				txtFieldTicketNumber.setText("");
				txtFieldWeightSS.setText("");
				txtFieldWeightOCC.setText("");
				txtFieldTrashoutWeight.setText("");
				cmbobxMaterialType.setSelectedIndex(0);
				comboBoxCustomerName.setSelectedIndex(0);
			}
		});
		
		
		btnClear.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnClear.setBounds(251, 396, 102, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(btnClear);

		JButton btnCancel = new JButton("Exit");
		
//Action Listener method for Exit button
		
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (reply == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
				else
					txtFieldTicketNumber.requestFocus();
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCancel.setBounds(378, 396, 102, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(btnCancel);

		JLabel lblNewLabel = new JLabel("Incoming Material Information");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(197, 11, 276, 30);
		frmRevolutionSystems_Steamboat.getContentPane().add(lblNewLabel);


		cmbobxMaterialType.setModel(new DefaultComboBoxModel(new String[] {"Select Material", "Single Stream", "OCC", "Mixed", "Trash out"}));
		cmbobxMaterialType.setFont(new Font("Calibri", Font.PLAIN, 16));
		cmbobxMaterialType.setBounds(195, 255, 220, 20);
		frmRevolutionSystems_Steamboat.getContentPane().add(cmbobxMaterialType);
		
		JLabel lblTrashOutWeight = new JLabel("Trash out weight (in lb)");
		lblTrashOutWeight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTrashOutWeight.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTrashOutWeight.setBounds(473, 261, 152, 20);
		frmRevolutionSystems_Steamboat.getContentPane().add(lblTrashOutWeight);
		
		txtFieldTrashoutWeight = new JTextField();
		txtFieldTrashoutWeight.setToolTipText("Weight of Cardboard in pounds");
		txtFieldTrashoutWeight.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldTrashoutWeight.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtFieldTrashoutWeight.setColumns(10);
		txtFieldTrashoutWeight.setBounds(635, 255, 220, 33);
		frmRevolutionSystems_Steamboat.getContentPane().add(txtFieldTrashoutWeight);

		//////////////////////
		//Get the Selected value of Material.
		///////////////////////


		//////////////////////////////////////
		//Condition to disabled unnecessary Text inputs. 
		//////////////////////////////////////



	}//End of Initialize Constructor

	public void ConnectionToSql() throws ClassNotFoundException, SQLException {

		try {
			Class.forName(JDBC_Driver);
			aConnection = DriverManager.getConnection(dbURL, userName, "");
			aStatement = aConnection.createStatement();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}//End of ConnectionToSql method

	public boolean validation() throws SQLException
	{
		objCustomerName =comboBoxCustomerName.getSelectedItem();
		objMaterialType = cmbobxMaterialType.getSelectedItem();
		ticketNumber = txtFieldTicketNumber.getText();

		if (comboBoxCustomerName.getSelectedIndex() == 2 )
		{
			if (!txtFieldOtherCustomerName.getText().matches("[a-zA-Z]+"))
			{
				JOptionPane.showMessageDialog(null,"Please Enter Customer Name in Other", "No null values allowed",JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}

		if(!ticketNumber.matches("[0-9]+"))
		{
			JOptionPane.showMessageDialog(null,"Please Enter valid Ticket number in digits.","Warning Message",JOptionPane.WARNING_MESSAGE); 
			return false;
		}

		else if (objMaterialType.toString().equals("Select Material"))
		{
			JOptionPane.showMessageDialog(null, "Please Select Valid Material", "Warning Message", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		
		else if((cmbobxMaterialType.getSelectedIndex() == 1))
			{
				if(!txtFieldWeightSS.getText().matches("[0-9]+")) 
				{
					JOptionPane.showMessageDialog(null, "Please enter only digits in Weight of SS", "Warning Message", JOptionPane.WARNING_MESSAGE);
					weightOCC = 0;
					return false;
				}
			}

		else if ((cmbobxMaterialType.getSelectedIndex() == 2))
			{
				if(!txtFieldWeightOCC.getText().matches("[0-9]+"))
				{
					JOptionPane.showMessageDialog(null, "Please enter only digits in Weight of OCC", "Warning Message", JOptionPane.WARNING_MESSAGE);
					weightSS = 0;
					return false;
				}
			}

		else if ((cmbobxMaterialType.getSelectedIndex() == 3))
		{
			if(!txtFieldWeightSS.getText().matches("[0-9]+") || !txtFieldWeightOCC.getText().matches("[0-9]+"))
			{
				JOptionPane.showMessageDialog(null, "Please enter only digits in Weight of SS and OCC", "Warning Message", JOptionPane.WARNING_MESSAGE);
				return false;
			}

		} 
		
		else if ((cmbobxMaterialType.getSelectedIndex() == 4))
		{
			if(!txtFieldTrashoutWeight.getText().matches("[0-9]+"))
			{
				JOptionPane.showMessageDialog(null, "Please enter only digits in Weight of Trash", "Warning Message", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		
		else 
		{
			return true;
		}
		
		return true;

	}//End of method Validation. It calls in Submit button action event.

	public void inputData() {
		
		if ((cmbobxMaterialType.getSelectedIndex() == 1))
		{
			weightSS = Double.parseDouble(txtFieldWeightSS.getText());
			weightOCC = 0;
			trashOutWeightInLb=0;
			trashOutWeightInTons=0;
			grosseightInLb = weightSS;
			grossWeightInTons = grosseightInLb / 2000.0;
		}
		
		else if ((cmbobxMaterialType.getSelectedIndex() == 2))
		{
			weightOCC = Double.parseDouble(txtFieldWeightOCC.getText()); 
			weightSS = 0;
			trashOutWeightInLb=0;
			trashOutWeightInTons=0;
			grosseightInLb = weightOCC;
			grossWeightInTons = grosseightInLb / 2000.0;
		}
		
		else if ((cmbobxMaterialType.getSelectedIndex() == 3))
		{
			weightSS = Double.parseDouble(txtFieldWeightSS.getText());
			weightOCC = Double.parseDouble(txtFieldWeightOCC.getText());
			grosseightInLb = weightSS + weightOCC;
			grossWeightInTons = grosseightInLb / 2000.0;
			
			trashOutWeightInLb=0;
			trashOutWeightInTons=0;
		}
		
		else
		{
			trashOutWeightInLb = Double.parseDouble(txtFieldTrashoutWeight.getText()); 
			trashOutWeightInTons = trashOutWeightInLb / 2000.0;
			weightSS=0;
			weightOCC=0;
			grosseightInLb=0;
			grossWeightInTons=0;
		}
		
		if (comboBoxCustomerName.getSelectedIndex() == 2)
		{
			objCustomerName = txtFieldOtherCustomerName.getText();
		}

		 
	}//End of Input data()
	
	/*
	 * public class MyNumericVerifier extends InputVerifier {
	 * 
	 * @Override public boolean verify(JComponent input) { String text =
	 * ((JTextField) input).getText(); try { Double.parseDouble(text); } catch
	 * (NumberFormatException e) { return false; }
	 * 
	 * return true; } }
	 */

	//InputVerifier verifier = new MyNumericVerifier();


	public void InsertMaterialData() throws SQLException {

		String qryInsert = "  Insert into [Steamboat].[dbo].[IncomingMaterial] (CustomerName,WeightTicketNumber,Date,Time,MatrialType,[WeightSS(lb)],[WeightOCC(lb)],[GrossWeight(lb)],[GrossWeight(tons)],[TrashOutWeight(lb)],[TrashOutWeight(tons)])\r\n" + 
				"  Values ('"+ objCustomerName +"',"+ ticketNumber +",'"+ txtFieldDate.getText() +"','"+ txtFieldTime.getText() +"', ' "+ objMaterialType +" ', "+ weightSS +" ,"+ weightOCC +" , "+ grosseightInLb +" , "+grossWeightInTons+", "+ trashOutWeightInLb +", "+ trashOutWeightInTons +") ";

		aStatement.executeUpdate(qryInsert);
		JOptionPane.showMessageDialog(null, "Data entered successfully");
		
		txtFieldOtherCustomerName.setText("");
		txtFieldTicketNumber.setText("");
		txtFieldWeightSS.setText("");
		txtFieldWeightOCC.setText("");
		txtFieldTrashoutWeight.setText("");
		cmbobxMaterialType.setSelectedIndex(0);
		comboBoxCustomerName.setSelectedIndex(0);
		

	}//End of Method SQL Exception
	
//Review entered data. 
	
	public boolean review() {
		
		if(cmbobxMaterialType.getSelectedIndex()==3) {
		grosseightInLb = Double.parseDouble(txtFieldWeightOCC.getText()) + Double.parseDouble(txtFieldWeightSS.getText());
		}

		if(cmbobxMaterialType.getSelectedIndex()==1) {
		grosseightInLb = Double.parseDouble(txtFieldWeightSS.getText());
		}
		
		if(cmbobxMaterialType.getSelectedIndex()==2) {
			grosseightInLb = Double.parseDouble(txtFieldWeightOCC.getText());
			}
		
		if(cmbobxMaterialType.getSelectedIndex()==4) {
			grosseightInLb = Double.parseDouble(txtFieldTrashoutWeight.getText());
			}
		if (comboBoxCustomerName.getSelectedIndex() == 2)
		{
			objCustomerName = txtFieldOtherCustomerName.getText();
		}
		
		int reply = JOptionPane.showConfirmDialog(null, "Review entered data : \n\n" + 
														" Weight Ticket Number : " + txtFieldTicketNumber.getText() +
														"\n Customer Name : " + objCustomerName +
														"\n Material Type : " + cmbobxMaterialType.getSelectedItem() +
														"\n Date : " + txtFieldDate.getText() +
														"\n Time : " + txtFieldTime.getText() + 
														"\n Weight : " + grosseightInLb, "Confirm Submit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if (reply == JOptionPane.YES_OPTION)
		{
			return true;
		}
		else
			return false;
		
	}
	
	
}//End of class
