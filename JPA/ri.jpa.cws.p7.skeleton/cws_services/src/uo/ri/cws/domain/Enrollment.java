package uo.ri.cws.domain;

import java.util.Objects;

import alb.util.assertion.Argument;

/**
 * Enrollment class. TENROLLMENTS table.
 * <p>
 * * @author UO257192
 */
public class Enrollment extends BaseEntity {
	private Mechanic mechanic;
	private Course course;
	private int attendance;
	private boolean passed;

	Enrollment() {
	}

	/**
	 * Enrollment class constructor
	 * 
	 * @param mechanic   Mechanic
	 * @param course     Course
	 * @param attendance Attendance percentage
	 * @param passed     Passed
	 */
	public Enrollment(Mechanic mechanic, Course course, int attendance, boolean passed) {
		Argument.isTrue((passed && attendance >= 85) || (!passed && attendance >= 85));
		Argument.isNotNull(mechanic);
		Argument.isNotNull(course);
		Associations.Enroll.link(mechanic, this, course);
		this.attendance = attendance;
		this.passed = passed;
	}

	/**
	 *
	 * @return Mechanic
	 */
	public Mechanic getMechanic() {
		return mechanic;
	}

	/**
	 *
	 * @return Course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * Internal use
	 * 
	 * @return Course
	 */
	Course _getCourse() {
		return course;
	}

	/**
	 * Internal use Set Mechanic
	 * 
	 * @param mechanic Mechanic
	 */
	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	/**
	 * Internal use Set Course
	 * 
	 * @param course Course
	 */
	void _setCourse(Course course) {
		this.course = course;
	}

	/**
	 *
	 * @return Course attendance
	 */
	public int getAttendance() {
		return attendance;
	}

	/**
	 *
	 * @return true if mechanic passed : false if not
	 */
	public boolean isPassed() {
		return passed;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Enrollment that = (Enrollment) o;
		return mechanic.equals(that.mechanic) && course.equals(that.course);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mechanic, course);
	}

	@Override
	public String toString() {
		return "Enrollment{" + "mechanic=" + mechanic + ", course=" + course + ", attendance=" + attendance
				+ ", passed=" + passed + '}';
	}

	/**
	 * Get the number of hours attended for a specific vehicle type
	 * 
	 * @param vehicleType VehicleType
	 * @return Hours attendede
	 */
	public int getAttendedHoursFor(VehicleType vehicleType) {
		int total = 0;
		for (Dedication dedication : course._getDedications()) {
			if (dedication.getVehicleType().equals(vehicleType)) {
				total += (dedication.getPercentage() * course.getHours() / 100) * attendance / 100;
			}
		}
		return total;
	}
}
