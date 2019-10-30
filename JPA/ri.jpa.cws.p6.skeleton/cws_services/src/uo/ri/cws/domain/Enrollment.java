package uo.ri.cws.domain;

import alb.util.assertion.Argument;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tEnrollments")
public class Enrollment extends BaseEntity{
    @ManyToOne
    private Mechanic mechanic;
    @ManyToOne
    private Course course;
    private int attendance;
    private boolean passed;

    Enrollment(){}

    public Enrollment(Mechanic mechanic, Course course) {
        Argument.isNotNull(mechanic);
        Argument.isNotNull(course);
        this.mechanic = mechanic;
        this.course = course;
    }

    public Enrollment(Mechanic mechanic, Course course, int attendance, boolean passed) {
        this(mechanic,course);
        Argument.isTrue((passed && attendance >= 85) || (!passed && attendance >= 85));
        this.attendance = attendance;
        this.passed = passed;
    }

    Mechanic _getMechanic() {
        return mechanic;
    }

    Course _getCourse() {
        return course;
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


}
