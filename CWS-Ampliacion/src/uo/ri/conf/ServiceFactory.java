package uo.ri.conf;

import uo.ri.business.serviceLayer.certificate.CertificateService;
import uo.ri.business.serviceLayer.certificate.impl.CertificateServiceImpl;
import uo.ri.business.serviceLayer.invoice.InvoiceService;
import uo.ri.business.serviceLayer.invoice.impl.InvoiceServiceImpl;
import uo.ri.business.serviceLayer.mechanic.MechanicCrudService;
import uo.ri.business.serviceLayer.mechanic.impl.MechanicCrudServiceImpl;
import uo.ri.business.serviceLayer.training.CourseCrudService;
import uo.ri.business.serviceLayer.training.CourseReportService;
import uo.ri.business.serviceLayer.training.impl.CourseCrudServiceImpl;
import uo.ri.business.serviceLayer.training.impl.CourseReportServiceImpl;
import uo.ri.business.serviceLayer.vehicle.VehicleCrudService;
import uo.ri.business.serviceLayer.vehicle.impl.VehicleCrudServiceImpl;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.business.serviceLayer.workorder.impl.WorkOrderServiceImpl;

public class ServiceFactory {

	/**
	 * Create a new instance of the implementation of MechanicCrudService
	 * 
	 * @return a new MechanicCrudServiceImpl
	 */
	public MechanicCrudService getMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}

	/**
	 * Create a new instance of the implementation of InvoiceService
	 * 
	 * @return a new InvoiceServiceImpl
	 */
	public InvoiceService getInvoiceService() {
		return new InvoiceServiceImpl();
	}

	/**
	 * Create a new instance of the implementation of CertificateService
	 * 
	 * @return a new CertificateServiceImpl
	 */
	public CertificateService forCertificateService() {
		return new CertificateServiceImpl();
	}

	/**
	 * Create a new instance of the implementation of WorkOrderService
	 * 
	 * @return a new WorkOrderServiceImpl
	 */
	public WorkOrderService forWorkOrderService() {
		return new WorkOrderServiceImpl();
	}

	/**
	 * Create a new instance of the implementation of VehicleCrudService
	 * 
	 * @return a new VehicleCrudServiceImpl
	 */
	public VehicleCrudService forVehicleCrudService() {
		return new VehicleCrudServiceImpl();
	}

	/**
	 * Create a new instance of the implementation of CourseReportService
	 * 
	 * @return a new CourseReportServiceImpl
	 */
	public CourseReportService getCourseReportService() {
		return new CourseReportServiceImpl();
	}

	/**
	 * Create a new instance of the implementation of CourseCrudService
	 * 
	 * @return a new CourseCrudServiceImpl
	 */
	public CourseCrudService forCourseCrudService() {
		return new CourseCrudServiceImpl();
	}

}
