package uo.ri.cws.application.service.training.attendance.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindAttendanceByCourseId implements Command<List<Enrollment>> {

    private CourseRepository courseRepository = Factory.repository.forCourse();
    private String id;

    public FindAttendanceByCourseId(String id) {
        this.id = id;
    }

    @Override
    public List<Enrollment> execute() throws BusinessException {
        Optional<Course> course = courseRepository.findById(id);
        return course.isPresent() ? courseRepository.findAttendanceByCourseId(course.get()) : Arrays.asList();
    }
}
