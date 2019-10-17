package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
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
		this.workorderID = workorderID;
	}

	/*
	 * 
	 * - the mechanic does not exist, or
	 *  - the work order does not exist, or
	 *  - the work order is not in OPEN status
	 */
	public void execute() throws BusinessException {
		if(!existMechanic(mechanicID)) {
			throw new BusinessException("The mechanic does not exist");
		}
		WorkOrderDto dto = getWorkOrder(workorderID);
		if(dto == null) {
			throw new BusinessException("The workorder does not exist");
		}
		if(!dto.status.equals("OPEN")) {
			throw new BusinessException("The workorder is not available");
		}
		
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			gateway.setConnection(c);
			gateway.assignMechanic(workorderID, mechanicID);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}

	}

	private boolean existMechanic(Long mechanicID) {
		try (Connection c = Jdbc.getConnection()) {
			MechanicGateway gateway = Factory.persistance.getMechanicCrudService();
			gateway.setConnection(c);
			return gateway.findByID(mechanicID) == null;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
	
	private WorkOrderDto getWorkOrder(Long workorderId) {
		try (Connection c = Jdbc.getConnection()) {
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			gateway.setConnection(c);
			return gateway.findWorkOrderByID(workorderId);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
	


}
