package uo.ri.ui.administrator.courseAttendance;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.courseAttendance.actions.ListAttendanceToCourseAction;
import uo.ri.ui.administrator.courseAttendance.actions.RegisterAttendanceAction;
import uo.ri.ui.administrator.courseAttendance.actions.RemoveAttendanceAction;

public class AttendanceMenu extends BaseMenu {

	public AttendanceMenu() {
		menuOptions = new Object[][] {
			{"Manager > Training management > Attendance", null},

			{ "Register", 			RegisterAttendanceAction.class },
			{ "Remove", 			RemoveAttendanceAction.class },
			{ "List attendance",	ListAttendanceToCourseAction.class },
		};
	}

}
