package uo.ri.conf;

import uo.ri.persistance.interventions.InterventionGateway;
import uo.ri.persistance.interventions.impl.InterventionGatewayImpl;
import uo.ri.persistance.invoice.InvoiceGateway;
import uo.ri.persistance.invoice.impl.InvoiceGatewayImpl;
import uo.ri.persistance.mechanic.MechanicGateway;
import uo.ri.persistance.mechanic.impl.MechanicGatewayImpl;
import uo.ri.persistance.training.CourseGateway;
import uo.ri.persistance.training.DedicationGateway;
import uo.ri.persistance.training.EnrollmentGateway;
import uo.ri.persistance.training.impl.CourseGatewayImpl;
import uo.ri.persistance.training.impl.DedicationGatewayImpl;
import uo.ri.persistance.training.impl.EnrollmentGatewayImpl;
import uo.ri.persistance.vehicle.VehicleGateway;
import uo.ri.persistance.vehicle.impl.VehicleGatewayImpl;
import uo.ri.persistance.workorder.WorkOrderGateway;
import uo.ri.persistance.workorder.impl.WorkOrderGatewayImpl;

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
	
	public DedicationGateway getDedicationGateway() {
		return new DedicationGatewayImpl();
	}

	public VehicleGateway getVehicleGateway() {
		return new VehicleGatewayImpl();
	}

	public InterventionGateway getInterventionGateway() {
		return new InterventionGatewayImpl();
	}

}
