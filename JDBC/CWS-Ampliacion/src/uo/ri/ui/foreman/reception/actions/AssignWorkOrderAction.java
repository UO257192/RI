package uo.ri.ui.foreman.reception.actions;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.serviceLayer.vehicle.VehicleCrudService;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;

public class AssignWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long woId = Console.readLong("Work order id");
		WorkOrderService as = Factory.service.forWorkOrderService();
		WorkOrderDto dto = as.findWorkOrderById(woId).get();
		if(dto == null) {
			throw new BusinessException("Workorder: " + woId + " does not exist");
		}
		if(dto.mechanicId != 0) {
			throw new BusinessException("Workorder: " + woId + " already has assigned mechanic");
		}

		if(!dto.status.equals("OPEN")) {
			throw new BusinessException("The workorder is not available");
		}
		
		// List Mechanic
		VehicleCrudService vs = Factory.service.forVehicleCrudService();
		VehicleDto vehicle = vs.findVehicleByID(dto.vehicleId);
		List<CertificateDto> mechanics = as.findCertificatesByVehicleTypeId(vehicle.vehicleTypeId);
		Console.println("Mechanics:");
		for (CertificateDto certificateDto : mechanics) {
			Console.println("ID: " +certificateDto.mechanic.id + " NAME: " +certificateDto.mechanic.name + " " +certificateDto.mechanic.surname);
		}
		Long mId = Console.readLong("Mechanic id");
		while(Contains(mId,mechanics)) {
			mId = Console.readLong("Mechanic id");
		}
		as.assignWorkOrderToMechanic(woId, mId);

		Console.println("\nAssignation done");
	}
	
	private boolean Contains(Long mId , List<CertificateDto> dtos) {
		for (CertificateDto certificateDto : dtos) {
			if(certificateDto.id.equals(mId)) {
				return true;
			}
		}
		return false;
	}
	
	
}
