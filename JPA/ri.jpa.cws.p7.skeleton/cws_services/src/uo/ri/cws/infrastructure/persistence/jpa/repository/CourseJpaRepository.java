package uo.ri.cws.infrastructure.persistence.jpa.repository;

import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CourseJpaRepository
        extends BaseJpaRepository<Course>
        implements CourseRepository {
	@Override
	public Optional<Course> findByCode(String code) {
		System.out.println(code);
		return Jpa.getManager()
				.createNamedQuery("Course.findByCode", Course.class)
				.setParameter(1, code)
				.getResultList().stream()
				.findFirst();
	}

	@Override
	public Optional<BigDecimal> findCourseHoursForCertificate(Mechanic mechanic, VehicleType vehicleType) {
		return Jpa.getManager()
				.createNamedQuery("Course.findCourseHoursForCertificate", BigDecimal.class)
				.setParameter(1, mechanic)
				.setParameter(2, vehicleType)
				.getResultList().stream()
				.findFirst();
	}

	@Override
	public List<Enrollment> findAttendanceByCourseId(Course course) {
		return Jpa.getManager()
				.createNamedQuery("Enrollment.findAttendanceByCourseId", Enrollment.class)
				.setParameter(1, course)
				.getResultList();
	}


}