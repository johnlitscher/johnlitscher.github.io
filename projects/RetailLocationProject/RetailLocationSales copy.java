package projects;

import java.util.*;

public class RetailLocationSales {
	ArrayList<RetailLocation> retail = new ArrayList<RetailLocation>();
	
	/**
	 * Adds retail location with a location id, address, and monthly sales.
	 * 
	 * @param locationId The identification number of the retail location.
	 * @param street The street address
	 * @param city City where the address is located
	 * @param state State where the address is located
	 * @param zip Zip where the address is location
	 * @param monthlySales Monthly sales for the retail location
	 */
	public void addRetailLocation(int locationId, String street, String city, String state, String zip, double[] monthlySales){
		Address a = new Address(street, city, state, zip);
		RetailLocation r = new RetailLocation(locationId, a, monthlySales);
		retail.add(r);
	}
	
	/**
	 * Gets the retail location that matches the location id.
	 * 
	 * @param locationId The identification number of the retail location.
	 * @return Index of the retail location
	 */
	private int getRetailLocation(final int locationId){
		int result = -1;//arbitrary number
		//for each retail location, if the enter location id equals the retail location id, that retail location is returned
		for(int i = 0; i < retail.size(); i++){
			if(locationId == retail.get(i).getLocationId()){
				result = i;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Returns the monthly sales for a retail location based on its location id.
	 * 
	 * @param locationId The identification number of the retail location.
	 * @return Monthly sales for the retail location.
	 */
	public double[] monthlySalesForLocation(final int locationId){
		int index = getRetailLocation(locationId);
		if(index == -1){
			return null;
		}else{
			return retail.get(index).getMonthlySales();
			
		}
	}
	
	/**
	 * Returns yearly sales of the retail location.
	 * 
	 * @param locationId The identification number of the retail location.
	 * @return Total yearly sales for the retail location.
	 */
	public double yearlySalesForLocation(final int locationId){
		int index = getRetailLocation(locationId);
		if(index == -1){
			return -1;
		}else{
			double result;
			return retail.get(index).getTotalSales();
		}
	}
	
	/**
	 * Returns the location id of the location with the highest total sales.
	 * 
	 * @return Location id with the highest total sales.
	 */
	public int highestTotalSales(){
		double maxSales = -1.0;
		double currentSales;
		int locationId = -1;
		//for loop to switch the max sales value if the current sales of the new index is greater than the previous max
		for (int i = 0; i < retail.size(); i++){
			RetailLocation retailLocation = retail.get(i);
			currentSales = retailLocation.getTotalSales();
			if(currentSales > maxSales){
				maxSales = currentSales;
				locationId = retailLocation.getLocationId();
			}
		}
		return locationId;
	}
	
	/**
	 * Returns the location id of the location with the highest average sales. 
	 * 
	 * @return Location id with highest average sales.
	 */
	public int highestAverageSales(){
		double maxSales = -1.0;
		double averageSales;
		int locationId = -1;
		//for loop to switch the max sales value if the average sales of the new index is greater than the previous max
		for (int i = 0; i < retail.size(); i++){
			RetailLocation retailLocation = retail.get(i);
			averageSales = retailLocation.getAverageSales();
			if(averageSales > maxSales){
				maxSales = averageSales;
				locationId = retailLocation.getLocationId();
			}
		}
		return locationId;
	}
	
	/**
	 * Returns a retail location object in a string form.
	 * 
	 * @return result of the retail location in string form
	 */
	public String toString(){
		String result = "";
		for(int i = 0; i < retail.size(); i++){
			if(i > 0){
				result += "\n";
			}
			RetailLocation retailLocation = retail.get(i);
			result += retailLocation.toString();
		}
		return result;
	}
	
	/**
	 * Removes a retail location based off its location id.
	 * 
	 * @param locationId The identification number of the retail location.
	 * @return The retail location to be removed
	 */
	public int removeRetailLocation(final int locationId){
		int index = getRetailLocation(locationId);
		if(index != -1){
			retail.remove(index);
		}
		return index;
	}
	
	/**
	 * Returns index of the location id if location id.
	 * 
	 * @param locationId The identification number of the retail location.
	 * @param salesAmount New monthly sales amount
	 * @return Retail location with the update monthly sales
	 */
	public int addNewMonthlySalesForLocation(final int locationId, final double salesAmount){
		int index = getRetailLocation(locationId);
		if(index != -1){
			retail.get(index).updateMonthlySales(salesAmount);
		}
		return index;
	}

}
