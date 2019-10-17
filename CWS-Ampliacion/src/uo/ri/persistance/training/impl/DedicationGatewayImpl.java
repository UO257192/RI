package uo.ri.persistance.training.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.DedicationDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.training.DedicationGateway;

public class DedicationGatewayImpl implements DedicationGateway {
	
	Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public List<DedicationDto> findDedicationByCourse(Long course_id) {
		List<DedicationDto> dedicationDtos = new ArrayList<DedicationDto>();
		String SQL = Conf.getInstance().getProperty("SQL_FIND_DEDICATION_BY_COURSE_ID");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, course_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				DedicationDto dto = new DedicationDto();
				dto.id = rs.getLong(1);
				dto.course_id = rs.getLong(2);
				dto.vehicleType_id = rs.getLong(3);
				dto.percentage = rs.getInt(4);
				dedicationDtos.add(dto);
			}
			return dedicationDtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<Long> findCoursesByVehicleType(Long vehicletype_id) {
		List<Long> coursesID = new ArrayList<Long>();
		String SQL_FIND_COURSE_BY_VEHICLETYPE = Conf.getInstance().getProperty("SQL_FIND_COURSE_BY_VEHICLETYPE");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL_FIND_COURSE_BY_VEHICLETYPE);
			pst.setLong(1, vehicletype_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				coursesID.add(rs.getLong(1));
			}
			return coursesID;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public int findPercentageForCourse(Long courseID, Long vehicleTypeId) {
		String SQL_FIND_DEDICATION_BY_COURSE_AND_VEHICLETYPE = Conf.getInstance().getProperty("SQL_FIND_DEDICATION_BY_COURSE_AND_VEHICLETYPE");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL_FIND_DEDICATION_BY_COURSE_AND_VEHICLETYPE);
			pst.setLong(1, courseID);
			pst.setLong(2, vehicleTypeId);
			rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
