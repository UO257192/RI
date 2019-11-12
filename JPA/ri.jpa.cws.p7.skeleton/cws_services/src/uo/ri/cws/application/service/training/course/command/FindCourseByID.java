package uo.ri.cws.application.service.training.course.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class FindCourseByID implements Command<Optional<CourseDto>> {

	private CourseRepository courseRepository = Factory.repository.forCourse();
	private String id;

	public FindCourseByID(String id) {
		this.id = id;
	}

	@Override
	public Optional<CourseDto> execute() throws BusinessException {
		Optional<Course> oc = courseRepository.findById(id);
		Course c = oc.isPresent() ? oc.get() : null;
		return c == null ? Optional.empty() : Optional.of(DtoAssembler.toDto(c));
	}
}
