package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.ampliacion.CourseDto;
import uo.ri.business.dto.ampliacion.EnrollmentDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.CourseGateway;
import uo.ri.persistance.EnrollmentGateway;
import uo.ri.persistance.MechanicGateway;

public class GenerateCertificates {

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			
			MechanicGateway mechanicGateway = Factory.persistance.getMechanicCrudService();
			EnrollmentGateway enrollmentGateway = Factory.persistance.getEnrollmentGateway();
			CourseGateway courseGateway = Factory.persistance.getCourseGateway();
			mechanicGateway.setConnection(c);
			enrollmentGateway.setConnection(c);
			courseGateway.setConnection(c);
			List<MechanicDto> mechanicDtos = mechanicGateway.findAll();
			for (MechanicDto mechanicDto : mechanicDtos) {
				List<EnrollmentDto> enrollmentDtos = enrollmentGateway.findPassedEnrollmentsByMechanicId(mechanicDto.id);
				List<CourseDto> courseDtos = new ArrayList<CourseDto>();
				for (EnrollmentDto enrollmentDto : enrollmentDtos) {
					courseDtos.add(courseGateway.findCourseByID(Long.valueOf(enrollmentDto.courseId)));
				}
			}
			
			

		

			
			
//			if(gateway.findByDNI(mechanicDto.dni) != null) {
//				c.rollback();
//				throw new BusinessException("Ya existe un mecanico con este DNI");
//			}
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}

	}

}
