package uo.ri.cws.domain;

import alb.util.assertion.Argument;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

@Entity
@Table(name = "TENROLLMENTS",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "MECHANIC_ID","COURSE_ID"
        })
})
public class Enrollment extends BaseEntity{
    @ManyToOne
    private Mechanic mechanic;
    @ManyToOne
    private Course course;
    private int attendance;
    private boolean passed;

    Enrollment(){}

    public Enrollment(Mechanic mechanic, Course course, int attendance, boolean passed) {
        Argument.isTrue((passed && attendance >= 85) || (!passed && attendance >= 85));
        Argument.isNotNull(mechanic);
        Argument.isNotNull(course);
        Associations.Enroll.link(mechanic,this, course);
        this.attendance = attendance;
        this.passed = passed;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public Course getCourse() {
        return course;
    }

    Course _getCourse() {
        return course;
    }

    void _setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    void _setCourse(Course course) {
        this.course = course;
    }

    public int getAttendance() {
        return attendance;
    }

    public boolean isPassed() {
        return passed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return mechanic.equals(that.mechanic) &&
                course.equals(that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mechanic, course);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "mechanic=" + mechanic +
                ", course=" + course +
                ", attendance=" + attendance +
                ", passed=" + passed +
                '}';
    }


    public int getAttendedHoursFor(VehicleType vehicleType) {
        int total=0;
        for (Dedication dedication: course._getDedications()) {
            if (dedication.getVehicleType().equals(vehicleType)) {
                total+=(dedication.getPercentage()*course.getHours()/100)*attendance/100;
            }
        }
        return total;
    }
}
