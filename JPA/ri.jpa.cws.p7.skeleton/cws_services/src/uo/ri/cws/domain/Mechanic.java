package uo.ri.cws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tMechanics")
public class Mechanic extends  BaseEntity{
	@Column(unique = true, nullable = false)
	private String dni;
	private String surname;
	private String name;

	@OneToMany(mappedBy = "mechanic")
	private Set<Intervention> interventions = new HashSet<>();
	@OneToMany(mappedBy = "mechanic")
	private Set<WorkOrder> assigned = new HashSet<>();
	@OneToMany(mappedBy = "mechanic")
	private Set<Certificate> certificates = new HashSet<>();
	@OneToMany(mappedBy = "mechanic")
	private Set<Enrollment> enrollments = new HashSet<>();

	Mechanic() {
	};

	public Mechanic(String dni) {
		super();
		this.dni = dni;
	}

	public Mechanic(String dni, String name, String surname) {
		this(dni);
		this.name = name;
		this.surname = surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public String getSurname() {
		return surname;
	}

	public String getName() {
		return name;
	}

	public Set<Intervention> getInterventions() {
		return new HashSet<Intervention>(interventions);
	}

	Set<Intervention> _getInterventions() {
		return interventions;
	}

	public Set<WorkOrder> getAssigned() {
		return new HashSet<WorkOrder>(assigned);
	}

	Set<WorkOrder> _getAssigned() {
		return assigned;
	}

	public Set<Certificate> getCertificates() {
		return new HashSet<Certificate>(certificates);
	}

	Set<Certificate> _getCertificates() {
		return certificates;
	}

	public Set<Enrollment> getEnrollments() {
		return new HashSet<Enrollment>(enrollments);
	}

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

    public boolean isCertifiedFor(VehicleType vehicleType) {
        for (Certificate certificate : _getCertificates()) {
            if (certificate._getVehicleType().equals(vehicleType)) {
                return true;
            }
        }
        return false;
    }
}
