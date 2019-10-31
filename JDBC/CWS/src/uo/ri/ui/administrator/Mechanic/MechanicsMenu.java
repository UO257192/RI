package uo.ri.ui.administrator.Mechanic;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.Mechanic.action.AddMechanicAction;
import uo.ri.ui.administrator.Mechanic.action.DeleteMechanicAction;
import uo.ri.ui.administrator.Mechanic.action.ListMechanicsAction;
import uo.ri.ui.administrator.Mechanic.action.UpdateMechanicAction;

public class MechanicsMenu extends BaseMenu {

	public MechanicsMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Mechanics management", null},
			
			{ "Add mechanic", 				AddMechanicAction.class }, 
			{ "Update mechanic", 	UpdateMechanicAction.class }, 
			{ "Delete mechanic", 				DeleteMechanicAction.class }, 
			{ "List mechanics", 				ListMechanicsAction.class },
		};
	}

}
