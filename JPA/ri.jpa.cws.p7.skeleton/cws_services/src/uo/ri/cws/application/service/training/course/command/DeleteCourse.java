package uo.ri.cws.application.service.training.course.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DeleteCourse implements Command<Void> {

    private CourseRepository courseRepository = Factory.repository.forCourse();
    private String id;

    public DeleteCourse(String id) {
        this.id = id;
    }

    @Override
    public Void execute() throws BusinessException {
        Optional<Course> course = courseRepository.findById(id);
        BusinessCheck.isTrue(course.isPresent(), "That course does not exists");
        BusinessCheck.isTrue(courseRepository.findAttendanceByCourseId(course.get()).isEmpty(), "That course has enrollments");
        course.get().clearDedications();
        courseRepository.remove(course.get());
        return null;
    }
}
