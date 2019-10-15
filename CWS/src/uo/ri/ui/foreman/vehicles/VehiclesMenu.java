package uo.ri.ui.foreman.vehicles;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class VehiclesMenu extends BaseMenu {

	public VehiclesMenu() {
		menuOptions = new Object[][] { 
			{ "Foreman > Vehicles management ", null },

			{ "Add vehicle", NotYetImplementedAction.class }, 
			{ "Update vehicle", NotYetImplementedAction.class }, 
			{ "Delete vehicle", NotYetImplementedAction.class }, 
			{ "List vehicles", NotYetImplementedAction.class }, 
		};
	}

}
