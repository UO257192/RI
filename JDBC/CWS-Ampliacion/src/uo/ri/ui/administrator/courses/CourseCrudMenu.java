package uo.ri.ui.administrator.courses;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.courses.action.AddCourseAction;
import uo.ri.ui.administrator.courses.action.ListCoursesAction;
import uo.ri.ui.administrator.courses.action.RemoveCourseAction;
import uo.ri.ui.administrator.courses.action.UpdateCourseAction;

public class CourseCrudMenu extends BaseMenu {

	public CourseCrudMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Training management > Course CRUD", null},
			
			{ "Add", 			AddCourseAction.class }, 
			{ "Update", 		UpdateCourseAction.class }, 
			{ "Remove", 		RemoveCourseAction.class }, 
			{ "List all", 		ListCoursesAction.class },
		};
	}

}
