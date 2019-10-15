package uo.ri.conf;

import uo.ri.persistance.CourseGateway;
import uo.ri.persistance.EnrollmentGateway;
import uo.ri.persistance.InvoiceGateway;
import uo.ri.persistance.MechanicGateway;
import uo.ri.persistance.WorkOrderGateway;
import uo.ri.persistance.impl.CourseGatewayImpl;
import uo.ri.persistance.impl.EnrollmentGatewayImpl;
import uo.ri.persistance.impl.InvoiceGatewayImpl;
import uo.ri.persistance.impl.MechanicGatewayImpl;
import uo.ri.persistance.impl.WorkOrderGatewayImpl;

public class PersistanceFactory {
	public MechanicGateway getMechanicCrudService() {
		return new MechanicGatewayImpl();
	}
	
	public InvoiceGateway getInvoiceGateway() {
		return new InvoiceGatewayImpl();
	}
	
	public WorkOrderGateway getWorkOrderGateway() {
		return new WorkOrderGatewayImpl();
	}
	
	public EnrollmentGateway getEnrollmentGateway() {
		return new EnrollmentGatewayImpl();
	}
	
	public CourseGateway getCourseGateway() {
		return new CourseGatewayImpl();
	}

}
