package uo.ri.cws.application.service.training.course.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.VehicleType;

public class RegisterNewCourse implements Command<CourseDto> {

    private CourseDto courseDto;
    private VehicleTypeRepository vehicleTypeRepository = Factory.repository.forVehicleType();
    private CourseRepository courseRepository = Factory.repository.forCourse();

    public RegisterNewCourse(CourseDto courseDto) {
        this.courseDto = courseDto;
    }

    @Override
    public CourseDto execute() throws BusinessException {
        Optional<Course> ocourse = courseRepository.findByCode(courseDto.code);
        BusinessCheck.isTrue(!ocourse.isPresent(), "Ya existe un curso con el mismo codigo.");
        Map<VehicleType, Integer> percentages = new HashMap<VehicleType,Integer>();
        for (Map.Entry<String, Integer>  entry : courseDto.percentages.entrySet()) {
            Optional<VehicleType> ov = vehicleTypeRepository.findById(entry.getKey());
            BusinessCheck.isTrue(ov.isPresent(), "No existe este tipo de vehiculo.");
            percentages.put(ov.get(), entry.getValue());
        }
        Course course = new Course(courseDto.code, courseDto.name, courseDto.description, courseDto.startDate, courseDto.endDate, courseDto.hours, percentages);
        courseRepository.add(course);
        courseDto.id = courseRepository.findByCode(courseDto.code).get().getId();
        return courseDto;
    }
}
