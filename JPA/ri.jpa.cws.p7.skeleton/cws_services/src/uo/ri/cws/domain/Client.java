package uo.ri.cws.domain;

import alb.util.assertion.Argument;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Client class. TCLIENTS table.
 * <p>
 * * @author UO257192
 */
public class Client extends BaseEntity {
    private String dni;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Address address;

    private Set<Vehicle> vehicles = new HashSet<>();
    private Set<PaymentMean> paymentMeans = new HashSet<>();

    Client() {
    }

    /**
     * Client class constructor
     *
     * @param dni Client dni
     */
    public Client(String dni) {
        super();
        Argument.isTrue(dni != null && dni.length() > 0);
        this.dni = dni;
    }

    /**
     * Client class constructor
     *
     * @param dni     Client dni
     * @param name    Client name
     * @param surname Client surname
     */
    public Client(String dni, String name, String surname) {
        this(dni);
        this.name = name;
        this.surname = surname;
    }

    /**
     * @return Client dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @return Client name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Client surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return Client email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Client phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return Client Address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set client address
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return a copy of the vehicles owned by client
     */
    public Set<Vehicle> getVehicles() {
        return new HashSet<>(vehicles);
    }

    /**
     * @return a copy of the paymentmeans of the client
     */
    public Set<PaymentMean> getPaymentMeans() {
        return new HashSet<>(paymentMeans);
    }

    /**
     * Internal use
     *
     * @return the vehicles owned by client
     */
    Set<Vehicle> _getVehicles() {
        return vehicles;
    }

    /**
     * Internal use
     *
     * @return the paymentmeans of the client
     */
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
