package uo.ri.cws.infrastructure.persistence.jpa.repository;

import uo.ri.cws.application.repository.*;

public class JpaRepositoryFactory implements RepositoryFactory {

	@Override
	public MechanicRepository forMechanic() {
		return new MechanicJpaRepository();
	}

	@Override
	public WorkOrderRepository forWorkOrder() {
		return new WorkOrderJpaRepository();
	}

	@Override
	public PaymentMeanRepository forPaymentMean() {
		return new PaymentMeanJpaRepository();
	}

	@Override
	public InvoiceRepository forInvoice() {
		return new InvoiceJpaRepository();
	}

	@Override
	public ClientRepository forClient() {
		return new ClientJpaRepository();
	}

	@Override
	public SparePartRepository forSparePart() {
		return new SparePartJpaRepository();
	}

	@Override
	public InterventionRepository forIntervention() {
		return new InterventionJpaRepository();
	}

	@Override
	public VehicleRepository forVehicle() {
		return new VehicleJpaRepository();
	}

	@Override
	public VehicleTypeRepository forVehicleType() {
		return new VehicleTypeJpaRepository();
	}

	@Override
	public CertificateRepository forCertificate() {
		return new CertificateJpaRepository();
	}

	@Override
	public CourseRepository forCourse() {
		return new CourseJpaRepository();
	}

	@Override
	public EnrollmentRepository forEnrollment() {
		return new EnrollmentJpaRepository();
	}

	@Override
	public DedicationRepository forDedication() {
		return new DedicationJpaRepository();
	}

}
