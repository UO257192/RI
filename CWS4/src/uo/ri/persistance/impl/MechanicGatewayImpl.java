package uo.ri.persistance.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.MechanicGateway;

public class MechanicGatewayImpl implements MechanicGateway {
	private Connection c;

	@Override
	public void add(MechanicDto mechanic) {
		// Process
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_ADD_MECHANIC");
		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, mechanic.dni);
			pst.setString(2, mechanic.name);
			pst.setString(3, mechanic.surname);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Long mechanic_id) {
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_MECHANIC");
		PreparedStatement pst = null;
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, mechanic_id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(MechanicDto mechanic) {
		String SQL = Conf.getInstance().getProperty("SQL_UPDATE_MECHANIC");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, mechanic.name);
			pst.setString(2, mechanic.surname);
			pst.setLong(3, mechanic.id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public List<MechanicDto> findAll() {
		List<MechanicDto> mechanicDtos = new ArrayList<MechanicDto>();
		String SQL = Conf.getInstance().getProperty("SQL_LIST_MECHANIC");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			while (rs.next()) {
				MechanicDto mechanicDto = new MechanicDto();
				mechanicDto.id = rs.getLong(1);
				mechanicDto.dni = rs.getString(2);
				mechanicDto.name = rs.getString(3);
				mechanicDto.surname = rs.getString(4);
				mechanicDtos.add(mechanicDto);
			}
			return mechanicDtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public MechanicDto findByID(Long idMechanic) {
		MechanicDto mechanicDto = null;
		String SQL = Conf.getInstance().getProperty("SQL_LIST_MECHANIC_BY_ID");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, idMechanic);
			rs = pst.executeQuery();
			if(rs.next()) {
				mechanicDto = new MechanicDto();
				mechanicDto.id = rs.getLong(1);
				mechanicDto.dni = rs.getString(2);
				mechanicDto.name = rs.getString(3);
				mechanicDto.surname = rs.getString(4);
			}
			return mechanicDto;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public MechanicDto findByDNI(String dni) {
		MechanicDto mechanicDto = null;
		String SQL = Conf.getInstance().getProperty("SQL_LIST_MECHANIC_BY_DNI");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, dni);
			rs = pst.executeQuery();
			if(rs.next()) {
				mechanicDto = new MechanicDto();
				mechanicDto.id = rs.getLong(1);
				mechanicDto.dni = rs.getString(2);
				mechanicDto.name = rs.getString(3);
				mechanicDto.surname = rs.getString(4);
			}
			return mechanicDto;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

}
