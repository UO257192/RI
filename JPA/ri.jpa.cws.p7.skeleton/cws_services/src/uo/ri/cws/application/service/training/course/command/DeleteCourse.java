package uo.ri.cws.application.service.training.course.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.DedicationRepository;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class DeleteCourse implements Command<Void> {

	private CourseRepository courseRepository = Factory.repository.forCourse();
	private EnrollmentRepository enrollmentRepository = Factory.repository.forEnrollment();
	private DedicationRepository dedicationRepository = Factory.repository.forDedication();
	private String id;

	public DeleteCourse(String id) {
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<Course> ocourse = courseRepository.findById(id);
		BusinessCheck.isTrue(ocourse.isPresent(), "That course does not exists");
		Course course = ocourse.get();
		BusinessCheck.isTrue(enrollmentRepository.findAttendanceByCourseId(course).isEmpty(),
				"El curso tiene mecanicos apuntados");
		course.getDedications().forEach(d -> dedicationRepository.remove(d));
		course.clearDedications();
		courseRepository.remove(course);
		return null;
	}
}
