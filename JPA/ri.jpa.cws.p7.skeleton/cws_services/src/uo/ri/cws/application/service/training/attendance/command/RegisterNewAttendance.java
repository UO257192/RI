package uo.ri.cws.application.service.training.attendance.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;

public class RegisterNewAttendance implements Command<EnrollmentDto> {

	private EnrollmentDto enrollmentDto;
	private MechanicRepository mechanicRepository = Factory.repository.forMechanic();
	private CourseRepository courseRepository = Factory.repository.forCourse();
	private EnrollmentRepository enrollmentRepository = Factory.repository.forEnrollment();

	public RegisterNewAttendance(EnrollmentDto enrollmentDto) {
		this.enrollmentDto = enrollmentDto;
	}

	@Override
	public EnrollmentDto execute() throws BusinessException {
		BusinessCheck.isNotNull(enrollmentDto);
		Optional<Mechanic> om = mechanicRepository.findById(enrollmentDto.mechanicId);
		BusinessCheck.isTrue(om.isPresent(), "El mecanico no existe");
		Mechanic mechanic = om.get();
		Optional<Course> oc = courseRepository.findById(enrollmentDto.courseId);
		BusinessCheck.isTrue(oc.isPresent(), "El curso no existe");
		Course course = oc.get();
		BusinessCheck.isTrue(!enrollmentRepository.findByMechanicCourse(course, mechanic).isPresent(),
				"Ya existe una asistencia registrada para el mecanico y el curso");
		BusinessCheck.isTrue(enrollmentDto.passed && !(enrollmentDto.attendance < 85),
				"El mecanico no puebe aprobar un curso sin tener el 85% de asistencia");
		BusinessCheck.isTrue(enrollmentDto.attendance <= 100 && enrollmentDto.attendance >= 0,
				"El porcentaje de asistencia no está entre 0 y 100");
		Enrollment enrollment = new Enrollment(mechanic, course, enrollmentDto.attendance, enrollmentDto.passed);
		enrollmentRepository.add(enrollment);
		enrollmentDto.id = enrollment.getId();
		return enrollmentDto;
	}
}
