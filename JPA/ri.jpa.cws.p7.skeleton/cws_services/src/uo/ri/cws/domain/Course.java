package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.assertion.Argument;
import alb.util.assertion.StateCheck;

public class Course extends BaseEntity{
    private String code;
    private String description;
    private Date enddate;
    private int hours;
    private String name;
    private Date startdate;
    private Set<Dedication> dedications = new HashSet<>();
    private Set<Enrollment> enrollments = new HashSet<>();

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

    public Course(String code, String name, String description, Date startDate, Date endDate, int duration, Map<VehicleType, Integer> percentages) {
        this(code, name, description, startDate, endDate, duration);
        addDedications(percentages);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
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

    public Set<Enrollment> getEnrollments() {
        return new HashSet<>(enrollments);
    }

    Set<Enrollment> _getEnrollments() {
        return enrollments;
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
        int totalPercentages = 0;
        for (Dedication dedication : dedications) {
            if(dedication.getCourse().equals(this)){
                totalPercentages += dedication.getPercentage();
            }
        }
        StateCheck.isTrue(totalPercentages <  100);
        for (Map.Entry<VehicleType, Integer>  entry : percentages.entrySet()) {
            totalPercentages += entry.getValue();
        }
        Argument.isTrue(totalPercentages ==  100);
        for (Map.Entry<VehicleType, Integer>  entry : percentages.entrySet()) {
            dedications.add(new Dedication(this, entry.getKey(), entry.getValue()));
        }
    }

    public void clearDedications() {
        for (Dedication dedication : new HashSet<>(dedications)) {
            Associations.Dedicate.unlink(dedication.getCourse(), dedication, dedication.getVehicleType());
        }
        dedications.clear();
    }
}
