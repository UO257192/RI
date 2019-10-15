package uo.ri.persistance;

import java.sql.Connection;

public interface WorkOrderGateway {

	void setConnection(Connection c);

	String checkWorkOrderStatus(Long workOrderID);

	void updateWorkorderTotal(Long workOrderID, double total);

	void linkWorkOrderInvoice(long invoiceId, Long workOrderID);

	void updateWorkOrderStatus(Long breakdownId, String status);
	
	double checkTotalLabor(Long workOrderID);
	
	double checkTotalParts(Long workOrderID);
}
