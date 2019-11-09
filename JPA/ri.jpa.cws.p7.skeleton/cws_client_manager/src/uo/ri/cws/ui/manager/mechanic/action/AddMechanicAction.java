package uo.ri.cws.ui.manager.mechanic.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicDto;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		MechanicDto m = new MechanicDto();
		m.dni = Console.readString("Doi"); 
		m.name = Console.readString("Name"); 
		m.surname = Console.readString("Surname");

		MechanicCrudService as = Factory.service.forMechanicCrudService();
		m = as.addMechanic( m );

		Console.println("New mechanic added: " + m.id);
	}

}
