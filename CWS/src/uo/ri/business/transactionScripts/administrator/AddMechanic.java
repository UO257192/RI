package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistanceFactory;
import uo.ri.persistance.MechanicGateway;

public class AddMechanic {

	private MechanicDto mechanicDto;

	public AddMechanic(MechanicDto mechanicDto) {
		super();
		this.mechanicDto = mechanicDto;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			MechanicGateway gateway = PersistanceFactory.getMechanicCrudService();
			gateway.setConnection(c);
			if(gateway.findByDNI(mechanicDto.dni) != null) {
				c.rollback();
				throw new BusinessException("Ya existe un mecanico con este DNI");
			}
			gateway.add(mechanicDto);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}

	}

}
