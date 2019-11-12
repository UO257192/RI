package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.math.BigDecimal;
import java.util.Optional;

import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class CourseJpaRepository extends BaseJpaRepository<Course> implements CourseRepository {
	@Override
	public Optional<Course> findByCode(String code) {
		return Jpa.getManager().createNamedQuery("Course.findByCode", Course.class).setParameter(1, code)
				.getResultList().stream().findFirst();
	}

	@Override
	public Optional<BigDecimal> findCourseHoursForCertificate(Mechanic mechanic, VehicleType vehicleType) {
		return Jpa.getManager().createNamedQuery("Course.findCourseHoursForCertificate", BigDecimal.class)
				.setParameter(1, mechanic).setParameter(2, vehicleType).getResultList().stream().findFirst();
	}

	@Override
	public Optional<BigDecimal> findTrainingByVehicleTypeAndMechanic(Mechanic mechanic, VehicleType vehicleType) {
		return Jpa.getManager().createNamedQuery("Course.findTrainingByVehicleTypeAndMechanic", BigDecimal.class)
				.setParameter(1, mechanic).setParameter(2, vehicleType).getResultList().stream().findFirst();
	}

	@Override
	public Optional<BigDecimal> findEnrolledHoursByVehicleTypeAndMechanic(Mechanic mechanic, VehicleType vehicleType) {
		return Jpa.getManager().createNamedQuery("Course.findEnrolledHoursByVehicleTypeAndMechanic", BigDecimal.class)
				.setParameter(1, mechanic).setParameter(2, vehicleType).getResultList().stream().findFirst();
	}
}
