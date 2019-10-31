package uo.ri.ui.foreman;

import alb.util.console.Printer;
import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.conf.Factory;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.foreman.client.ClientsMenu;
import uo.ri.ui.foreman.reception.ReceptionMenu;
import uo.ri.ui.foreman.vehicles.VehiclesMenu;

public class ForemanMain {

	private static class MainMenu extends BaseMenu {
		MainMenu() {
			menuOptions = new Object[][] {
				{ "Foreman", null },
				{ "Vehicle reception", 		ReceptionMenu.class },
				{ "Client management", 		ClientsMenu.class },
				{ "Vehicle management", 	VehiclesMenu.class },
				{ "Review client history", 	NotYetImplementedAction.class },
			};
		}
	}

	public static void main(String[] args) {
		new ForemanMain()
			.config()
			.run()
			.close();
	}

	private ForemanMain config() {
		Factory.service = new ServiceFactory();

		return this;
	}

	public ForemanMain run() {
		try {
			new MainMenu().execute();

		} catch (RuntimeException rte) {
			Printer.printRuntimeException(rte);
		}
		return this;
	}

	private void close() {}

}
