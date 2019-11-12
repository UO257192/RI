package uo.ri.cws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * VehicleType class. TVEHICLETYPE table.
 * <p>
 * * @author UO257192
 */
public class VehicleType extends BaseEntity {

	private String name;
	private double pricePerHour;
	private int mintraininghours;

	private Set<Vehicle> vehicles = new HashSet<>();
	private Set<Dedication> dedications = new HashSet<>();
	private Set<Certificate> certificates=new HashSet<Certificate>();

	VehicleType() {
	}

	/**
	 * VehicleType class constructor
	 * @param name Type name
	 */
	public VehicleType(String name) {
		super();
		this.name = name;
	}

	/**
	 * VehicleType class constructor
	 * @param name Type name
	 * @param pricePerHour Price per hour of workorder
	 */
	public VehicleType(String name, double pricePerHour) {
		this(name);
		this.pricePerHour = pricePerHour;
	}

	/**
	 *
	 * @return VehicleType name
	 */
	public String getNombre() {
		return name;
	}

	/**
	 *
	 * @return VehicleType price per hour
	 */
	public double getPricePerHour() {
		return pricePerHour;
	}

	/**
	 * Internal use
	 * @return the vehicles with this type
	 */
	Set<Vehicle> _getVehicles() {
		return vehicles;
	}
	/**
	 *
	 * @return a copy of the vehicles with this type
	 */
	public Set<Vehicle> getVehicles() {
		return new HashSet<Vehicle>(vehicles);
	}

	/**
	 * Internal use
	 * @return courses dedicated to this type
	 */
	Set<Dedication> _getDedications() {
		return dedications;
	}

	/**
	 *
	 * @return a copy of the courses dedicated to this type
	 */
	public Set<Dedication> getDedications() {
		return new HashSet<Dedication>(dedications);
	}

	/**
	 * Internal use
	 * @return certificates for this type
	 */
	Set<Certificate> _getCertificates() {
		return certificates;
	}

	/**
	 *
	 * @return a copy of the certificates for this type
	 */
	public Set<Certificate> getCertificates() {
		return new HashSet<Certificate>(certificates);
	}

	/**
	 *
	 * @return minimun traning hours necessary to get certified
	 */
	public int getMinTrainingHours() {
		return mintraininghours;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleType other = (VehicleType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VehicleType [name=" + name + ", pricePerHour=" + pricePerHour + "]";
	}

}
