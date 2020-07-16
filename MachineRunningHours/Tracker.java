package SteamboatSprings.SiteManagementAPI.MachineRunningHours;

import java.sql.*;

import javax.swing.JOptionPane;

public class Tracker {
	
	/**
	 * Method to connect with SQL Database
	 */
	public static void connectToSQL() {
		
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		String dbURL = "jdbc:sqlserver://FLEXBIZHTAPP\\SQLEXPRESS:1433;databaseName=Steamboat;integratedSecurity=true";
		String userName = "CTLIO/Sbhatt";
		
		
		try {
			Class.forName(JDBC_DRIVER);
			Connection aConnection = DriverManager.getConnection(dbURL,userName,"");
			Statement aStatement = aConnection.createStatement();
			
			JOptionPane.showMessageDialog(null, "Connected to SQL instance");

		} 
		catch (ClassNotFoundException e) {
			System.out.println("Class Not found!!!");
		} 
		catch (SQLException e) {
			System.out.println("Unable to Connect with SQL instance");
		}
	}//End of connectToSQL()
	
	/**
	 * Method to add data into SQL database into MachineHourTracker table. 
	 */
	public static void insertToMachineHour() {
		
		
	}

}
