package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.workorder.WorkOrderGateway;

public class UpdateWorkOrderDescription {

	private WorkOrderDto workOrderDto;

	public UpdateWorkOrderDescription(WorkOrderDto workOrderDto) {
		super();
		this.workOrderDto = workOrderDto;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			gateway.setConnection(c);
			if(!workOrderDto.status.equals("OPEN") && workOrderDto.status.equals("ASSIGNED")) {
				throw new BusinessException("The WorkORder: "+  workOrderDto.id + " is openned or assigned.");
			}
			gateway.updateWorkOrder(workOrderDto);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}

	}

}
