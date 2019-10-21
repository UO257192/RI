package uo.ri.persistance.training.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.training.EnrollmentGateway;

public class EnrollmentGatewayImpl implements EnrollmentGateway {
	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public List<EnrollmentDto> findPassedEnrollmentsByMechanicId(Long mechanic_id) {
		List<EnrollmentDto> enrollmentDtos = new ArrayList<EnrollmentDto>();
		String SQL = Conf.getInstance().getProperty("SQL_FIND_PASSED_COURSES_BY_MECHANIC");
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

	@Override
	public List<Long> findPassedMechanicIDS() {
		List<Long> mechanicIDS = new ArrayList<Long>();
		String SQL = Conf.getInstance().getProperty("SQL_FIND_PASSED_MECHANIC");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			while (rs.next()) {
				mechanicIDS.add(rs.getLong(1));
			}
			return mechanicIDS;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<Long> findPassedCoursessByMechanicId(Long mechanic_id) {
		List<Long> coursesID = new ArrayList<Long>();
		String SQL_FIND_PASSED_COURSES_FOR_MECHANIC = Conf.getInstance().getProperty("SQL_FIND_PASSED_COURSES_FOR_MECHANIC");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL_FIND_PASSED_COURSES_FOR_MECHANIC);
			pst.setLong(1, mechanic_id);
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
	public int findAttendanceForCourseAndMechanic(Long courseID, Long mechanicId) {
		String SQL_FIND_ATTENDANCE_BY_COURSE_AND_MECHANIC = Conf.getInstance().getProperty("SQL_FIND_ATTENDANCE_BY_COURSE_AND_MECHANIC");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL_FIND_ATTENDANCE_BY_COURSE_AND_MECHANIC);
			pst.setLong(1, courseID);
			pst.setLong(2, mechanicId);
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

	@Override
	public List<Long> findCoursesByMechanicId(Long mechanic_id) {
		List<Long> coursesID = new ArrayList<Long>();
		String SQL_FIND_ALL_COURSES_BY_MECHANIC = Conf.getInstance().getProperty("SQL_FIND_ALL_COURSES_BY_MECHANIC");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL_FIND_ALL_COURSES_BY_MECHANIC);
			pst.setLong(1, mechanic_id);
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

}
