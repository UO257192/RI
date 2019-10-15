package uo.ri.ui.foreman.clientReception;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.foreman.clientReception.action.AddBreakdownAction;
import uo.ri.ui.foreman.clientReception.action.UpdateBreakdownAction;

public class ClientReceptionsMenu extends BaseMenu {

	public ClientReceptionsMenu() {
		menuOptions = new Object[][] { 
			{"Foreman > Check in menu", null},
			
			{"Add breakdown", 			AddBreakdownAction.class }, 
			{"Update breakdown", 		UpdateBreakdownAction.class },
			{"Delete breakdown", 		NotYetImplementedAction.class },
			{"", null},
			{"List breakdown", 			NotYetImplementedAction.class }, 
			{"Check a breakdown", 		NotYetImplementedAction.class },
			{"", null},
			{"List mechanics", 			NotYetImplementedAction.class }, 
			{"Assign a breakdown",  	NotYetImplementedAction.class },
		};
	}

}
