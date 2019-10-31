package uo.ri.business.transactionScripts.administrator.vehicle;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.vehicle.VehicleGateway;

public class FindVehicleByPlate {
	
	private String plate;

	public FindVehicleByPlate(String plate) {
		this.plate = plate;
	}
	
	public VehicleDto execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			VehicleGateway gateway = Factory.persistance.getVehicleGateway();
			gateway.setConnection(c);
			VehicleDto dto = gateway.findVehicleByPlate(plate);
			if(dto == null) {
				throw new BusinessException("Vehicle with plate: " + this.plate + " does not exists");
			}
			return dto;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
}
