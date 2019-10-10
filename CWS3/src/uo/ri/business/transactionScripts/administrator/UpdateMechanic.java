package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.PersistanceFactory;
import uo.ri.persistance.MechanicGateway;

public class UpdateMechanic {
	private MechanicDto mechanicDto;

	public UpdateMechanic(MechanicDto mechanicDto) {
		super();
		this.mechanicDto = mechanicDto;
	}

	public void execute() {
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			MechanicGateway gateway = PersistanceFactory.getMechanicCrudService();
			gateway.setConnection(c);
			gateway.update(mechanicDto);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

}
