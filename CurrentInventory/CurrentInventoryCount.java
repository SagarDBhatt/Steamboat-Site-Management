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
	/*
	 * public static void main(String[] Args) { connection(); calcBaledMaterial();
	 * calcSoldMaterial(); getCurrentInventory(); updateNewInventory(); }
	 */

	// Class level Map values.
	static Map<String, Integer> soldMaterialBalesYesterday = new TreeMap<String, Integer>();
	static Map<String, Double> soldMaterialWeightYesterday = new TreeMap<String, Double>();

	static Map<String, Integer> productionMaterialBalesYesterday = new TreeMap<String, Integer>();
	static Map<String, Double> productionMaterialWeightYesterday = new TreeMap<String, Double>();

	static Map<String, Integer> currentInvetoryMaterialBale = new TreeMap<String, Integer>();
	static Map<String, Double> currentInventoryMaterialWeight = new TreeMap<String, Double>();

	public static void connection() {
		IncomingMaterial object = new IncomingMaterial();
		try {
			object.ConnectionToSql();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Class Not Found inside Current Inventory!!", "Error!!",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void calcBaledMaterial(String materialType, Integer baleCount, Double grossWeight) {

		/**
		 * Map Material type and appropriate Bales Count and Weight in tons.
		 */

		/*
		 * String qryTotalIncomingMaterial =
		 * "  Select TRIM(MaterialType) AS MaterialType, sum(NumberOfBales) AS TotalIncomingBales, Sum(GrossWeightInTons) AS TotalIncomingWeight\r\n"
		 * +
		 * "  From BaledMaterial   Where Date >= DATEADD(day, datediff(day,1,getDate()),0)\r\n"
		 * + "  AND Date < DATEADD(day,datediff(day,0,getdate()),0)\r\n" +
		 * "  Group by MaterialType\r\n" + "  Order By MaterialType";
		 */

		productionMaterialBalesYesterday.put(materialType, baleCount);
		productionMaterialWeightYesterday.put(materialType, grossWeight);

	}// End of calcBaledMaterial()

	public static void calcSoldMaterial(String materialType, Integer soldBaleCount, Double soldWeight) {

		/*
		 * String qryTotalSoldMaterial =
		 * "Select TRIM(MaterialType) AS MaterialType, Sum(NumberOfBales) AS TotalSoldBales, Sum([WeightOfMaterial (in tons)]) AS TotalSoldWeight\r\n"
		 * +
		 * "  From SoldMaterial Where Date = DATEADD(day, datediff(day,0,getDate()),0)\r\n"
		 * + "  Group by MaterialType";
		 */

		soldMaterialBalesYesterday.put(materialType, soldBaleCount);
		soldMaterialWeightYesterday.put(materialType, soldWeight);

	}// End of calcSoldMaterial()

	/**
	 * Method to get current Inventory Bale count and Weight.
	 */
	public static void getCurrentInventory() {

		String qryGetCurrentInventory = "  Select TRIM([Material Type]) As MaterialType, [Available Bales] AS AvailableBales, [Weight (tons)] AS AvailableWeight\r\n"
				+ "  From [Current Inventory]\r\n";

		try {
			ResultSet rsGetCurrentInventory = IncomingMaterial.getaStatement().executeQuery(qryGetCurrentInventory);

			while (rsGetCurrentInventory.next()) {
				currentInvetoryMaterialBale.put(rsGetCurrentInventory.getString("MaterialType"),
						rsGetCurrentInventory.getInt("AvailableBales"));
				currentInventoryMaterialWeight.put(rsGetCurrentInventory.getString("MaterialType"),
						rsGetCurrentInventory.getDouble("AvailableWeight"));
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to Execute GetCurrentMaterial Query", "Error!!",
					JOptionPane.ERROR_MESSAGE);
		}

	}// End of getCurrentInventory()

	/**
	 * Method to calculate the new Inventory = CurrentInventory + Yesterday's
	 * Production - Yesterday's Sold.
	 */
	public static void updateNewInventory() {
		Integer newInventoryBales = 0;
		Double newInventoryWeight = 0d;

		for (String material : currentInvetoryMaterialBale.keySet()) {

			if (productionMaterialBalesYesterday.containsKey(material)
					|| soldMaterialBalesYesterday.containsKey(material)) {

				if (soldMaterialBalesYesterday.get(material) != null
						&& productionMaterialBalesYesterday.get(material) != null) {
					newInventoryBales = currentInvetoryMaterialBale.get(material)
							+ productionMaterialBalesYesterday.get(material) - soldMaterialBalesYesterday.get(material);
					newInventoryWeight = currentInventoryMaterialWeight.get(material)
							+ productionMaterialWeightYesterday.get(material)
							- soldMaterialWeightYesterday.get(material);
				}
				if (productionMaterialBalesYesterday.get(material) != null) {
					newInventoryBales = currentInvetoryMaterialBale.get(material)
							+ productionMaterialBalesYesterday.get(material);
					newInventoryWeight = currentInventoryMaterialWeight.get(material)
							+ productionMaterialWeightYesterday.get(material);
				}
				if (soldMaterialBalesYesterday.get(material) != null) {
					newInventoryBales = currentInvetoryMaterialBale.get(material)
							- soldMaterialBalesYesterday.get(material);
					newInventoryWeight = currentInventoryMaterialWeight.get(material)
							- soldMaterialWeightYesterday.get(material);
				}

				String qryInsertNewInventory = "  Insert Into [Current Inventory] ([Material Type],[Weight (tons)],[Available Bales])\r\n"
						+ "  Values (' " + material + " ', " + newInventoryWeight + " , " + newInventoryBales + " )";

				String qryUpdateInventory = "  Update [Current Inventory]\r\n" + "  Set [Available Bales]  = "
						+ newInventoryBales + ", [Weight (tons)] = " + newInventoryWeight + "\r\n"
						+ "  where TRIM([Material Type]) = '" + material + "'\r\n";

				try {
					IncomingMaterial.getaStatement().executeUpdate(qryUpdateInventory);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Unable to insert new Inventory to the SQL", "Error!!",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		}

	}// End of updateNewInventory()

	/*
	 * public static void calcCurrentInventory(Map productionMaterialBales, Map
	 * productionMaterialWeight, Map soldMaterialBales, Map soldMaterialWeight) {
	 * Double currentWeight = 0d; int currentBale = 0;
	 * 
	 * System.out.println("Inside Calc Current Inv");
	 *//**
		 * Iterate the MaterialType in a KeySet() of ProductionMaterialBales. Identify
		 * the similar key from SoldMaterialBales using containsKey(). Get the
		 * ProductionWeight for Baled and SoldMaterial and subtract to get currentWeight
		 * of inventory.
		 * 
		 * Simillarly, get the TotalBales from Production and Sold material to calculate
		 * Current Bale count.
		 *//*
			 * for (Object str : productionMaterialBales.keySet()) {
			 * 
			 * if (productionMaterialBales.containsKey(str)) {
			 * 
			 * System.out.println("Inside if statement"); System.out.println("Material = " +
			 * str); currentBale = (Integer) productionMaterialBales.get(str) - (Integer)
			 * soldMaterialBales.get(str); System.out.println(currentBale); }
			 * 
			 * }
			 * 
			 * }
			 */

}// End of class
