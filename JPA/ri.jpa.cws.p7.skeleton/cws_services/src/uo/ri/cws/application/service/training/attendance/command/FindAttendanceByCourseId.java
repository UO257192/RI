package uo.ri.cws.application.service.training.attendance.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class FindAttendanceByCourseId implements Command<List<EnrollmentDto>> {

	private String id;
	private EnrollmentRepository enrollmentRepository = Factory.repository.forEnrollment();
	private CourseRepository courseRepository = Factory.repository.forCourse();

	public FindAttendanceByCourseId(String id) {
		this.id = id;
	}

	@Override
	public List<EnrollmentDto> execute() throws BusinessException {
		BusinessCheck.isNotEmpty(id, "ID vacio");
		BusinessCheck.isNotNull(id, "ID null");
		Optional<Course> optionalCourse = courseRepository.findById(id);
		BusinessCheck.isTrue(optionalCourse.isPresent(), "No existe el curso");
		return DtoAssembler.toEnrollmentDtoList(enrollmentRepository.findAttendanceByCourseId(optionalCourse.get()));
	}
}
