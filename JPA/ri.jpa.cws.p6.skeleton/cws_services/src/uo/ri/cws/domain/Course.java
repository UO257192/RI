package uo.ri.cws.domain;

import alb.util.assertion.Argument;
import alb.util.assertion.StateCheck;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "TCOURSES")
public class Course extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String code;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    private int hours;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @OneToMany
    private Set<Dedication> dedications = new HashSet<>();

    Course(){}

    public Course(String code) {
        Argument.isNotNull(code);
        Argument.isNotEmpty(code);
        this.code = code;
    }

    public Course(String code, String name, String description, Date startDate, Date endDate, int duration) {
        this(code);
        Argument.isNotNull(name);
        Argument.isNotEmpty(name);
        Argument.isNotNull(description);
        Argument.isNotEmpty(description);
        Argument.isNotNull(startDate);
        Argument.isNotNull(endDate);
        Argument.isTrue(startDate.before(endDate));
        Argument.isTrue(endDate.after(startDate));
        Argument.isTrue(duration>0);
        this.name = name;
        this.description = description;
        this.startdate = new Date(startDate.getTime());
        this.enddate = new Date(endDate.getTime());
        this.hours = duration;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Date getEndDate() {
        return new Date(enddate.getTime());
    }

    public int getHours() {
        return hours;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return new Date(startdate.getTime());
    }

    public Set<Dedication> getDedications() {
        return new HashSet<>(dedications);
    }

    Set<Dedication> _getDedications() {
        return dedications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Course course = (Course) o;
        return code.equals(course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code);
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", enddate=" + enddate +
                ", hours=" + hours +
                ", name='" + name + '\'' +
                ", startdate=" + startdate +
                '}';
    }

    public void addDedications(Map<VehicleType, Integer> percentages) {
        Set<Dedication> aux = new HashSet<>();
        int totalPercentages = 0;
        for (Dedication dedication : dedications) {
            if(dedication.getCourse().equals(this)){
                totalPercentages += dedication.getPercentage();
            }
        }
        StateCheck.isTrue(totalPercentages <  100);
        for (Map.Entry<VehicleType, Integer>  entry : percentages.entrySet()) {
            Dedication dedication = new Dedication(this, entry.getKey(), entry.getValue());
            aux.add(dedication);
            totalPercentages += entry.getValue();
        }
        Argument.isTrue(totalPercentages ==  100);
        for (Dedication dedication : aux) {
            Associations.Dedicate.link(dedication);
        }
    }

    public void clearDedications() {
        Associations.Dedicate.unlink(this);
    }
}
