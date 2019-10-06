package uo.ri.ui.administrator.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.ui.util.Printer;
import uo.ri.business.administrator.ListMechanics;
import uo.ri.business.dto.MechanicDto;

public class ListMechanicsAction implements Action {


	
	@Override
	public void execute() throws BusinessException {

		Console.println("\nList of mechanics \n"); 
		ListMechanics listMechanics = new ListMechanics();
		List<MechanicDto> mechanics = listMechanics.execute();
		for (MechanicDto mechanicDto : mechanics) {
			Printer.printMechanic(mechanicDto);
		}

	
	}
}
