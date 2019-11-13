package uo.ri.cws.application.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.WorkOrder;

public interface WorkOrderRepository extends Repository<WorkOrder> {

	/**
	 * @param idsAveria, lista de los id de avería a recuperar
	 * @return lista con averias cuyo id aparece en idsAveria, o lista vacía si no
	 *         hay ninguna
	 */
	List<WorkOrder> findByIds(List<String> workOrderIds);

	/**
	 *
	 * @param vehicle Workorder Vehicle
	 * @param date WorkOrder date
	 * @return WorkOrder for the vehicle at the date
	 */
	Optional<WorkOrder> findByVehicleAndDate(Vehicle vehicle, Date date);

}