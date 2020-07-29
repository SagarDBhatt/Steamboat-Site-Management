package SteamboatSprings.SiteManagementAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectionToSQL {

	private static String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
	private static String dbURL = "jdbc:sqlserver://FLEXBIZHTAPP\\SQLEXPRESS:1433;databaseName=Steamboat;integratedSecurity=true";
	private static String userName = "CTLIO/Sbhatt";
	private static Connection aConnection = null;
	private static Statement aStatement = null;
	
	
	
	public static Connection getaConnection() {
		return aConnection;
	}



	public static void setaConnection(Connection aConnection) {
		ConnectionToSQL.aConnection = aConnection;
	}



	public static Statement getaStatement() {
		return aStatement;
	}



	public static void setaStatement(Statement aStatement) {
		ConnectionToSQL.aStatement = aStatement;
	}



	public static void ConnectionSql() throws ClassNotFoundException, SQLException {

		try {
			Class.forName(JDBC_Driver);
			aConnection = DriverManager.getConnection(dbURL, userName, "");
			aStatement = aConnection.createStatement();
			
			JOptionPane.showMessageDialog(null, "Connected To SQL Database", "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} 
		catch (Exception e) {
			e.printStackTrace();JOptionPane.showMessageDialog(null, "Unable to Connect SQL Database", "Error!!",
					JOptionPane.ERROR_MESSAGE);
		}

	}//End of ConnectionToSql method

}
