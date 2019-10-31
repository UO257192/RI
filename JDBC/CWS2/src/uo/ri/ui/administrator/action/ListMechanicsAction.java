package uo.ri.ui.administrator.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.ui.util.Printer;
import uo.ri.business.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.impl.MechanicCrudServiceImpl;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nList of mechanics \n");
		MechanicCrudService mcs = new MechanicCrudServiceImpl();
		List<MechanicDto> mechanics = mcs.findAllMechanics();
		for (MechanicDto mechanicDto : mechanics) {
			Printer.printMechanic(mechanicDto);
		}

	}
}
