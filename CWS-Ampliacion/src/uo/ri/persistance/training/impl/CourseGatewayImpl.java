package uo.ri.persistance.training.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.training.CourseGateway;

public class CourseGatewayImpl implements CourseGateway {
	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public CourseDto findCourseByID(Long courseID) {
		CourseDto dto = new CourseDto();
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_BY_ID");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, courseID);
			rs = pst.executeQuery();
			while (rs.next()) {
				dto.id = rs.getLong(1);
				dto.code = rs.getString(2);
				dto.description = rs.getString(3);
				dto.endDate = rs.getDate(4);
				dto.hours = rs.getInt(5);
				dto.name = rs.getString(6);
				dto.startDate = rs.getDate(7);
			}
			return dto;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<CourseDto> findAll() {
		List<CourseDto> dtos = new ArrayList<CourseDto>();
		String SQL_FIND_ALL_COURSES = Conf.getInstance().getProperty("SQL_FIND_ALL_COURSES");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL_FIND_ALL_COURSES);
			rs = pst.executeQuery();
			while (rs.next()) {
				CourseDto dto = new CourseDto();
				dto.id = rs.getLong(1);
				dto.code = rs.getString(2);
				dto.description = rs.getString(3);
				dto.endDate = rs.getDate(4);
				dto.hours = rs.getInt(5);
				dto.name = rs.getString(6);
				dto.startDate = rs.getDate(7);
				dtos.add(dto);
			}
			return dtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
