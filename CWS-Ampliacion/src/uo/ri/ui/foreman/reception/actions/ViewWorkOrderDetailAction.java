package uo.ri.ui.foreman.reception.actions;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.ampliacion.WorkOrderDto;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.ui.util.Printer;

public class ViewWorkOrderDetailAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long woId = Console.readLong("Work order id");

		WorkOrderService as = Factory.service.forWorkOrderService();
		Optional<WorkOrderDto> oWo = as.findWorkOrderById(woId);

		if ( oWo.isPresent() ) {
			Printer.printWorkOrderDetail( oWo.get() );
		} else {
			Console.println("There is no work order with that id");
		}

	}
}
