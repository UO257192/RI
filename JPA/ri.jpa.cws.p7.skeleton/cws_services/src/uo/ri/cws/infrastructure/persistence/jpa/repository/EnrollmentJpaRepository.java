package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class EnrollmentJpaRepository
        extends BaseJpaRepository<Enrollment>
        implements EnrollmentRepository {
    @Override
    public Optional<Enrollment> findByMechanicCourse(Course course, Mechanic mechanic) {
        return Jpa.getManager()
                .createNamedQuery("Enrollment.findByMechanicCourse", Enrollment.class)
                .setParameter(1, course)
                .setParameter(2, mechanic)
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
