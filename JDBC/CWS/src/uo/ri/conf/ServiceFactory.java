package uo.ri.conf;

import uo.ri.business.serviceLayer.invoice.InvoiceService;
import uo.ri.business.serviceLayer.invoice.impl.InvoiceServiceImpl;
import uo.ri.business.serviceLayer.mechanic.MechanicCrudService;
import uo.ri.business.serviceLayer.mechanic.impl.MechanicCrudServiceImpl;
import uo.ri.business.serviceLayer.training.CertificateService;
import uo.ri.business.serviceLayer.training.CourseAttendanceService;
import uo.ri.business.serviceLayer.training.CourseCrudService;
import uo.ri.business.serviceLayer.training.CourseReportService;
import uo.ri.business.serviceLayer.training.impl.CertificateServiceImpl;
import uo.ri.business.serviceLayer.training.impl.CourseAttendanceServiceImpl;
import uo.ri.business.serviceLayer.training.impl.CourseCrudServiceImpl;
import uo.ri.business.serviceLayer.training.impl.CourseReportServiceImpl;
import uo.ri.business.serviceLayer.vehicle.VehicleCrudService;
import uo.ri.business.serviceLayer.vehicle.impl.VehicleCrudServiceImpl;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.business.serviceLayer.workorder.impl.WorkOrderServiceImpl;

public class ServiceFactory {
	public static MechanicCrudService getMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}
	
	public static InvoiceService getInvoiceService() {
		return new InvoiceServiceImpl();
	}

	public CourseAttendanceService forCourseAttendanceService() {
		return new CourseAttendanceServiceImpl();
	}

	public CertificateService forCertificateService() {
		return new CertificateServiceImpl();
	}

	public CourseCrudService forCourseCrudService() {
		return new CourseCrudServiceImpl();
	}

	public CourseReportService forCourseReportService() {
		return new CourseReportServiceImpl();
	}

	public WorkOrderService forWorkOrderService() {
		return new WorkOrderServiceImpl();
	}

	public VehicleCrudService forVehicleCrudService() {
		return new VehicleCrudServiceImpl();
	}
	
	

}
