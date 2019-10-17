package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.Factory;
import uo.ri.persistance.workorder.WorkOrderGateway;

public class FindWorkOrderByID {
	private Long id;

	public FindWorkOrderByID(Long id) {
		super();
		this.id = id;
	}

	public WorkOrderDto execute(){
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			gateway.setConnection(c);
			WorkOrderDto dto = gateway.findWorkOrderByID(id);
			c.commit();
			return dto;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}

	}
}
