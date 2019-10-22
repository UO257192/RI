package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.training.CourseGateway;
import uo.ri.persistance.vehicle.VehicleTypeGateway;

public class RegisterNewCourse {
	
	private CourseDto dto;
	
	public RegisterNewCourse(CourseDto dto) {
		this.dto = dto;
	}
	/*
	 * 	- there is percentage devoted to a non existing vehicle type, or
	 * 	- the initial and final dates are in the past or inverted, or
	 * 	- the number of hours are zero or negative, or
	 *  - there are no dedications specified, or
	 *  - the sum of devoted percentages does not equals 100%, or
	 *  - the are any dedication with an invalid percentage (empty, zero, negative)
	*/
	public CourseDto execute() {
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			CourseGateway gateway = Factory.persistance.getCourseGateway();
			VehicleTypeGateway gateVehicleTypeGateway = Factory.persistance.getVehicleTypeGateway();
			gateVehicleTypeGateway.setConnection(c);
			gateway.setConnection(c);
			if(dto.code.length() == 0 || dto.name.length() == 0 || dto.description.length() == 0 || dto.startDate == null || dto.endDate == null || dto.hours <= 0) {
				c.rollback();
				throw new BusinessException("Some value is empty");
			}
			CourseDto aux = gateway.findCourseByID(dto.id);
			if(aux.name.equals(dto.name)) {
				c.rollback();
				throw new BusinessException("A Course with the same name already exists");
			}
			for (Map.Entry<Long, Integer>  entry : dto.percentages.entrySet()) {
			    if(entry.getKey()) {
			    	
			    }
			}
			
			gateway.save(workOrderDto);
			c.commit();
			this.workOrderDto.id = gateway.findLastWorkOrder();
			return this.workOrderDto;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
		
	}

}
