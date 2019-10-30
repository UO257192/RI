package uo.ri.business.transactionScripts.administrator.course;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.conf.Factory;
import uo.ri.persistance.training.CourseGateway;

public class FindCourseByID {
	private Long id;
	
	public FindCourseByID(Long id) {
		this.id = id;
	}
	
	public CourseDto execute() {
		try (Connection c = Jdbc.getConnection()) {
			CourseGateway gateway = Factory.persistance.getCourseGateway();
			gateway.setConnection(c);
			return gateway.findCourseByID(id);	
		} catch (SQLException e) {
			throw new RuntimeException("findAllVehicleTypes");
		}
	}
}
