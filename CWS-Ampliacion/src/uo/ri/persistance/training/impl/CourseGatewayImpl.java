package uo.ri.persistance.training.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
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

	@Override
	public void add(CourseDto dto) {
		PreparedStatement pst = null;
		String SQL_ADD_COURSE = Conf.getInstance().getProperty("SQL_ADD_COURSE");
		try {
			pst = c.prepareStatement(SQL_ADD_COURSE);
			pst.setString(1, dto.code);
			pst.setString(2, dto.description);
			pst.setDate(3, new java.sql.Date(dto.endDate.getTime()));
			pst.setInt(4, dto.hours);
			pst.setString(5, dto.name);
			pst.setDate(6, new java.sql.Date(dto.startDate.getTime()));
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Long findMaxCourse() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_FIND_MAX_COURSE = Conf.getInstance().getProperty("SQL_FIND_MAX_COURSE");
		try {
			pst = c.prepareStatement(SQL_FIND_MAX_COURSE);
			rs = pst.executeQuery();
			return rs.next() ? rs.getLong(1) : null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public CourseDto findByName(String name) {
		CourseDto course = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_BY_NAME");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = c.prepareStatement(SQL);
			pst.setString(1, name);
			rs = pst.executeQuery();

			if (rs.next() == false)
				return course;

			course = new CourseDto();
			course.id = rs.getLong("id");
			course.code = rs.getString("code");
			course.description = rs.getString("description");
			course.endDate = rs.getDate("enddate");
			course.hours = rs.getInt("hours");
			course.name = rs.getString("name");
			course.startDate = rs.getDate("startdate");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return course;
	}

	@Override
	public CourseDto findByCode(String code) {
		CourseDto course = null;
		String SQL = Conf.getInstance().getProperty("SQL_COURSE_BY_COD");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = c.prepareStatement(SQL);
			pst.setString(1, code);
			rs = pst.executeQuery();

			if (rs.next() == false)
				return course;

			course = new CourseDto();
			course.id = rs.getLong("id");
			course.code = rs.getString("code");
			course.description = rs.getString("description");
			course.endDate = rs.getDate("enddate");
			course.hours = rs.getInt("hours");
			course.name = rs.getString("name");
			course.startDate = rs.getDate("startdate");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return course;
	}

	@Override
	public void delete(Long id) {
		String SQL_DELETE_COURSE = Conf.getInstance().getProperty("SQL_DELETE_COURSE");
		PreparedStatement pst = null;
		try {
			pst = c.prepareStatement(SQL_DELETE_COURSE);
			pst.setLong(1, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void update(CourseDto dto) {
		String SQL = Conf.getInstance().getProperty("SQL_UPDATE_COURSE");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, dto.code);
			pst.setString(2, dto.name);
			pst.setString(3, dto.description);
			pst.setDate(4, new java.sql.Date(dto.startDate.getTime()));
			pst.setDate(5, new java.sql.Date(dto.endDate.getTime()));
			pst.setInt(6, dto.hours);
			pst.setLong(7, dto.id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

}
