package uo.ri.cws.ui.manager.training.course.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseCrudService;
import uo.ri.cws.application.service.training.CourseDto;

public class AddCourseAction implements Action {

	private CourseUserInteractor user = new CourseUserInteractor();
	
	@Override
	public void execute() throws BusinessException {

		CourseDto c = new CourseDto();
		user.fill( c );

		CourseCrudService cs = Factory.service.forCourseCrudService();
		cs.registerNew(c);

		Console.println("New course registered: " + c.id);
	}

}
