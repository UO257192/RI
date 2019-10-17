package uo.ri.ui.administrator;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.action.AddMechanicAction;
import uo.ri.ui.administrator.action.DeleteMechanicAction;
import uo.ri.ui.administrator.action.ListMechanicsAction;
import uo.ri.ui.administrator.action.ListMechanicsByVehicleTypeAction;
import uo.ri.ui.administrator.action.UpdateMechanicAction;

public class MechanicsMenu extends BaseMenu {

	public MechanicsMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Mechanics management", null},
			
			{ "Add mechanic", 				AddMechanicAction.class }, 
			{ "Update mechanic", 	UpdateMechanicAction.class }, 
			{ "Delete mechanic", 				DeleteMechanicAction.class }, 
			{ "List mechanics", 				ListMechanicsAction.class },
			{ "List of mechanics who have attended the training by type of vehicle", ListMechanicsByVehicleTypeAction.class},
		};
	}

}
