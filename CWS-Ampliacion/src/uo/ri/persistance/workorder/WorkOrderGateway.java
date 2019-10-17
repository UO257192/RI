package uo.ri.persistance.workorder;

import java.sql.Connection;

import uo.ri.business.dto.WorkOrderDto;

public interface WorkOrderGateway {

	void save(WorkOrderDto dto);
	
	void remove(Long workorderID);
	
	Long findLastWorkOrder();
	
	boolean findWorkOrderByData(WorkOrderDto dto);
	
	void assignMechanic(Long workorderID, Long mechanicID);
	
	WorkOrderDto findWorkOrderByID(Long id);
	
	void updateWorkOrder(WorkOrderDto dto);

	void setConnection(Connection c);

	String checkWorkOrderStatus(Long workOrderID);

	void updateWorkorderTotal(Long workOrderID, double total);

	void linkWorkOrderInvoice(long invoiceId, Long workOrderID);

	void updateWorkOrderStatus(Long breakdownId, String status);
	
	double checkTotalLabor(Long workOrderID);
	
	double checkTotalParts(Long workOrderID);
}
