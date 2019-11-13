package uo.ri.cws.infrastructure.persistence.jpa.repository;

import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class WorkOrderJpaRepository extends BaseJpaRepository<WorkOrder> implements WorkOrderRepository {

	@Override
	public List<WorkOrder> findByIds(List<String> idsAveria) {
		return Jpa.getManager().createNamedQuery("WorkOrder.findByIds", WorkOrder.class).setParameter(1, idsAveria)
				.getResultList();
	}

	@Override
	public Optional<WorkOrder> findByVehicleAndDate(Vehicle vehicle, Date date) {
		return Jpa.getManager().createNamedQuery("Course.findCourseHoursForCertificate", WorkOrder.class)
				.setParameter(1, vehicle).setParameter(2, date).getResultList().stream().findFirst();
	}

}
