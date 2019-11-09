package uo.ri.cws.ui.manager.training.attendance.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.training.CourseAttendanceService;

public class RemoveAttendanceAction implements Action {

	@Override
	public void execute() throws Exception {

		String attId = Console.readString("Attendance id");

		CourseAttendanceService cs = Factory.service.forCourseAttendanceService();
		cs.deleteAttendace( attId );

		Console.println("Course attendance removed");
	}

}
