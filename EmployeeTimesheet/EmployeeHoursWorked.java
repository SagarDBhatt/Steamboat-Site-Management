package SteamboatSprings.SiteManagementAPI.EmployeeTimesheet;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EmployeeHoursWorked {
	
	
	static JComboBox comboBoxEmpName = new JComboBox();
	static JComboBox comboBoxHoursWorked = new JComboBox();
	static JComboBox comboBoxBreakTime = new JComboBox();
	
	static Object objEmpName = comboBoxEmpName.getSelectedItem();
	static Object objHoursWorked = comboBoxHoursWorked.getSelectedItem();
	static Object objBreakTime = comboBoxBreakTime.getSelectedItem();
	
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
		static Statement aStatement = null;

		ResultSet rsInsert = null;
		
//End of SQL connection Variables

	private JFrame frmRevolutionSystems;
	private static JTextField txtFieldDateEmp;
	private static JTextField txtFieldTimeEmp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeHoursWorked window = new EmployeeHoursWorked();
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
	public EmployeeHoursWorked() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRevolutionSystems = new JFrame();
		frmRevolutionSystems.setIconImage(Toolkit.getDefaultToolkit().getImage(EmployeeHoursWorked.class.getResource("/windowBuilder/Resources/RS_logo.jpg")));
		frmRevolutionSystems.setType(Type.POPUP);
		frmRevolutionSystems.setTitle("Revolution Systems");
		frmRevolutionSystems.setBounds(100, 100, 554, 373);
		frmRevolutionSystems.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRevolutionSystems.getContentPane().setLayout(null);
		
		JLabel lblEmployeeWorkHours = new JLabel("Employee Work Hours");
		lblEmployeeWorkHours.setBounds(113, 11, 230, 30);
		lblEmployeeWorkHours.setFont(new Font("Calibri", Font.BOLD, 24));
		lblEmployeeWorkHours.setBackground(Color.LIGHT_GRAY);
		frmRevolutionSystems.getContentPane().add(lblEmployeeWorkHours);
		
		//JComboBox comboBoxEmpName = new JComboBox();
		comboBoxEmpName.setModel(new DefaultComboBoxModel(new String[] {"Ruth B Alcantar", "Yahaira R Cordero Alcantar", "Maynor J Galeano Martinez", "Danir Hernandez", "Wilmer J Sevilla Hernandez"}));
		comboBoxEmpName.setBounds(162, 63, 131, 20);
		frmRevolutionSystems.getContentPane().add(comboBoxEmpName);
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblEmployeeName.setBackground(Color.LIGHT_GRAY);
		lblEmployeeName.setBounds(37, 58, 115, 30);
		frmRevolutionSystems.getContentPane().add(lblEmployeeName);
		
		JLabel lblHoursWorked = new JLabel("Hours Worked");
		lblHoursWorked.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblHoursWorked.setBackground(Color.LIGHT_GRAY);
		lblHoursWorked.setBounds(37, 111, 115, 30);
		frmRevolutionSystems.getContentPane().add(lblHoursWorked);
		
		//JComboBox comboBoxHoursWorked = new JComboBox();
		comboBoxHoursWorked.setModel(new DefaultComboBoxModel(new String[] {"1", "1.25", "1.5", "1.75", "2", "2.25", "2.5", "2.75", "3", "3.25", "3.5", "3.75", "4", "4.25", "4.5", "4.75", "5", "5.25", "6.5", "6.75", "7", "7.25", "7.5", "7.75", "8", ""}));
		comboBoxHoursWorked.setBounds(162, 116, 76, 20);
		frmRevolutionSystems.getContentPane().add(comboBoxHoursWorked);
		
		JLabel lblBreakTime = new JLabel("Break Time");
		lblBreakTime.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblBreakTime.setBackground(Color.LIGHT_GRAY);
		lblBreakTime.setBounds(37, 168, 115, 30);
		frmRevolutionSystems.getContentPane().add(lblBreakTime);
		
		//JComboBox comboBoxBreakTime = new JComboBox();
		comboBoxBreakTime.setModel(new DefaultComboBoxModel(new String[] {"0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5", "8"}));
		comboBoxBreakTime.setBounds(162, 173, 76, 20);
		frmRevolutionSystems.getContentPane().add(comboBoxBreakTime);
		
//Submit button Action Listener		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					ConnectToSql();
					
					if(review())
					{
					insertToEmployee();
					}
				} 
				
				catch (ClassNotFoundException | SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
		
//End of Submit Button
		
		
		button.setFont(new Font("Calibri", Font.PLAIN, 16));
		button.setBounds(10, 277, 150, 32);
		frmRevolutionSystems.getContentPane().add(button);
		
		JButton button_1 = new JButton("Clear");
		
//Clear button Action Listener		
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				comboBoxEmpName.setSelectedIndex(0);
				comboBoxHoursWorked.setSelectedIndex(0);
				comboBoxBreakTime.setSelectedIndex(0);
			}
		});
		button_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_1.setBounds(186, 277, 150, 32);
		frmRevolutionSystems.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Exit");
