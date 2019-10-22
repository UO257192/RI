package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.conf.Factory;
import uo.ri.persistance.training.CourseGateway;

public class FindAllCourses {

	public List<CourseDto> execute() {
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			CourseGateway gateway = Factory.persistance.getCourseGateway();
			gateway.setConnection(c);
			return gateway.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

}
