package uo.ri.cws.domain;

/**
 * Client adress
 *
 * @author UO257192
 */
public class Address {
	private String street;
	private String city;
	private String zipCode;

	/**
	 * Address constructor
	 *
	 * @param street street
	 * @param city city
	 * @param zipCode zipcode
	 */
	public Address(String street, String city, String zipCode) {
		super();
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
	}

	/**
	 * Adress constructor for jpa
	 */
	Address() {

	}

	/**
	 *
	 * @return street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 *
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 *
	 * @return zipcode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 *
	 * @return Address class hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	/**
	 *
	 * @param obj adress
	 * @return true if addresses is equals : false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	/**
	 *
	 * @return Address class to string
	 */
	@Override
	public String toString() {
		return "Address [street=" + street 
				+ ", city=" + city 
				+ ", zipCode=" + zipCode 
				+ "]";
	}

}
