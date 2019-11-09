package uo.ri.cws.application.service.training.course.command;

import alb.util.date.Dates;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

import java.util.Optional;

public class UpdateCourse  implements Command<Void> {

    private CourseRepository courseRepository = Factory.repository.forCourse();
    private CourseDto dto;

    public UpdateCourse(CourseDto dto) {
        this.dto = dto;
    }

    @Override
    public Void execute() throws BusinessException {
        Optional<Course> course = courseRepository.findById(dto.id);
        BusinessCheck.isTrue(course.isPresent(), "That course does not exists");
        Course cour = course.get();
        BusinessCheck.isTrue(!cour.getStartDate().before(Dates.today()) && !cour.getEndDate().after(Dates.today()), "The course is being taught");
        cour.setName(dto.name);
        cour.setCode(dto.code);
        cour.setDescription(dto.description);
        cour.setHours(dto.hours);
        return null;
    }
}
