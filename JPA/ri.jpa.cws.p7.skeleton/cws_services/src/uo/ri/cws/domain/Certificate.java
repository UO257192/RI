package uo.ri.cws.domain;

import alb.util.assertion.Argument;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TCERTIFICATES", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "MECHANIC_ID", "VEHICLETYPE_ID" }) })
public class Certificate extends BaseEntity{
    @ManyToOne
    private Mechanic mechanic;
    @ManyToOne
    private VehicleType vehicleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    Certificate() {
    }

    public Certificate(Mechanic mechanic, VehicleType vehicleType) {
        Argument.isNotNull(mechanic);
        Argument.isNotNull(vehicleType);
        this.vehicleType = vehicleType;
        this.date = new Date();
        Associations.Certify.link(mechanic,this, vehicleType);
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public Mechanic getMechanic() {
        return mechanic;
    }
    void _setMechanic(Mechanic mechanic){
        this.mechanic = mechanic;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
    VehicleType _getVehicleType() {
        return vehicleType;
    }

    void _setVehicleType(VehicleType vehicleType){
        this.vehicleType = vehicleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Certificate that = (Certificate) o;
        return mechanic.equals(that.mechanic) &&
                vehicleType.equals(that.vehicleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mechanic, vehicleType);
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "date=" + date +
                ", mechanic=" + mechanic +
                ", vehicleType=" + vehicleType +
                '}';
    }

}
