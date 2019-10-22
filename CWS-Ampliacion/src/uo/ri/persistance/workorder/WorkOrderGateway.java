package uo.ri.persistance.workorder;

import java.sql.Connection;

import uo.ri.business.dto.WorkOrderDto;

public interface WorkOrderGateway {

	/**
	 * Add a new workorder to the system with the data specified in the dto. The id
	 * value will be ignored
	 * 
	 * @param workorder dto
	 */
	void save(WorkOrderDto dto);

	/**
	 * Remove a workorder to the system for an id.
	 * 
	 * @param mechanic_id
	 */
	void remove(Long workorderID);

	/**
	 * Find the last workorder id created
	 * 
	 * @return last workorder id
	 */
	Long findLastWorkOrder();

	/**
	 * 
	 * @param workorder dto
	 * @return true if workorder exist : false if not
	 */
	boolean findWorkOrderByData(WorkOrderDto dto);

	/**
	 * Assign a mechanic to a workorder
	 * 
	 * @param workorderID
	 * @param mechanicID
	 */
	void assignMechanic(Long workorderID, Long mechanicID);

	/**
	 * @param id of the workorder
	 * @return the dto for the workorder or null if there is none with the id
	 */
	WorkOrderDto findWorkOrderByID(Long id);

	/**
	 * Update a workorder to the system with the data specified in the dto.
	 * 
	 * @param workorder dto
	 */
	void updateWorkOrder(WorkOrderDto dto);

	/**
	 * Set the database connection
	 * 
	 * @param database connection
	 */
	void setConnection(Connection c);

	/**
	 * 
	 * @param workOrderID
	 * @return workorder status
	 */
	String checkWorkOrderStatus(Long workOrderID);

	/**
	 * Update workorder total to the system with the data specified.
	 * @param workOrderID
	 * @param total
	 */
	void updateWorkorderTotal(Long workOrderID, double total);

	
	void linkWorkOrderInvoice(long invoiceId, Long workOrderID);

	void updateWorkOrderStatus(Long breakdownId, String status);

	double checkTotalLabor(Long workOrderID);

	double checkTotalParts(Long workOrderID);
}
