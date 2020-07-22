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
		connection();
		// calcBaledMaterial();
		calcSoldMaterial();
	}
	
	//Class level Map values.
	static Map<String, Integer> soldMaterialBales = new TreeMap<String, Integer>();
	static Map<String, Double> soldMaterialWeight = new TreeMap<String, Double>();
	
	static Map<String, Integer> materialBales = new TreeMap<String, Integer>();
	static Map<String, Double> materialWeight = new TreeMap<String, Double>();

	public static void connection() {
		IncomingMaterial object = new IncomingMaterial();
		try {
			object.ConnectionToSql();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Class Not Found!!", "Error!!", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void calcBaledMaterial() {

		/**
		 * Map Material type and appropriate Bales Count and Weight in tons.
		 */
		

		String qryTotalIncomingMaterial = "  Select MaterialType, sum(NumberOfBales) AS TotalIncmongBales, Sum(GrossWeightInTons) AS TotalIncomingWeight\r\n"
				+ "  From BaledMaterial \r\n" + "  Group by MaterialType\r\n" + "  Order By MaterialType";

		try {

			ResultSet rsTotalIncoming = IncomingMaterial.getaStatement().executeQuery(qryTotalIncomingMaterial);

			while (rsTotalIncoming.next()) {

				materialBales.put(rsTotalIncoming.getString("MaterialType"),
						rsTotalIncoming.getInt("TotalIncmongBales"));
				materialWeight.put(rsTotalIncoming.getString("MaterialType"),
						rsTotalIncoming.getDouble("TotalIncomingWeight"));
			}

			/*
			 * System.out.println("Bales"); for(String mtr : materialBales.keySet()) {
			 * System.out.println("Material = " + mtr + "\t Bales = " +
			 * materialBales.get(mtr)); }
			 * 
			 * System.out.println("MaterialType \t Stats"); for(String s :
			 * materialWeight.keySet()) { System.out.println(s + materialWeight.get(s)); }
			 */

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to Execute TotalIncoming Material Query", "Error!!",
					JOptionPane.ERROR_MESSAGE);
		} 

	}// End of calcBaledMaterial()

	public static void calcSoldMaterial() {

		

		String qryTotalSoldMaterial = "Select MaterialType, Sum(NumberOfBales) AS TotalSoldBales, Sum([WeightOfMaterial (in tons)]) AS TotalSoldWeight\r\n"
				+ "  From SoldMaterial\r\n" + "  Group by MaterialType";

		try {
			ResultSet rsSoldInventory = IncomingMaterial.getaStatement().executeQuery(qryTotalSoldMaterial);

			while (rsSoldInventory.next()) {
				soldMaterialBales.put(rsSoldInventory.getString("MaterialType"),
						rsSoldInventory.getInt("TotalSoldBales"));
				soldMaterialWeight.put(rsSoldInventory.getString("MaterialType"),
						rsSoldInventory.getDouble("TotalSoldWeight"));
			}

			for (String s : soldMaterialBales.keySet())
				System.out.println(s + "\t" + soldMaterialBales.get(s));

			for (String s : soldMaterialWeight.keySet())
				System.out.println(s + "\t" + soldMaterialWeight.get(s));

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to Execute Sold Material Query", "Error!!",
					JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException n) {
			JOptionPane.showMessageDialog(null, "No result from Query!", "Error!!", JOptionPane.ERROR_MESSAGE);
		}

	}// End of calcSoldMaterial()

}// End of class
