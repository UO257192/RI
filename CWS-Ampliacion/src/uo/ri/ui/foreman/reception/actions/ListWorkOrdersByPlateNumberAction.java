package uo.ri.ui.foreman.reception.actions;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.ampliacion.WorkOrderDto;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.ui.util.Printer;

public class ListWorkOrdersByPlateNumberAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String plate = Console.readString("Plate number");

		WorkOrderService as = Factory.service.forWorkOrderService();
		List<WorkOrderDto> wos = as.findWorkOrdersByPlateNumber(plate);

		Console.println("Work orders for vehicle " + plate);
		for(WorkOrderDto wo: wos) {
			Printer.printWorkOrderDetail( wo );
		}

	}
}
