package uo.ri.cws.application.service.training.course;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseCrudService;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.service.training.course.command.DeleteCourse;
import uo.ri.cws.application.service.training.course.command.FindAllCourses;
import uo.ri.cws.application.service.training.course.command.RegisterNewCourse;
import uo.ri.cws.application.service.vehicletype.FindAllVehicleTypes;
import uo.ri.cws.application.service.vehicletype.VehicleTypeDto;
import uo.ri.cws.application.service.workorder.crud.command.RegisterNew;
import uo.ri.cws.application.util.command.CommandExecutor;

import java.util.List;
import java.util.Optional;

public class CourseCrudServiceImpl implements CourseCrudService {
    private CommandExecutor executor = Factory.executor.forExecutor();


    @Override
    public CourseDto registerNew(CourseDto dto) throws BusinessException {
        return executor.execute(new RegisterNewCourse(dto));
    }

    @Override
    public void updateCourse(CourseDto dto) throws BusinessException {

    }

    @Override
    public void deleteCourse(String id) throws BusinessException {
        executor.execute(new DeleteCourse(id));
    }

    @Override
    public List<CourseDto> findAllCourses() throws BusinessException {
        return executor.execute(new FindAllCourses());
    }

    @Override
    public List<VehicleTypeDto> findAllVehicleTypes() throws BusinessException {
        return executor.execute(new FindAllVehicleTypes());
    }

    @Override
    public Optional<CourseDto> findCourseById(String cId) throws BusinessException {
        return Optional.empty();
    }
}
