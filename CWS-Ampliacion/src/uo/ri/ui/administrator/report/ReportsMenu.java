package uo.ri.ui.administrator.report;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.administrator.report.action.ListCertificationsByVehicleTypeAction;
import uo.ri.ui.administrator.report.action.ListTrainingByVehicleTypeAction;

public class ReportsMenu extends BaseMenu {

	public ReportsMenu() {
		menuOptions = new Object[][] {
			{ "Manager > Training management > Reports", null },

			{ "Training of a mechanic",
				NotYetImplementedAction.class },
			{ "Training by vehicle types",
					ListTrainingByVehicleTypeAction.class },
			{ "Certifications by vehicle type",
					ListCertificationsByVehicleTypeAction.class } 
		};
	}

}
