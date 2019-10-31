package uo.ri.business.transactionScripts.administrator.courseReport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.training.CourseGateway;
import uo.ri.persistance.training.DedicationGateway;
import uo.ri.persistance.training.EnrollmentGateway;
import uo.ri.persistance.vehicle.VehicleTypeGateway;

public class ListTrainingForMechanic {

	private Long mechanic_id;

	public ListTrainingForMechanic(Long id) {
		this.mechanic_id = id;
	}

	public List<TrainingForMechanicRow> execute() throws BusinessException {
		List<TrainingForMechanicRow> trainingForMechanicRows = new ArrayList<TrainingForMechanicRow>();
		List<VehicleTypeDto> vehicleTypeDtos = findAllVehicleTypes();
		for (VehicleTypeDto vehicleTypeDto : vehicleTypeDtos) {
			List<Long> courses = findCoursesForMechanicAndVehicleType(mechanic_id, vehicleTypeDto.id);
			TrainingForMechanicRow tfh = new TrainingForMechanicRow();
			tfh.vehicleTypeName = vehicleTypeDto.name;
			tfh.enrolledHours = 0;
			tfh.attendedHours = 0;
			for (Long courseid : courses) {
				int hours = getCourseDuration(courseid);
				int pertentage = getPercentageForCourseVehicleType(courseid, vehicleTypeDto.id);
				tfh.enrolledHours += (hours*pertentage/100);
				int attendance = getAttendanceForMechanicInCourse(courseid, mechanic_id);

				tfh.attendedHours += (hours * attendance / 100) * pertentage /100;
			}
			trainingForMechanicRows.add(tfh);
		}

		return trainingForMechanicRows;
	}
	
	public int getPercentageForCourseVehicleType(Long courseID, Long vehicleTypeId) {
		try (Connection c = Jdbc.getConnection()) {
			DedicationGateway gateway = Factory.persistance.getDedicationGateway();
			gateway.setConnection(c);
			return gateway.findPercentageForCourse(courseID, vehicleTypeId);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public List<VehicleTypeDto> findAllVehicleTypes() {
		try (Connection c = Jdbc.getConnection()) {
			VehicleTypeGateway gateway = Factory.persistance.getVehicleTypeGateway();
			gateway.setConnection(c);
			return gateway.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public List<Long> findCoursesForMechanicAndVehicleType(Long mechanicID, Long vehicleTypeID) {
		List<Long> coursesIDForMechanic = findCoursesByMechanicId(mechanicID);
		List<Long> coursesIDForVehicleType = findCoursesByVehicleType(vehicleTypeID);
		List<Long> courses = new ArrayList<Long>();
		for (Long courseIds : coursesIDForVehicleType) {
			if (coursesIDForMechanic.contains(courseIds)) {
				courses.add(courseIds);
			}
		}
		return courses;

	}

	public List<Long> findCoursesByMechanicId(Long mechanic_id) {
		try (Connection c = Jdbc.getConnection()) {
			EnrollmentGateway gateway = Factory.persistance.getEnrollmentGateway();
			gateway.setConnection(c);
			return gateway.findCoursesByMechanicId(mechanic_id);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public List<Long> findCoursesByVehicleType(Long vehicleType) {
		try (Connection c = Jdbc.getConnection()) {
			DedicationGateway gateway = Factory.persistance.getDedicationGateway();
			gateway.setConnection(c);
			return gateway.findCoursesByVehicleType(vehicleType);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public int getCourseDuration(Long courseID) {
		try (Connection c = Jdbc.getConnection()) {
			CourseGateway gateway = Factory.persistance.getCourseGateway();
			gateway.setConnection(c);
			return gateway.findCourseByID(courseID).hours;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public int getAttendanceForMechanicInCourse(Long courseID, Long mechanicID) {
		try (Connection c = Jdbc.getConnection()) {
			EnrollmentGateway gateway = Factory.persistance.getEnrollmentGateway();
			gateway.setConnection(c);
			return gateway.findAttendanceForCourseAndMechanic(courseID, mechanicID);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
}