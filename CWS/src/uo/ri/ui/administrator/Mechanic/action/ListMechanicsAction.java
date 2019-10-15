package uo.ri.ui.administrator.Mechanic.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.serviceLayer.mechanic.MechanicCrudService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nList of mechanics \n");
		MechanicCrudService mcs = ServiceFactory.getMechanicCrudService();
		List<MechanicDto> mechanics = mcs.findAllMechanics();
		for (MechanicDto mechanicDto : mechanics) {
			Printer.printMechanic(mechanicDto);
		}

	}
}
