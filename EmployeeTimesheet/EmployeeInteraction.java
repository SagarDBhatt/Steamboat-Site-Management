package SteamboatSprings.SiteManagementAPI.EmployeeTimesheet;

import java.sql.Date;

import com.toedter.calendar.JDateChooser;

import SteamboatSprings.SiteManagementAPI.ConnectionToSQL;

public class EmployeeInteraction {
	
	public static void insertEmployeeData(String empName, Double workHours, JDateChooser dateChooser) {
		java.sql.Date workDate = new java.sql.Date(dateChooser.getDate().getTime());
		
		
	}

}
