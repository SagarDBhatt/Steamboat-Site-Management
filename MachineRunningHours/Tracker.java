package SiteManagementAPI.MachineRunningHours;

import java.sql.*;

import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

public class Tracker {

	private static Connection aConnection = null;
	private static Statement aStatement = null;

	/**
	 * Method to connect with SQL Database
	 */
	public static void connectToSQL() {

		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		String dbURL = "jdbc:sqlserver://FLEXBIZHTAPP\\SQLEXPRESS:1433;databaseName=Steamboat;integratedSecurity=true";
		String userName = "CTLIO/Sbhatt";

		try {
			Class.forName(JDBC_DRIVER);
			aConnection = DriverManager.getConnection(dbURL, userName, "");
			aStatement = aConnection.createStatement();

			JOptionPane.showMessageDialog(null, "Connected to SQL instance");

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Class Not found!!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to Connect with SQL instance");
		}
	}// End of connectToSQL()

	/**
	 * Method to add data into SQL database into MachineHourTracker table.
	 */
	public static void insertToMachineHour(JDateChooser dateChooser, String clockHours, String comments) {

		java.sql.Date date = new java.sql.Date(dateChooser.getDate().getTime());

		String qryInsrtHoursTracker = "Insert into tblMachineHourTracker (TransactionID, ClockHours, Date, Comments, RunningHours)\r\n"
				+ "Values(  (Select ISNULL(MAX(TransactionID) + 1, 0) from tblMachineHourTracker) , "
				+ Double.parseDouble(clockHours) + " , '" + date + "', '" + comments + "', 20) ";

		try {
			aStatement.executeUpdate(qryInsrtHoursTracker);
			JOptionPane.showMessageDialog(null, "Data inserted successfully!!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to execute SQL Query!!!");
		}

	}// End of InsertTOMachineHour()

	public static void reviewData(JDateChooser dateChooser, String clockHours, String comments) {

		java.sql.Date date = null;
		boolean errorCapture=false;
		
		try {
			date = new java.sql.Date(dateChooser.getDate().getTime());
		} 
		catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Insert the valid Date!!");
			errorCapture = true;
		}

		if (!clockHours.matches("[0-9]+\\.[0-9]*")) {
			JOptionPane.showMessageDialog(null, "Insert valid Clock Hours. For Eg: 5034.7");
		}

		else if(errorCapture) {
			JOptionPane.showMessageDialog(null, "Insert the valid Date!!");
		}
		else {

			int reply = JOptionPane.showConfirmDialog(null,
					"Review Data \n" + "Clock Hours : " + Double.parseDouble(clockHours) + "\n" + "Date : " + date
							+ "\n" + "Comments : " + comments,
					"Confirm Review", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (reply == JOptionPane.YES_OPTION) {
				insertToMachineHour(dateChooser, clockHours, comments);
			}

		}
	}// End of reviewData()
}
