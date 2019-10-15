package uo.ri.persistance.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.ampliacion.EnrollmentDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.EnrollmentGateway;

public class EnrollmentGatewayImpl implements EnrollmentGateway {
	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public List<EnrollmentDto> findPassedEnrollmentsByMechanicId(Long mechanic_id) {
		List<EnrollmentDto> enrollmentDtos = new ArrayList<EnrollmentDto>();
		String SQL = Conf.getInstance().getProperty("SQL_FIND_PASSED_COURSES_BY_ID");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, mechanic_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				EnrollmentDto dto = new EnrollmentDto();
				dto.id = rs.getLong(1);
				dto.attendance = rs.getInt(2);
				dto.passed = rs.getBoolean(3);
				dto.courseId = "" + rs.getLong(4);
				dto.mechanicId = "" + rs.getLong(5);
				enrollmentDtos.add(dto);
			}
			return enrollmentDtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
