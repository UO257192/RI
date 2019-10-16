package uo.ri.ui.foreman.reception.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;

public class AssignWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long woId = Console.readLong("Work order id");
		Long mId = Console.readLong("Mechanic id");

		WorkOrderService as = Factory.service.forWorkOrderService();
		as.assignWorkOrderToMechanic(woId, mId);
		
		Console.println("\nAssignation done");
	}
}
