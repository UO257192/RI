package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.workorder.WorkOrderGateway;

public class AddWorkOrder {
	private WorkOrderDto workOrderDto;

	public AddWorkOrder(WorkOrderDto workOrderDto) {
		super();
		this.workOrderDto = workOrderDto;
	}

	public WorkOrderDto execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			gateway.setConnection(c);
			this.workOrderDto.date =  new java.sql.Date(new java.util.Date().getTime());
			if(gateway.findWorkOrderByData(this.workOrderDto)){
				c.rollback();
				throw new BusinessException("A WorkOrder already exist for date: " + this.workOrderDto.date + " and for vehicle: " + this.workOrderDto.id);
			}
			gateway.save(workOrderDto);
			c.commit();
			this.workOrderDto.id = gateway.findLastWorkOrder();
			return this.workOrderDto;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}

	}
}
