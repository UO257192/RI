package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.interventions.InterventionGateway;
import uo.ri.persistance.workorder.WorkOrderGateway;

public class RemoveWorkOrder {
	private Long workOrderID;

	public RemoveWorkOrder(Long workOrderID) {
		super();
		this.workOrderID = workOrderID;
	}

	public void execute() throws BusinessException {
		checkInterventions();
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			gateway.setConnection(c);
			if(gateway.findWorkOrderByID(workOrderID) == null) {
				c.rollback();
				throw new BusinessException("WorkOrder: " + workOrderID + " does not exist");
			}
			gateway.remove(workOrderID);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	private void checkInterventions() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			InterventionGateway gateway = Factory.persistance.getInterventionGateway();
			gateway.setConnection(c);
			if (gateway.hasInterventions(workOrderID)) {
				c.rollback();
				throw new BusinessException("WorkOrder: " + workOrderID + " has interventions");
			}

		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

}
