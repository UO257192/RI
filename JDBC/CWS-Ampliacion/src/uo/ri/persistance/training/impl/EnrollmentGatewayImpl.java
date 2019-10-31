package uo.ri.persistance.training.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;
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

	@Override
	public int findRegistrationByCourse(Long id) {
		String SQL_FIND_REGISTRATION_TO_COURSE = Conf.getInstance().getProperty("SQL_FIND_REGISTRATION_TO_COURSE");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL_FIND_REGISTRATION_TO_COURSE);
			pst.setLong(1, id);
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
	public List<EnrollmentDto> findEnrollmentByCourseID(Long id) {
		List<EnrollmentDto> enrollmentDtos = new ArrayList<EnrollmentDto>();
		String SQL_FIND_ENROLLMENTS_BY_COURSEID = Conf.getInstance().getProperty("SQL_FIND_ENROLLMENTS_BY_COURSEID");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL_FIND_ENROLLMENTS_BY_COURSEID);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				EnrollmentDto dto = new EnrollmentDto();
				dto.id = rs.getLong(1);
				dto.attendance = rs.getInt(2);
				dto.passed = rs.getBoolean(3);
				dto.courseId = "" + rs.getLong(4);
				dto.mechanicId = "" + rs.getLong(5);
				dto.mechanic = new MechanicDto();
				dto.mechanic.name = rs.getString(6);
				dto.mechanic.surname = rs.getString(7);
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
	public EnrollmentDto findEnrollment(Long courseID, Long mechanicID) {
		EnrollmentDto dto = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ENROLLMENT_BY_IDS");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, courseID);
			pst.setLong(2, mechanicID);
			rs = pst.executeQuery();
			while (rs.next()) {
				dto = new EnrollmentDto();
				dto.id = rs.getLong(1);
				dto.attendance = rs.getInt(2);
				dto.passed = rs.getBoolean(3);
				dto.courseId = "" + rs.getLong(4);
				dto.mechanicId = "" + rs.getLong(5);
			}
			return dto;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void add(EnrollmentDto dto) {
		String SQL = Conf.getInstance().getProperty("SQL_ADD_ENROLLMENT");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			pst.setInt(1, dto.attendance);
			pst.setBoolean(2, dto.passed);
			pst.setLong(3, Long.parseLong(dto.courseId));
			pst.setLong(4, Long.parseLong(dto.mechanicId));
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}
	
	@Override
	public Long findLastID() {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_MAX_ID");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			return rs.next()? rs.getLong(1) : null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void delete(Long id) {
		String SQL_DELETE_ENROLLMENT = Conf.getInstance().getProperty("SQL_DELETE_ENROLLMENT");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL_DELETE_ENROLLMENT);
			pst.setLong(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public EnrollmentDto findEnrollmentByID(Long id) {
		EnrollmentDto dto = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ENROLLMENT_BY_ID");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				dto = new EnrollmentDto();
				dto.id = rs.getLong(1);
				dto.attendance = rs.getInt(2);
				dto.passed = rs.getBoolean(3);
				dto.courseId = "" + rs.getLong(4);
				dto.mechanicId = "" + rs.getLong(5);
			}
			return dto;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}
	
	

}