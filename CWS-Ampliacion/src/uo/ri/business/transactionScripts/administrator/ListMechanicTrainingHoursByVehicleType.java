package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Factory;
import uo.ri.persistance.certificate.CertificateGateway;
import uo.ri.persistance.mechanic.MechanicGateway;
import uo.ri.persistance.training.CourseGateway;
import uo.ri.persistance.training.DedicationGateway;
import uo.ri.persistance.training.EnrollmentGateway;
import uo.ri.persistance.vehicle.VehicleTypeGateway;

public class ListMechanicTrainingHoursByVehicleType {

	public List<TrainingHoursRow> execute() {
		List<TrainingHoursRow> trainingHoursRows = new ArrayList<TrainingHoursRow>();
		List<VehicleTypeDto> vehicleTypeDtos = findAllVehicleTypes();
		String lastVehicleTypeName = "";
		for (VehicleTypeDto vehicleTypeDto : vehicleTypeDtos) {
			List<Long> mechanicIDs = findPassedMechanics();
			for (Long mechanicID : mechanicIDs) {
				int totalHours = 0;
				String name = "";
				List<Long> coursesIDs = findCoursesForMechanicAndVehicleType(mechanicID, vehicleTypeDto.id);
				for (Long courseID : coursesIDs) {
					int hours = getCourseDuration(courseID);
					int attendance = getAttendanceForMechanicInCourse(courseID, mechanicID);
					totalHours += hours * attendance / 100;
					name = findMechanicFullNameByID(mechanicID);
				}
				if(totalHours>0) {
					TrainingHoursRow trainingHoursRow = new TrainingHoursRow();
					trainingHoursRow.enrolledHours = totalHours;
					trainingHoursRow.mechanicFullName = name;
					if(lastVehicleTypeName.equals("") || !lastVehicleTypeName.equals(vehicleTypeDto.name)) {
						lastVehicleTypeName = vehicleTypeDto.name;
						trainingHoursRow.vehicleTypeName = vehicleTypeDto.name;
					}else {
						trainingHoursRow.vehicleTypeName = "";
					}
					trainingHoursRows.add(trainingHoursRow);
				}
			}
		}
		return trainingHoursRows;
	}
	
	public String findMechanicFullNameByID(Long mechanicID) {
		try (Connection c = Jdbc.getConnection()) {
			MechanicGateway gateway = Factory.persistance.getMechanicCrudService();
			gateway.setConnection(c);
			MechanicDto dto = gateway.findByID(mechanicID);
			return dto.name + " " + dto.surname;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public void generateCretificate(Long mechanicID, Long vehicleTypeID) {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			CertificateGateway gateway = Factory.persistance.getCertificateGateway();
			gateway.setConnection(c);
			gateway.generateCertificate(mechanicID, vehicleTypeID);
			c.commit();
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

	public int getPercentageForCourseVehicleType(Long courseID, Long vehicleTypeId) {
		try (Connection c = Jdbc.getConnection()) {
			DedicationGateway gateway = Factory.persistance.getDedicationGateway();
			gateway.setConnection(c);
			return gateway.findPercentageForCourse(courseID, vehicleTypeId);
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

	public List<Long> findCoursesForMechanicAndVehicleType(Long mechanicID, Long vehicleTypeID) {
		// sacamos cursos aprobados por mechanic_id
		List<Long> coursesIDForMechanic = findCoursesByMechanicId(mechanicID);

		// sacarmos cursos dedicados a vehicleType
		List<Long> coursesIDForVehicleType = findCoursesByVehicleType(vehicleTypeID);

		List<Long> courses = new ArrayList<Long>();

		for (Long courseIds : coursesIDForVehicleType) {
			if (coursesIDForMechanic.contains(courseIds)) {
				courses.add(courseIds);
			}
		}
		return courses;

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

	public List<Long> findPassedMechanics() {
		try (Connection c = Jdbc.getConnection()) {
			EnrollmentGateway gateway = Factory.persistance.getEnrollmentGateway();
			gateway.setConnection(c);
			return gateway.findPassedMechanicIDS();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public boolean findCertificateByMechanic(Long mechLong, Long vehicletypeId) {
		try (Connection c = Jdbc.getConnection()) {
			CertificateGateway gateway = Factory.persistance.getCertificateGateway();
			gateway.setConnection(c);
			return gateway.findCertificateByMechanic(mechLong, vehicletypeId) == null;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
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

}
