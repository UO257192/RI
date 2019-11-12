package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Vehicle class. TVEHICLES table.
 * <p>
 * * @author UO257192
 */
public class Vehicle extends BaseEntity {
	private String plateNumber;
	private String make;
	private String model;

	private Client client;
	private VehicleType vehicleType;

	private Set<WorkOrder> workOrders = new HashSet<>();

	Vehicle() {
	};

	/**
	 * Vehicle class constructor
	 * 
	 * @param plateNumber Vehicle plateNumber
	 */
	public Vehicle(String plateNumber) {
		super();
		this.plateNumber = plateNumber;
	}

	/**
	 * Vehicle class constructor
	 * 
	 * @param plateNumber Vehicle plateNumber
	 * @param make        Vehicle make
	 * @param model       Vehicle model
	 */
	public Vehicle(String plateNumber, String make, String model) {
		this(plateNumber);
		this.make = make;
		this.model = model;
	}

	/**
	 *
	 * @return Vehicle plateNumber
	 */
	public String getPlateNumber() {
		return plateNumber;
	}

	/**
	 *
	 * @return Vehicle make
	 */
	public String getMake() {
		return make;
	}

	/**
	 *
	 * @return Vehicle model
	 */
	public String getModel() {
		return model;
	}

	/**
	 *
	 * @return Vehicle owner
	 */
	public Client getClient() {
		return client;
	}

	/**
	 *
	 * @return Type of the vehicle
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	/**
	 *
	 * @return a copy of the workOrders of the vehicle
	 */
	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<WorkOrder>(workOrders);
	}

	/**
	 * Internal use
	 * 
	 * @return the workOrders of the vehicle
	 */
	Set<WorkOrder> _getWorkOrders() {
		return workOrders;
	}

	/**
	 * Internal use Set the owner of the vehicle
	 * 
	 * @param client Client owner
	 */
	void _setClient(Client client) {
		this.client = client;
	}

	/**
	 * Internal use Set the type of the vehicle
	 * 
	 * @param vehicleType VehicleType
	 */
	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((plateNumber == null) ? 0 : plateNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (plateNumber == null) {
			if (other.plateNumber != null)
				return false;
		} else if (!plateNumber.equals(other.plateNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle{" + "plateNumber='" + plateNumber + '\'' + ", brand='" + make + '\'' + ", model='" + model
				+ '\'' + ", client=" + client + ", vehicleType=" + vehicleType + '}';
	}
}
