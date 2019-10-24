package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.conf.Factory;
import uo.ri.persistance.training.EnrollmentGateway;

public class FindAllEnrollmentsByCourse {
	
	private Long id;
	
	public FindAllEnrollmentsByCourse(Long id) {
		this.id = id;
	}
	
	public List<EnrollmentDto> execute() {
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			EnrollmentGateway gateway = Factory.persistance.getEnrollmentGateway();
			gateway.setConnection(c);
			return gateway.findEnrollmentByCourseID(id);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
}
