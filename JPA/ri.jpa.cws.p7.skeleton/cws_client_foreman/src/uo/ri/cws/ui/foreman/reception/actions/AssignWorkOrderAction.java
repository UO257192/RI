package uo.ri.cws.ui.foreman.reception.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.vehicle.VehicleCrudService;
import uo.ri.cws.application.service.vehicle.VehicleDto;
import uo.ri.cws.application.service.workorder.AssignWorkOrderService;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.ui.util.Printer;

import java.util.Optional;

public class AssignWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String woId = Console.readString("Work order id");
//		WorkOrderCrudService workOrderCrudService = Factory.service.forWorkOrderService();
//		Optional<WorkOrderDto> wo = workOrderCrudService.findWorkOrderById(woId);
//		BusinessCheck.isTrue(wo.isPresent(), "No existe el workorder");
//
//		VehicleCrudService vehicleCrudService = Factory.service.forVehicleCrudService();
//		System.out.println(wo.get().vehicleId);
//		Optional<VehicleDto> vehicle = vehicleCrudService.findVehicleByPlate(wo.get().vehicleId);
//		BusinessCheck.isTrue(vehicle.isPresent(), "No existe el vehiculo");

//		System.out.println(vehicle.get());

		AssignWorkOrderService as = Factory.service.forAssignWorkOrderService();
//		as.findCertificatesByVehicleTypeId(vehicle.get().vehicleTypeId).forEach(Printer::printCertifiedMechanic);
		String mId = Console.readString("Mechanic id");
		as.assignWorkOrderToMechanic(woId, mId);

		Console.println("\nAssignation done");
	}
}
