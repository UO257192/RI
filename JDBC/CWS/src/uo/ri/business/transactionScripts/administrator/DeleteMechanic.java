package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistanceFactory;
import uo.ri.persistance.MechanicGateway;

public class DeleteMechanic {
	private long id;

	public DeleteMechanic(long id) {
		super();
		this.id = id;
	}

	public void execute() {
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			MechanicGateway gateway = PersistanceFactory.getMechanicCrudService();
			gateway.setConnection(c);
			gateway.delete(id);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
}
