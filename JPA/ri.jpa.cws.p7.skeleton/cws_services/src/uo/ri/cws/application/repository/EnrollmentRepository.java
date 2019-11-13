package uo.ri.cws.application.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;

public interface EnrollmentRepository extends Repository<Enrollment> {

	/**
	 *
	 * @param course Course
	 * @param mechanic Mechanic
	 * @return Enroll mechanic to course
	 */
	Optional<Enrollment> findByMechanicCourse(Course course, Mechanic mechanic);

	/**
	 *
	 * @param course Course
	 * @return list of enrollments to the course
	 */
	List<Enrollment> findAttendanceByCourseId(Course course);
}
