package SteamboatSprings.SiteManagementAPI.CurrentInventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import SteamboatSprings.SiteManagementAPI.IncomingInventory.IncomingMaterial;

public class CurrentInventoryCount {

//	ONSJHJSA;
	public static void main(String[] Args) {
		calcBaledMaterial();
	}
	
	public static void calcBaledMaterial() {
		
		IncomingMaterial object = new IncomingMaterial();
		
		/**
		 * Map Material type and appropriate Bales Count and Weight in tons. 
		 */
		Map<String,Integer> materialBales = new TreeMap<String,Integer>();
		Map<String,Double> materialWeight = new TreeMap<String,Double>();
		
		String qryTotalIncomingMaterial = "  Select MaterialType, sum(NumberOfBales) AS TotalIncmongBales, Sum(GrossWeightInTons) AS TotalIncomingWeight\r\n" + 
				"  From BaledMaterial \r\n" + 
				"  Group by MaterialType\r\n" + 
				"  Order By MaterialType";
		
		try {	
			
			object.ConnectionToSql();
			
			ResultSet rsTotalIncoming = IncomingMaterial.getaStatement().executeQuery(qryTotalIncomingMaterial);
			
			while(rsTotalIncoming.next()) {
				
				materialBales.put(rsTotalIncoming.getString("MaterialType"),rsTotalIncoming.getInt("TotalIncmongBales"));
				materialWeight.put(rsTotalIncoming.getString("MaterialType"),rsTotalIncoming.getDouble("TotalIncomingWeight"));
			}

			/*
			 * System.out.println("Bales"); for(String mtr : materialBales.keySet()) {
			 * System.out.println("Material = " + mtr + "\t Bales = " +
			 * materialBales.get(mtr)); }
			 * 
			 * System.out.println("MaterialType \t Stats"); for(String s :
			 * materialWeight.keySet()) { System.out.println(s + materialWeight.get(s)); }
			 */
			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to Execute TotalIncoming Material Query", "Error!!", JOptionPane.ERROR_MESSAGE);
		} 
		catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Class Not Found!!", "Error!!", JOptionPane.ERROR_MESSAGE);

		}
		
	}//End of calcBaledMaterial()
	
	public static void calcSoldMaterial() {
			
		
		
	}//End of calcSoldMaterial()


}//End of class
