package uo.ri.cws.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tVehicles")
public class Vehicle extends BaseEntity{
	@Column(unique=true, nullable=false)private String plateNumber;
	private String make;
	private String model;
	
	@ManyToOne private Client client;
	@ManyToOne private VehicleType vehicleType;
	
	@OneToMany(mappedBy = "vehicle") private Set<WorkOrder> workOrders = new HashSet<>();
	
	Vehicle() {};
	
	public Vehicle(String plateNumber) {
		super();
		this.plateNumber = plateNumber;
	}

	public Vehicle(String plateNumber, String make, String model) {
		this(plateNumber);
		this.make = make;
		this.model = model;
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

	public Client getClient() {
		return client;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<WorkOrder>(workOrders);
	}

	Set<WorkOrder> _getWorkOrders() {
		return workOrders;
	}
	void _setClient(Client client) {
		this.client = client;
	}

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
		return "Vehicle{" +
				"plateNumber='" + plateNumber + '\'' +
				", brand='" + make + '\'' +
				", model='" + model + '\'' +
				", client=" + client +
				", vehicleType=" + vehicleType +
				'}';
	}
}
