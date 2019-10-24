package uo.ri.ui.administrator.courseAttendance.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.serviceLayer.training.CourseAttendanceService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;

public class RegisterAttendanceAction implements Action {

	private AttendanceUserInteractor user = new AttendanceUserInteractor();

	@Override
	public void execute() throws BusinessException {

		// Ask the user for data
		EnrollmentDto att = new EnrollmentDto();
		user.fill( att );

		// Invoke the service
		CourseAttendanceService cs = Factory.service.forCourseAttendanceService();
		cs.registerNew( att );

		// Show result
		Console.println("Attendance registered:" + att.id);
	}

}
