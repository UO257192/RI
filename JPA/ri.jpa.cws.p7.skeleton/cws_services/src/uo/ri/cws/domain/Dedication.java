package uo.ri.cws.domain;

import alb.util.assertion.Argument;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

/**
 * Dedication class. TDEDICATIONS table.
 * <p>
 * * @author UO257192
 */
public class Dedication extends BaseEntity {
    private VehicleType vehicleType;
    private Course course;
    private int percentage;

    Dedication(){}

    /**
     * Dedication class constructor
     * @param course Course
     * @param vehicleType VehicleType
     * @param percentage Percentage dedicated
     */
    Dedication(Course course, VehicleType vehicleType, Integer percentage) {
        Argument.isNotNull(course);
        Argument.isNotNull(vehicleType);
        Argument.isTrue(percentage>0);
        Associations.Dedicate.link(course, this, vehicleType);
        this.percentage = percentage;
    }

    /**
     *
     * @return Course of dedication
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Internal use
     * @return Course of dedication
     */
    Course _getCourse() {
        return course;
    }

    /**
     * Internal use
     * Set Course
     * @param c Course
     */
    void _setCourse(Course c){
        this.course = c;
    }

    /**
     *
     * @return VehicleType of dedication
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * Internal use
     * Set VehicleType
     * @param vt VehicleType
     */
    void _setVehicleType(VehicleType vt){
        this.vehicleType = vt;
    }

    /**
     *
     * @return Percentage dedicated
     */
    public int getPercentage() {
        return percentage;
    }

    /**
     *
     * @param o Object to compare
     * @return Dedication class equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dedication that = (Dedication) o;
        return vehicleType.equals(that.vehicleType) &&
                course.equals(that.course);
    }

    /**
     *
     * @return Dedication class hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vehicleType, course);
    }

    /**
     *
     * @return Dedication class toString
     */
    @Override
    public String toString() {
        return "Dedication{" +
                "vehicleType=" + vehicleType +
                ", course=" + course +
                ", percentage=" + percentage +
                '}';
    }
}
