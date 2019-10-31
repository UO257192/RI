package uo.ri.persistance.vehicle.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.vehicle.VehicleTypeGateway;

public class VehicleTypeGatewayImpl implements VehicleTypeGateway {
	
	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
		
	}

	@Override
	public List<VehicleTypeDto> findAll() {
		List<VehicleTypeDto> vehicleTypeDtos = new ArrayList<VehicleTypeDto>();
		String SQL_FIND_ALL_TYPES = Conf.getInstance().getProperty("SQL_FIND_ALL_TYPES");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL_FIND_ALL_TYPES);
			rs = pst.executeQuery();
			while (rs.next()) {
				VehicleTypeDto dto = new VehicleTypeDto();
				dto.id = rs.getLong(1);
				dto.minTrainigHours = rs.getInt(2);
				dto.name = rs.getString(3);
				dto.pricePerHour = rs.getDouble(4);
				vehicleTypeDtos.add(dto);
			}
			return vehicleTypeDtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public VehicleTypeDto findVehicleTypeById(Long id) {
		VehicleTypeDto dto = null;
		String SQL_FIND_TYPE_BY_ID = Conf.getInstance().getProperty("SQL_FIND_TYPE_BY_ID");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL_FIND_TYPE_BY_ID);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				dto = new VehicleTypeDto();
				dto.id = rs.getLong(1);
				dto.minTrainigHours = rs.getInt(2);
				dto.name = rs.getString(3);
				dto.pricePerHour = rs.getDouble(4);
			}
			return dto;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
