package uo.ri.cws.application.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

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

	/**
	 *
	 * @param mechanic Mechanic certified
	 * @param vehicleType VehicleType certified by Mechanic
	 * @return total number of hours of courses dedicated to the vehicle type passed for the mechanic
	 */
	Optional<BigDecimal> findCourseHoursForCertificate(Mechanic mechanic, VehicleType vehicleType);

	/**
	 *
	 * @param mechanic Mechanic
	 * @param vehicleType VehicleType
	 * @return  total number of hours of courses dedicated to the vehicle type for the mechanic
	 */
	Optional<BigDecimal> findTrainingByVehicleTypeAndMechanic(Mechanic mechanic, VehicleType vehicleType);

	/**
	 *
	 * @param mechanic Mechanic
	 * @param vehicleType VehicleType
	 * @return total number of enrolled hours of courses dedicated to the vehicle type for the mechanic
	 */
	Optional<BigDecimal> findEnrolledHoursByVehicleTypeAndMechanic(Mechanic mechanic, VehicleType vehicleType);
}
