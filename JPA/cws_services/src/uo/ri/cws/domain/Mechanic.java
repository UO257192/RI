package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

public class Mechanic {
	private String dni;
	private String surname;
	private String name;
	
	/*
	 * Relacion assign
	 */
	private Set<WorkOrder> workorders = new HashSet<WorkOrder>();

	public Set<WorkOrder> getAssigned() {
		return new HashSet<WorkOrder>(workorders);
	}
	Set<WorkOrder> _getWorkorders() {
		return workorders;
	}
	
	/*
	 * Relacion intervenir
	 */

	private Set<Intervention> interventions = new HashSet<Intervention>();

	public Set<Intervention> getInterventions() {
		return new HashSet<Intervention>(interventions);
		
	}
	 Set<Intervention> _getInterventions() {
		 return interventions;
	}

	public Mechanic(String dni) {
		this.dni = dni;
	}

	public Mechanic(String dni, String name, String surname) {
		this(dni);
		this.name = name;
		this.surname = surname;
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
}