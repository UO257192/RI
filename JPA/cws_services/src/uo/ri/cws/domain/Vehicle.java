package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import alb.util.assertion.Argument;

public class Vehicle {
	private String plateNumber;
	private String make;
	private String model;
	
	/*
	 * Relacion own
	 */
	private Client client = null;
	
	/*
	 * Relation classify
	 */
	private VehicleType vehicleType = null;
	
	/*
	 * Relacion subre averia (order)
	 */
	
	private Set<WorkOrder> workorders = new HashSet<WorkOrder>();
	
	public Client getClient() {
		return client;
	}

	
	public Vehicle(String plateNumber, String make, String model) {
		this(plateNumber);
		this.make = make;
		this.model = model;
	}


	public Vehicle(String plateNumber) {
		super();
		Argument.isTrue(plateNumber != null);
		this.plateNumber = plateNumber;
	}
	
	public String getPlateNumber() {
		return plateNumber;
	}
	public String getMake() {
		return make;
	}
	public String getModel() {
		return model;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plateNumber == null) ? 0 : plateNumber.hashCode());
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
		return "Vehicle [plateNumber=" + plateNumber + ", make=" + make + ", model=" + model + "]";
	}

	void _setClient(Client client2) {
		this.client = client2;	
	}


	public VehicleType getVehicleType() {
		return vehicleType;
	}


	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
		
	}

	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<WorkOrder>(workorders);
	}


	Set<WorkOrder> _getWorkOrders() {
		return workorders;
	}
	
}