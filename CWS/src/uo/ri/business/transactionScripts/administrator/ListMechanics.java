package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.PersistanceFactory;
import uo.ri.persistance.MechanicGateway;

public class ListMechanics {

	public List<MechanicDto> execute() {
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			MechanicGateway gateway = PersistanceFactory.getMechanicCrudService();
			gateway.setConnection(c);
			return gateway.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
}