//Clear Button action Listener
		
//Exit button Action Listener
		button_2.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) 
			{
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (reply == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
				else
					comboBoxEmpName.requestFocus();
			}
		});
		
//Exit Button end
		button_2.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_2.setBounds(362, 277, 150, 32);
		frmRevolutionSystems.getContentPane().add(button_2);
		
		JLabel lblinHours = new JLabel("(in hours)");
		lblinHours.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblinHours.setBackground(Color.LIGHT_GRAY);
		lblinHours.setBounds(247, 111, 115, 30);
		frmRevolutionSystems.getContentPane().add(lblinHours);
		
		JLabel label = new JLabel("(in hours)");
		label.setFont(new Font("Calibri", Font.PLAIN, 12));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(248, 168, 115, 30);
		frmRevolutionSystems.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Date");
		label_1.setToolTipText("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		label_1.setBounds(10, 220, 120, 25);
		frmRevolutionSystems.getContentPane().add(label_1);
		
		txtFieldDateEmp = new JTextField();
		txtFieldDateEmp.setToolTipText("System Date");
		txtFieldDateEmp.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldDateEmp.setColumns(10);
		txtFieldDateEmp.setBounds(160, 221, 120, 23);
		frmRevolutionSystems.getContentPane().add(txtFieldDateEmp);
		
		JLabel label_2 = new JLabel("Time");
		label_2.setToolTipText("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Calibri", Font.PLAIN, 16));
		label_2.setBounds(289, 220, 94, 25);
		frmRevolutionSystems.getContentPane().add(label_2);
		
		txtFieldTimeEmp = new JTextField();
		txtFieldTimeEmp.setToolTipText("System Time");
		txtFieldTimeEmp.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldTimeEmp.setColumns(10);
		txtFieldTimeEmp.setBounds(393, 221, 120, 23);
		frmRevolutionSystems.getContentPane().add(txtFieldTimeEmp);
		
		//Set System Date & time
		
				txtFieldDateEmp.setText(localDate);
				txtFieldTimeEmp.setText(localTime);

		//End of System Date & Time 
	}
	
//Connect to SQL Server. 
	
		public void ConnectToSql() throws ClassNotFoundException, SQLException
		{
			Class.forName(JDBC_DRIVER);
			aConnection = DriverManager.getConnection(dbURL, userName, "");
			aStatement = aConnection.createStatement();
			//JOptionPane.showMessageDialog(null, "Database Connection Successful!!!");
			
		}

//End of method ConnectToSql(). 
	
	
	public static void insertToEmployee() throws SQLException
	{	
		objEmpName = comboBoxEmpName.getSelectedItem();
		objHoursWorked = comboBoxHoursWorked.getSelectedItem();
		objBreakTime = comboBoxBreakTime.getSelectedItem();
		
		//System.out.println(objEmpName + " " + objHoursWorked + " " + objBreakTime);
		
		String insertToEmployee = " Insert into dbo.Employee (EmployeeID, EmpName, HoursWorked, BreakTime, Date, Time)\r\n" + 
				"  Values ( (Select ISNULL(Max(EmployeeID) + 1,0) from Employee) , ' "+objEmpName+" ', "+objHoursWorked+" , "+objBreakTime+", '"+txtFieldDateEmp.getText()+"',' "+txtFieldTimeEmp.getText()+" ')";
		
		aStatement.executeUpdate(insertToEmployee);
		JOptionPane.showMessageDialog(null, "Employee data entered Successfully");
		
		comboBoxEmpName.setSelectedIndex(0);
		comboBoxHoursWorked.setSelectedIndex(0);
		comboBoxBreakTime.setSelectedIndex(0);
	}
	
	
	public boolean review() {
		
		int reply = JOptionPane.showConfirmDialog(null, "Review entered data : \n" + 
														"\n Employee : " + comboBoxEmpName.getSelectedItem() +
														"\n Hours worked : " + comboBoxHoursWorked.getSelectedItem() +
														"\n Break Time : " + comboBoxBreakTime.getSelectedItem() +
														"\n Date : " + txtFieldDateEmp.getText() +
														"\n Time : " + txtFieldTimeEmp.getText() , "Confirm Submit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if (reply == JOptionPane.YES_OPTION)
		{
			return true;
		}
		else
			return false;
		
	}//End of Review()
	
}//End of Class
