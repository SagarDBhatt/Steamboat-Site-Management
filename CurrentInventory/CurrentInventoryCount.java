package SteamboatSprings.SiteManagementAPI.CurrentInventory;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import SteamboatSprings.SiteManagementAPI.IncomingInventory.IncomingMaterial;

public class CurrentInventoryCount {

//	ONSJHJSA;
	public static void main(String[] Args) {
		calcIncomingMaterial();
	}
	
	public static void calcIncomingMaterial() {
		
		IncomingMaterial object = new IncomingMaterial();
		
		
		
		String qryTotalIncomingMaterial = "  Select MaterialType, sum(NumberOfBales) AS TotalIncmongBales, Sum(GrossWeightInTons) AS TotalIncomingWeight\r\n" + 
				"  From BaledMaterial \r\n" + 
				"  Group by MaterialType\r\n" + 
				"  Order By MaterialType";
		
		try {	
			
			object.ConnectionToSql();
			
			ResultSet rsTotalIncoming = IncomingMaterial.getaStatement().executeQuery(qryTotalIncomingMaterial);
			
			while(rsTotalIncoming.next()) {
				System.out.println("Material Type = " + rsTotalIncoming.getString("MaterialType")
									+ " \t Bales = " + rsTotalIncoming.getInt("TotalIncmongBales")
									+ "\t weight = " + rsTotalIncoming.getDouble("TotalIncomingWeight"));
			}
			
			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to Execute TotalIncoming Material Query", "Error!!", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Class Not Found!!", "Error!!", JOptionPane.ERROR_MESSAGE);

		}
		
	}

}//End of class
