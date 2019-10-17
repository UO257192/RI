package uo.ri.conf;

import uo.ri.business.serviceLayer.certificate.CertificateService;
import uo.ri.business.serviceLayer.certificate.impl.CertificateServiceImpl;
import uo.ri.business.serviceLayer.invoice.InvoiceService;
import uo.ri.business.serviceLayer.invoice.impl.InvoiceServiceImpl;
import uo.ri.business.serviceLayer.mechanic.MechanicCrudService;
import uo.ri.business.serviceLayer.mechanic.impl.MechanicCrudServiceImpl;
import uo.ri.business.serviceLayer.training.CourseReportService;
import uo.ri.business.serviceLayer.training.impl.CourseReportServiceImpl;
import uo.ri.business.serviceLayer.vehicle.VehicleCrudService;
import uo.ri.business.serviceLayer.vehicle.impl.VehicleCrudServiceImpl;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.business.serviceLayer.workorder.impl.WorkOrderServiceImpl;

public class ServiceFactory {
	public MechanicCrudService getMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}
	
	public InvoiceService getInvoiceService() {
		return new InvoiceServiceImpl();
	}

	public CertificateService forCertificateService() {
		return new CertificateServiceImpl();
	}

	public WorkOrderService forWorkOrderService() {
		return new WorkOrderServiceImpl();
	}

	public VehicleCrudService forVehicleCrudService() {
		return new VehicleCrudServiceImpl();
	}

	public CourseReportService getCourseReportService() {
		return new CourseReportServiceImpl();
	}

}
