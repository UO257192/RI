package uo.ri.ui.foreman.reception.actions;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.serviceLayer.certificate.CertificateService;
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
		if(dto.mechanicId != null) {
			throw new BusinessException("Workorder: " + woId + " already has assigned mechanic");
		}

		if(!dto.status.equals("OPEN")) {
			throw new BusinessException("The workorder is not available");
		}
		
		List<CertificateDto> mechanics = as.findCertificatesByVehicleTypeId(woId);
		// List Mechanic

		Long mId = Console.readLong("Mechanic id");

		as.assignWorkOrderToMechanic(woId, mId);

		Console.println("\nAssignation done");
	}
	
	
	private boolean Contains(Long mechanicID, List<CertificateDto> mechanics) {
		
	}
}
