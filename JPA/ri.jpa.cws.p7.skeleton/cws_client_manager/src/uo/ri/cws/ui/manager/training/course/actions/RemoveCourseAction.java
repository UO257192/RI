package uo.ri.cws.ui.manager.training.course.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseCrudService;

public class RemoveCourseAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String cId = Console.readString("Course id");

		CourseCrudService cs = Factory.service.forCourseCrudService();
		cs.deleteCourse( cId );

		Console.println("Course removed");
	}

}
