package uo.ri.business.transactionScripts.administrator.vehicleType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Factory;
import uo.ri.persistance.vehicle.VehicleTypeGateway;

public class ListVehicleTypes {
	
	public List<VehicleTypeDto> execute(){
		try (Connection c = Jdbc.getConnection()) {
			VehicleTypeGateway gateway = Factory.persistance.getVehicleTypeGateway();
			gateway.setConnection(c);
			return gateway.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

}
