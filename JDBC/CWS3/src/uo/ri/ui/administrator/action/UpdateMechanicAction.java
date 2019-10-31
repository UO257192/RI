package uo.ri.ui.administrator.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.serviceLayer.MechanicCrudService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		MechanicDto mechanicDto = new MechanicDto();
		// Get info
		mechanicDto.id = Console.readLong("Type mechahic id to update");
		mechanicDto.dni = Console.readString("Dni");
		mechanicDto.name = Console.readString("Name");
		mechanicDto.surname = Console.readString("Surname");

		MechanicCrudService mcs = ServiceFactory.getMechanicCrudService();
		mcs.updateMechanic(mechanicDto);

		// Print result
		Console.println("Mechanic updated");
	}

}
