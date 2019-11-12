package uo.ri.cws.application.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;

public interface EnrollmentRepository extends Repository<Enrollment> {

	Optional<Enrollment> findByMechanicCourse(Course course, Mechanic mechanic);

	List<Enrollment> findAttendanceByCourseId(Course course);
}
