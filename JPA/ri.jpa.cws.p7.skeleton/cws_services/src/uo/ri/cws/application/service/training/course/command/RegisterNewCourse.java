package uo.ri.cws.application.service.training.course.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class RegisterNewCourse implements Command<CourseDto> {

    private CourseDto courseDto;
    private VehicleTypeRepository vehicleTypeRepository = Factory.repository.forVehicleType();
    private CourseRepository courseRepository = Factory.repository.forCourse();

    public RegisterNewCourse(CourseDto courseDto) {
        this.courseDto = courseDto;
    }

    @Override
    public CourseDto execute() throws BusinessException {
        Map<VehicleType, Integer> percentages = new HashMap<VehicleType,Integer>();
        for (Map.Entry<String, Integer>  entry : courseDto.percentages.entrySet()) {
            VehicleType v = vehicleTypeRepository.findById(entry.getKey()).get();
            percentages.put(v, entry.getValue());
        }
        Course course = new Course(courseDto.code, courseDto.name, courseDto.description, courseDto.startDate, courseDto.endDate, courseDto.hours, percentages);
        courseRepository.add(course);
        courseDto.id = courseRepository.findByCode(courseDto.code).get().getId();
        return courseDto;
    }
}
