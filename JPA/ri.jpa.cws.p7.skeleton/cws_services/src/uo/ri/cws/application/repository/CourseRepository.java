package uo.ri.cws.application.repository;

import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends Repository<Course> {

	/**
	 * @return a list with all courses (might be empty)
	 */
	List<Course> findAll();

	/**
	 * @param code course code
	 * @return the course identified by the code or null if none
	 */
	Optional<Course> findByCode(String code);


	Optional<BigDecimal> findCourseHoursForCertificate(Mechanic mechanic, VehicleType vehicleType);

	List<Enrollment> findAttendanceByCourseId(Course course);
}
