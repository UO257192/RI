package uo.ri.cws.application.service.training.attendance;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.service.mechanic.crud.command.FindAllMechanics;
import uo.ri.cws.application.service.training.CourseAttendanceService;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.service.training.attendance.command.DeleteAttendance;
import uo.ri.cws.application.service.training.attendance.command.FindAttendanceByCourseId;
import uo.ri.cws.application.service.training.attendance.command.RegisterNewAttendance;
import uo.ri.cws.application.service.training.course.command.FindAllCourses;
import uo.ri.cws.application.util.command.CommandExecutor;

public class CourseAttendanceServiceImpl implements CourseAttendanceService {

    private CommandExecutor executor = Factory.executor.forExecutor();
    @Override
    public EnrollmentDto registerNew(EnrollmentDto dto) throws BusinessException {
        return executor.execute(new RegisterNewAttendance(dto));
    }

    @Override
    public void deleteAttendace(String id) throws BusinessException {
        executor.execute(new DeleteAttendance(id));
    }

    @Override
    public List<EnrollmentDto> findAttendanceByCourseId(String id) throws BusinessException {
        return executor.execute(new FindAttendanceByCourseId(id));
    }

    @Override
    public List<CourseDto> findAllActiveCourses() throws BusinessException {
        return executor.execute(new FindAllCourses());
    }

    @Override
    public List<MechanicDto> findAllActiveMechanics() throws BusinessException {
        return executor.execute(new FindAllMechanics());
    }
}
