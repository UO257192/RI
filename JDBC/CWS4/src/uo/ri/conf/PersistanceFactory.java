package uo.ri.conf;

import uo.ri.persistance.InvoiceGateway;
import uo.ri.persistance.MechanicGateway;
import uo.ri.persistance.WorkOrderGateway;
import uo.ri.persistance.impl.InvoiceGatewayImpl;
import uo.ri.persistance.impl.MechanicGatewayImpl;
import uo.ri.persistance.impl.WorkOrderGatewayImpl;

public class PersistanceFactory {
	public static MechanicGateway getMechanicCrudService() {
		return new MechanicGatewayImpl();
	}
	
	public static InvoiceGateway getInvoiceGateway() {
		return new InvoiceGatewayImpl();
	}
	
	public static WorkOrderGateway getWorkOrderGateway() {
		return new WorkOrderGatewayImpl();
	}

}
