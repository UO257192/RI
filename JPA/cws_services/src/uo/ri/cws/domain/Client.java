package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import alb.util.assertion.Argument;

public class Client {
	
	//PK DNI
	
	private String dni;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private Address address;
	
	/*
	 * Relacion own
	 * */
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();

	public Set<Vehicle> getVehicles() {
		return new HashSet<>(vehicles);
	}

	public Client(String dni) {
		Argument.isTrue(dni != null);
		this.dni = dni;
	}

	
	public Client(String dni, String name, String surname) {
		this(dni);
		this.name = name;
		this.surname = surname;
	}

	public String getDni() {
		return dni;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public Address getAddress() {
		return address;
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
		Client other = (Client) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [dni=" + dni + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phone="
				+ phone + ", address=" + address + "]";
	}

	public Set<Vehicle> _getVehicles() {
		return vehicles;
	}
	
	

}
