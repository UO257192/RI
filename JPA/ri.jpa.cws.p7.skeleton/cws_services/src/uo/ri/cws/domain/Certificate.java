package uo.ri.cws.domain;

import alb.util.assertion.Argument;

import java.util.Date;
import java.util.Objects;

/**
 * Certificate class. TCERTIFICATES table.
 * <p>
 * * @author UO257192
 */
public class Certificate extends BaseEntity {

    private Mechanic mechanic;
    private VehicleType vehicleType;
    private Date date;

    Certificate() {
    }

    /**
     * Certificate class constructor
     *
     * @param mechanic    Mechanic associated to the certificate
     * @param vehicleType VehicleType associated to the certificate
     */
    public Certificate(Mechanic mechanic, VehicleType vehicleType) {
        Argument.isNotNull(mechanic);
        Argument.isNotNull(vehicleType);
        this.date = new Date();
        Associations.Certify.link(mechanic, this, vehicleType);
    }

    /**
     * @return a copy of the date
     */
    public Date getDate() {
        return new Date(date.getTime());
    }

    /**
     * @return certified Mechanic
     */
    public Mechanic getMechanic() {
        return mechanic;
    }

    /**
     * Internal use
     *
     * @param mechanic Set certified mechanic
     */
    void _setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    /**
     * @return Mechanic certified VehicleType
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * Internal use
     *
     * @return Mechanic certified VehicleType
     */
    VehicleType _getVehicleType() {
        return vehicleType;
    }

    /**
     * Internal use
     *
     * @param vehicleType Set mechanic certified vehicleType
     */
    void _setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * @param o Object to compare
     * @return Certificate class Equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Certificate that = (Certificate) o;
        return mechanic.equals(that.mechanic) &&
                vehicleType.equals(that.vehicleType);
    }

    /**
     * @return Certificate class hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mechanic, vehicleType);
    }

    /**
     * @return Certificate class toString
     */
    @Override
    public String toString() {
        return "Certificate{" +
                "date=" + date +
                ", mechanic=" + mechanic +
                ", vehicleType=" + vehicleType +
                '}';
    }

}
