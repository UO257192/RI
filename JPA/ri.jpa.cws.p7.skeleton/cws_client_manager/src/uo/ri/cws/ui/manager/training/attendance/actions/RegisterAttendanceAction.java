package uo.ri.cws.ui.manager.training.attendance.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseAttendanceService;
import uo.ri.cws.application.service.training.EnrollmentDto;

public class RegisterAttendanceAction implements Action {

	private AttendanceUserInteractor user = new AttendanceUserInteractor();

	@Override
	public void execute() throws BusinessException {

		EnrollmentDto att = new EnrollmentDto();
		user.fill( att );

		CourseAttendanceService cs = Factory.service.forCourseAttendanceService();
		cs.registerNew( att );

		Console.println("Attendance registered:" + att.id);
	}

}
