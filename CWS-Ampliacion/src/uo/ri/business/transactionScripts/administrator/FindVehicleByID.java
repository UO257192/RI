package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.vehicle.VehicleGateway;

public class FindVehicleByID {

	private Long vehicleId;

	public FindVehicleByID(Long vehicleId) {
		super();
		this.vehicleId = vehicleId;
	}

	public VehicleDto execute() throws BusinessException {
		VehicleDto dto = null;
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			VehicleGateway gateway = Factory.persistance.getVehicleGateway();
			gateway.setConnection(c);
			dto = gateway.findVehicleByID(vehicleId);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
		return dto;

	}

}
