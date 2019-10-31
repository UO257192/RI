package uo.ri.business.transactionScripts.foreman.workorder;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.mechanic.MechanicGateway;
import uo.ri.persistance.workorder.WorkOrderGateway;

public class AssignMechanicToWorkorder {
	private Long workorderID;
	private Long mechanicID;

	public AssignMechanicToWorkorder(Long workorderID, Long mechanicID) {
		super();
		this.workorderID = workorderID;
		this.mechanicID = mechanicID;
	}

	/*
	 * 
	 * - the mechanic does not exist, or - the work order does not exist, or - the
	 * work order is not in OPEN status
	 */
	public void execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway gatewayWorkOrderGateway = Factory.persistance.getWorkOrderGateway();
			MechanicGateway gateMechanicGateway = Factory.persistance.getMechanicCrudService();
			gateMechanicGateway.setConnection(c);
			gatewayWorkOrderGateway.setConnection(c);
			if(gateMechanicGateway.findByID(mechanicID) == null) {
				c.rollback();
				throw new BusinessException("Mechanic id does not exist");
			}
			if(gatewayWorkOrderGateway.mechanicAssigned(mechanicID)) {
				c.rollback();
				throw new BusinessException("This workorder already has mechanic assigned");
			}
			gatewayWorkOrderGateway.assignMechanic(workorderID, mechanicID);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}

	}

}
