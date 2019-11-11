package uo.ri.cws.domain;

import alb.util.assertion.Argument;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

public class Dedication extends BaseEntity {
    private VehicleType vehicleType;
    private Course course;
    private int percentage;

    Dedication(){}


    Dedication(Course course, VehicleType vehicleType, Integer percentage) {
        Argument.isNotNull(course);
        Argument.isNotNull(vehicleType);
        Argument.isTrue(percentage>0);
        Associations.Dedicate.link(course, this, vehicleType);
        this.percentage = percentage;
    }

    public Course getCourse() {
        return course;
    }

    Course _getCourse() {
        return course;
    }

    void _setCourse(Course c){
        this.course = c;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    void _setVehicleType(VehicleType vt){
        this.vehicleType = vt;
    }
    public int getPercentage() {
        return percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dedication that = (Dedication) o;
        return vehicleType.equals(that.vehicleType) &&
                course.equals(that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vehicleType, course);
    }

    @Override
    public String toString() {
        return "Dedication{" +
                "vehicleType=" + vehicleType +
                ", course=" + course +
                ", percentage=" + percentage +
                '}';
    }
}
