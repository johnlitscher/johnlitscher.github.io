package projects;

public class RetailLocation {

	private Address address;
	
	private int locationId;
	
	private double[] monthlySales;
	
	public RetailLocation(int locationId, Address address, double[] ms) {
		setLocationId(locationId);
		setAddress(address);
		setMonthlySales(ms);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public double[] getMonthlySales() {
		return monthlySales;
	}

	public void setMonthlySales(double[] monthlySales) {
		this.monthlySales = monthlySales;
	}
	
	public double getTotalSales() {
		double sum = 0; 
		for(int i = 0; i < 12; i++) {
			sum += ms[i];
		}
	}
}
