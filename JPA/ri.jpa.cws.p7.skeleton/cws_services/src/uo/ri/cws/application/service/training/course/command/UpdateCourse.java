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
        BusinessCheck.isTrue(course.isPresent(), "El curso no existe");
        Course cour = course.get();
        BusinessCheck.isTrue(!cour.getStartDate().before(Dates.today()), "El curso ya ha empezado");
        BusinessCheck.isTrue(dto.startDate.after(Dates.today()), "La fecha de inicio del curso es anterior a la actual");
        BusinessCheck.isNotNull(dto.name);
        BusinessCheck.isNotEmpty(dto.name, "El nombre está vacio");
        BusinessCheck.isNotNull(dto.code);
        BusinessCheck.isNotEmpty(dto.code, "El codigo está vacio");
        BusinessCheck.isNotNull(dto.description);
        BusinessCheck.isNotEmpty(dto.description, "La descripcion está vacia");
        BusinessCheck.isNotNull(dto.hours);
        BusinessCheck.isTrue(dto.hours > 0, "Las horas del curso no pueden ser 0 o negativas");
        BusinessCheck.isTrue(dto.version == cour.getVersion());
        cour.setName(dto.name);
        cour.setCode(dto.code);
        cour.setDescription(dto.description);
        cour.setHours(dto.hours);
        cour.setStartdate(dto.startDate);
        cour.setEnddate(dto.endDate);
        return null;
    }
}
