package uo.ri.conf;

import uo.ri.business.serviceLayer.InvoiceService;
import uo.ri.business.serviceLayer.MechanicCrudService;
import uo.ri.business.serviceLayer.impl.InvoiceServiceImpl;
import uo.ri.business.serviceLayer.impl.MechanicCrudServiceImpl;

public class ServiceFactory {
	public static MechanicCrudService getMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}
	
	public static InvoiceService getInvoiceService() {
		return new InvoiceServiceImpl();
	}

}
