package uo.ri.ui.administrator;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.certificate.action.GenerateCertificatesAction;
import uo.ri.ui.administrator.courseAttendance.AttendanceMenu;
import uo.ri.ui.administrator.courses.CourseCrudMenu;
import uo.ri.ui.administrator.mechanic.MechanicsMenu;
import uo.ri.ui.administrator.report.ReportsMenu;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrator", null },
			{ "Mechanics management", 			MechanicsMenu.class }, 
			{ "Spare parts management", 			SparesMenu.class },
			{ "Course management", 			CourseCrudMenu.class },
			{ "Vehicle types management", 	VehicleTypeMenu.class },
			{ "Reports", 	ReportsMenu.class },
			{ "Certificate generation", 	GenerateCertificatesAction.class },
			{ "Attendance management", 	AttendanceMenu.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}
	
}