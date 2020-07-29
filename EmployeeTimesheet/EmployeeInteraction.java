package SteamboatSprings.SiteManagementAPI.EmployeeTimesheet;

import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import SteamboatSprings.SiteManagementAPI.ConnectionToSQL;

public class EmployeeInteraction {
	
	public static void insertEmployeeData(String empName, Double workHours, JDateChooser dateChooser) {
		java.sql.Date workDate = new java.sql.Date(dateChooser.getDate().getTime());
		
		String qryInsertToEmployeeTable = "  Insert into Employee(EmpName,HoursWorked,BreakTime,Date)\r\n" + 
				"  Values('"+empName+"',"+workHours+",1,'"+workDate+"')";
		
		try {
			ConnectionToSQL.getaStatement().execute(qryInsertToEmployeeTable);
			JOptionPane.showMessageDialog(null, "Data updated to Employee Table!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();JOptionPane.showMessageDialog(null, "Unable to update data into Employee Table!", "Error!!",
					JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

}
