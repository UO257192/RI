package uo.ri.ui.foreman.reception.actions;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;

public class UpdateWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long woId = Console.readLong("Work order id");

		WorkOrderService as = Factory.service.forWorkOrderService();
		Optional<WorkOrderDto> oWo = as.findWorkOrderById(woId);
		assertPresent(oWo);
		/*
		 * it can also be checked here the work order is in OPEN
		 * or ASSIGNED status. It is checked by the service anyway, but doing
		 * it here improves the user experience by detecting the problem earlier.
		 */

		if(!oWo.get().status.equals("OPEN") && !oWo.get().status.equals("ASSIGNED")) {
			throw new BusinessException("The WorkORder: "+  oWo.get().id + " is openned or assigned.");
		}

		String description = Console.readString("New description");
		WorkOrderDto wo = oWo.get();
		wo.description = description;

		as.updateWorkOrder( wo );

		Console.println("\nUpdate done");
	}

	private void assertPresent(Optional<WorkOrderDto> oWo) throws BusinessException {
		if ( oWo.isPresent() ) return;
		throw new BusinessException("There is no work order for this id");
	}
}
