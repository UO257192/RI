package uo.ri.ui.administrator.courses.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.serviceLayer.training.CourseCrudService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;

public class RemoveCourseAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Ask the user for data
		Long cId = Console.readLong("Course id");

		// Invoke the service
		CourseCrudService cs = Factory.service.forCourseCrudService();
		cs.deleteCourse( cId );

		// Show result
		Console.println("Course removed");
	}

}
