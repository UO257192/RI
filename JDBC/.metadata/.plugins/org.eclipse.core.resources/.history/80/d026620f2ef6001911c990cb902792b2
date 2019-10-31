package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.training.CourseGateway;
import uo.ri.persistance.training.DedicationGateway;
import uo.ri.persistance.vehicle.VehicleTypeGateway;

public class UpdateCourse {

	private CourseDto dto;

	public UpdateCourse(CourseDto dto) {
		this.dto = dto;
	}
	
	public void execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			CourseGateway courseGateway = Factory.persistance.getCourseGateway();
			courseGateway.setConnection(c);
			if (courseGateway.findCourseByID(dto.id) == null) {
				c.rollback();
				throw new BusinessException("This course does not exist");
			}

			if (dto.startDate.after(dto.endDate)) {
				c.rollback();
				throw new BusinessException("Start Date is after End date");
			}

			if (dto.endDate.before(dto.startDate)) {
				c.rollback();
				throw new BusinessException("End Date is before Start Date");
			}
			if (dto.hours <= 0) {
				c.rollback();
				throw new BusinessException("The number of hours are zero or negative");
			}

			if (dto.code.trim().length() == 0 || dto.name.trim().length() == 0 || dto.description.trim().length() == 0
					|| dto.startDate == null || dto.endDate == null) {
				c.rollback();
				throw new BusinessException("Some value is empty");
			}
			
			if (dto.percentages.size() == 0) {
				c.rollback();
				throw new BusinessException("There are no dedications specified");
			}
			VehicleTypeGateway gateVehicleTypeGateway = Factory.persistance.getVehicleTypeGateway();
			gateVehicleTypeGateway.setConnection(c);
			for (Map.Entry<Long, Integer> entry : dto.percentages.entrySet()) {
				if (gateVehicleTypeGateway.findVehicleTypeById(entry.getKey()) == null) {
					c.rollback();
					throw new BusinessException("There is percentage devoted to a non existing vehicle type");
				}
			}

			courseGateway.update(dto);
			c.commit();
			reLoadDedication();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	private void reLoadDedication() {
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			DedicationGateway dedicationGateway = Factory.persistance.getDedicationGateway();
			dedicationGateway.setConnection(c);
			dedicationGateway.removeByCourseID(dto.id);
			for (Map.Entry<Long, Integer> entry : dto.percentages.entrySet()) {
				dedicationGateway.add(dto.id,entry.getKey(), entry.getValue());
			}
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
}
