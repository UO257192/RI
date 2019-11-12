package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Mechanic class. TMECHANICS table.
 * <p>
 * * @author UO257192
 */
public class Mechanic extends BaseEntity {
	private String dni;
	private String surname;
	private String name;

	private Set<Intervention> interventions = new HashSet<>();
	private Set<WorkOrder> assigned = new HashSet<>();
	private Set<Certificate> certificates = new HashSet<>();
	private Set<Enrollment> enrollments = new HashSet<>();

	Mechanic() {
	};

	/**
	 * Mechanic class constructor
	 * 
	 * @param dni Mechanic dni
	 */
	public Mechanic(String dni) {
		super();
		this.dni = dni;
	}

	/**
	 * Mechanic class constructor
	 * 
	 * @param dni     Mechanic dni
	 * @param name    Mechanic name
	 * @param surname Mechanic surname
	 */
	public Mechanic(String dni, String name, String surname) {
		this(dni);
		this.name = name;
		this.surname = surname;
	}

	/**
	 * Set a surname to the mechanic
	 * 
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Set a name to the mechanic
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return Mechanic dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 *
	 * @return Mechanic surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 *
	 * @return Mechanic name
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @return a copy of the interventions of the mechanic
	 */
	public Set<Intervention> getInterventions() {
		return new HashSet<Intervention>(interventions);
	}

	/**
	 * Internal use
	 * 
	 * @return the interventions of the mechanic
	 */
	Set<Intervention> _getInterventions() {
		return interventions;
	}

	/**
	 *
	 * @return a copy of the workorders assigned to the mechanic
	 */
	public Set<WorkOrder> getAssigned() {
		return new HashSet<WorkOrder>(assigned);
	}

	/**
	 *
	 * @return the workorders assigned to the mechanic
	 */
	Set<WorkOrder> _getAssigned() {
		return assigned;
	}

	/**
	 *
	 * @return a copy of the certificates of the mechanic
	 */
	public Set<Certificate> getCertificates() {
		return new HashSet<Certificate>(certificates);
	}

	/**
	 *
	 * @return the certificates of the mechanic
	 */
	Set<Certificate> _getCertificates() {
		return certificates;
	}

	/**
	 *
	 * @return a copy of the enrollments of the mechanic
	 */
	public Set<Enrollment> getEnrollments() {
		return new HashSet<Enrollment>(enrollments);
	}

	/**
	 *
	 * @return the enrollments of the mechanic
	 */
	Set<Enrollment> _getEnrollments() {
		return enrollments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Mechanic other = (Mechanic) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mechanic [dni=" + dni + ", surname=" + surname + ", name=" + name + "]";
	}

	/**
	 *
	 * @param vehicleType Vehicle type
	 * @return Enrollmets for the mechanic in courses with dedications to the
	 *         vehicleType
	 */
	public Set<Enrollment> getEnrollmentsFor(VehicleType vehicleType) {
		Set<Enrollment> ens = new HashSet<>();
		for (Enrollment enrollment : _getEnrollments()) {
			for (Dedication dedication : enrollment._getCourse()._getDedications()) {
				if (dedication.getVehicleType().equals(vehicleType)) {
					ens.add(enrollment);
				}
			}
		}

		return ens;
	}

	/**
	 *
	 * @param vehicleType Vehicle type
	 * @return true if mechanic is certified for the vehicle type
	 */
	public boolean isCertifiedFor(VehicleType vehicleType) {
		for (Certificate certificate : _getCertificates()) {
			if (certificate._getVehicleType().equals(vehicleType)) {
				return true;
			}
		}
		return false;
	}
}
