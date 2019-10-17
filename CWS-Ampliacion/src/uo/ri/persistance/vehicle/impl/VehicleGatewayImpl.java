package uo.ri.persistance.vehicle.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.vehicle.VehicleGateway;

public class VehicleGatewayImpl implements VehicleGateway {

	Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public VehicleDto findVehicleByPlate(String plate) {
		VehicleDto dto = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_VEHICLE_BY_PLATE");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, plate);
			rs = pst.executeQuery();
			while (rs.next()) {
				dto = new VehicleDto();
				dto.id = rs.getLong(1);
				dto.make = rs.getString(2);
				dto.model = rs.getString(3);
				dto.plate = rs.getString(4);
				dto.clientId = rs.getLong(5);
				dto.vehicleTypeId = rs.getLong(6);
			}
			return dto;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}
}
