package uo.ri.cws.domain;

import alb.util.assertion.Argument;
import alb.util.assertion.StateCheck;

import java.util.*;

/**
 * Course class. TCOURSES table.
 * <p>
 * * @author UO257192
 */
public class Course extends BaseEntity {
    private String code;
    private String description;
    private Date enddate;
    private int hours;
    private String name;
    private Date startdate;
    private Set<Dedication> dedications = new HashSet<>();
    private Set<Enrollment> enrollments = new HashSet<>();

    Course() {
    }

    /**
     * Course class constructor
     *
     * @param code Course code
     */
    public Course(String code) {
        Argument.isNotNull(code);
        Argument.isNotEmpty(code);
        this.code = code;
    }

    /**
     * Course class constructor
     *
     * @param code        Course code
     * @param name        Course name
     * @param description Course description
     * @param startDate   Course start date
     * @param endDate     Course end date
     * @param duration    Course duration (hours)
     */
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
        Argument.isTrue(duration > 0);
        this.name = name;
        this.description = description;
        this.startdate = new Date(startDate.getTime());
        this.enddate = new Date(endDate.getTime());
        this.hours = duration;
    }

    /**
     * Course class constructor
     *
     * @param code        Course code
     * @param name        Course name
     * @param description Course description
     * @param startDate   Course start date
     * @param endDate     Course end date
     * @param duration    Course duration (hours)
     * @param percentages Course dedications
     */
    public Course(String code, String name, String description, Date startDate, Date endDate, int duration, Map<VehicleType, Integer> percentages) {
        this(code, name, description, startDate, endDate, duration);
        addDedications(percentages);
    }

    /**
     * Set course code
     *
     * @param code Course code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Set course description
     *
     * @param description Course description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set course end date
     *
     * @param enddate Course end date
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * Set course hours
     *
     * @param hours Course duration
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * Set course name
     *
     * @param name Course name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set course start date
     *
     * @param startdate Course start date
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * @return Course code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Course description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return copy of Course end date
     */
    public Date getEndDate() {
        return new Date(enddate.getTime());
    }

    /**
     * @return Course hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * @return Course name
     */
    public String getName() {
        return name;
    }

    /**
     * @return copy of course start date
     */
    public Date getStartDate() {
        return new Date(startdate.getTime());
    }

    /**
     * @return copy of course dedications
     */
    public Set<Dedication> getDedications() {
        return new HashSet<>(dedications);
    }

    /**
     * Internal use
     *
     * @return course dedications
     */
    Set<Dedication> _getDedications() {
        return dedications;
    }

    /**
     * @return copy of course enrollments
     */
    public Set<Enrollment> getEnrollments() {
        return new HashSet<>(enrollments);
    }

    /**
     * Internal use
     *
     * @return course enrollments
     */
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

    /**
     * Add dedications to the course
     *
     * @param percentages Course dedications
     */
    public void addDedications(Map<VehicleType, Integer> percentages) {
        int totalPercentages = 0;
        for (Dedication dedication : dedications) {
            if (dedication.getCourse().equals(this)) {
                totalPercentages += dedication.getPercentage();
            }
        }
        StateCheck.isTrue(totalPercentages < 100);
        for (Map.Entry<VehicleType, Integer> entry : percentages.entrySet()) {
            totalPercentages += entry.getValue();
        }
        Argument.isTrue(totalPercentages == 100);
        for (Map.Entry<VehicleType, Integer> entry : percentages.entrySet()) {
            dedications.add(new Dedication(this, entry.getKey(), entry.getValue()));
        }
    }

    /**
     * Clear course dedications
     */
    public void clearDedications() {
        for (Dedication dedication : new HashSet<>(dedications)) {
            Associations.Dedicate.unlink(dedication.getCourse(), dedication, dedication.getVehicleType());
        }
        dedications.clear();
    }
}
