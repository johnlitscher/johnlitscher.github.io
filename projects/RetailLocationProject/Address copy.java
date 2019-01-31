package projects;

import java.util.List;

/**
 * Represents a mailing address.
 * 
 * @author John Litscher
 */
public class Address {
	
	/** Street address */
	private String street;
	
	private String city;
	
	private String state;
	
	private String zip;
	
	public Address(String street, String city, String state, String zip) {
		setStreet(street);
		setCity(city);
		setState(state);
		setZip(zip);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) throws IllegalArgumentException {
		if(state.length() != 2) {
			throw new IllegalArgumentException();
		}
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) throws IllegalArgumentException {
		
		//checks to make sure zipcode is 5 digits
		if(zip.length() != 5) {
			throw new IllegalArgumentException();
		}
		this.zip = zip;
	}
	
    /**
    * Returns a string representation of this address object in the form:
    * street1; street2; city, state zip
    * If the street2 field is null, it is not included.
    *
    * @return A string representation of this address object
    */
    public String toString() {
        return street + "; " + city + ", " + state + " " + zip;
    }
	
}
