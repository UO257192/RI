package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.training.CourseGateway;
import uo.ri.persistance.training.DedicationGateway;
import uo.ri.persistance.training.EnrollmentGateway;

public class DeleteCourse {
	
	private Long id;
	
	public DeleteCourse(Long id) {
		this.id = id;
	}
	
	public void execute() throws BusinessException {
		if(check()) {
			throw new BusinessException("This course have registred mechanics");
		}
		try (Connection c = Jdbc.getConnection()){
			CourseGateway courseGateway = Factory.persistance.getCourseGateway();
			courseGateway.setConnection(c);
			if(courseGateway.findCourseByID(id) == null) {
				c.rollback();
				throw new BusinessException("");
			}
			deleteDedications();
			courseGateway.delete(id);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
		
		
	}
	
	private boolean check() {
		try (Connection c = Jdbc.getConnection()){
			EnrollmentGateway gateway = Factory.persistance.getEnrollmentGateway();
			gateway.setConnection(c);
			return gateway.findRegistrationByCourse(id) > 0;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
	
	private void deleteDedications() {
		try (Connection c = Jdbc.getConnection()){
			DedicationGateway gateway = Factory.persistance.getDedicationGateway();
			gateway.setConnection(c);
			gateway.removeByCourseID(id);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

}
