package uo.ri.cws.domain;

import alb.util.assertion.Argument;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Client extends BaseEntity{
	private String dni;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private Address address;
	
	private Set<Vehicle> vehicles = new HashSet<>();
	private Set<PaymentMean> paymentMeans = new HashSet<>();

	Client() {};

	public Client(String dni) {
		super();
		Argument.isTrue(dni != null && dni.length() > 0);
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

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Vehicle> getVehicles() {
		return new HashSet<>(vehicles);
	}

	public Set<PaymentMean> getPaymentMeans() {
		return new HashSet<>(paymentMeans);
	}

	Set<Vehicle> _getVehicles() {
		return vehicles;
	}

	Set<PaymentMean> _getPaymentMeans() {
		return paymentMeans;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return dni.equals(client.dni);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public String toString() {
		return "Client{" +
				"dni='" + dni + '\'' +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", address=" + address +
				'}';
	}
}
