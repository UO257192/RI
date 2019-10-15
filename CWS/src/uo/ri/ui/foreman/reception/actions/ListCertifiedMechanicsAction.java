package uo.ri.ui.foreman.reception.actions;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.ui.util.Printer;

public class ListCertifiedMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long vtId = Console.readLong("Vehicle type id");

		WorkOrderService as = Factory.service.forWorkOrderService();
		List<CertificateDto> certs = as.findCertificatesByVehicleTypeId( vtId );

		Console.println("\nList of certified mechanics\n");
		for(CertificateDto m : certs) {
			Printer.printCertifiedMechanic( m );
		}

	}
}
