package uo.ri.cws.ui.manager.training.attendance.actions;

import java.util.Comparator;
import java.util.List;

import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.training.CourseAttendanceService;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.ui.util.Printer;

public class ListAttendanceToCourseAction implements Action {

	private AttendanceUserInteractor user = new AttendanceUserInteractor();
	
	@Override
	public void execute() throws Exception {
		String cId = user.askForCourseId();

		CourseAttendanceService s = Factory.service.forCourseAttendanceService();
		List<EnrollmentDto> attendance = s.findAttendanceByCourseId( cId );

		attendance.sort( new TVTRComparator() );
		attendance.forEach( att ->
				Printer.printAttendingMechanic(att)
		);
	}


	/**
	 * The sorting can be done in the query, but is also frequently done
	 * at the presentation layer
	 */
	private class TVTRComparator implements Comparator<EnrollmentDto> {

		@Override
		public int compare(EnrollmentDto a,
						   EnrollmentDto b) {

			int res = a.mechanic.surname.compareTo( b.mechanic.surname );
			if ( res == 0)  {
				res = a.mechanic.name.compareTo( b.mechanic.name);
			}
			return res;
		}

	}

}
