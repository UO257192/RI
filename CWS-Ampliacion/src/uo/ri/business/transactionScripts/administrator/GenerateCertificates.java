package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.ampliacion.CourseDto;
import uo.ri.business.dto.ampliacion.DedicationDto;
import uo.ri.business.dto.ampliacion.EnrollmentDto;
import uo.ri.business.dto.ampliacion.VehicleTypeDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.mechanic.MechanicGateway;
import uo.ri.persistance.training.CourseGateway;
import uo.ri.persistance.training.DedicationGateway;
import uo.ri.persistance.training.EnrollmentGateway;

public class GenerateCertificates {

	public void execute() throws BusinessException {
		List<MechanicDto> mechanicDtos = findAllMechanics();
		for (MechanicDto mechanicDto : mechanicDtos) {
			List<EnrollmentDto> enrollmentDtos = findPassedEnrollmentsByMechanicId(mechanicDto.id);
			List<CourseDto> courseDtos = new ArrayList<CourseDto>();
			for (EnrollmentDto enrollmentDto : enrollmentDtos) {
				courseDtos.add(findCourseByID(Long.valueOf(enrollmentDto.courseId)));
			}
			List<DedicationDto> dedicationDtos = new ArrayList<DedicationDto>();
			for (CourseDto courseDto : courseDtos) {
				dedicationDtos.addAll(findVehicleTypesByCourse(courseDto.id));
			}
			
			List<VehicleTypeDto> vehicleTypeDtos = new ArrayList<VehicleTypeDto>();
			
		}
		
		
		
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			

			for (MechanicDto mechanicDto : mechanicDtos) {
				
				
				
				
				
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
	
	public List<MechanicDto> findAllMechanics(){
		try (Connection c = Jdbc.getConnection()){
			MechanicGateway mechanicGateway = Factory.persistance.getMechanicCrudService();
			mechanicGateway.setConnection(c);
			return mechanicGateway.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
	
	public List<EnrollmentDto> findPassedEnrollmentsByMechanicId(Long mechanic_id){
		try (Connection c = Jdbc.getConnection()){
			EnrollmentGateway enrollmentGateway = Factory.persistance.getEnrollmentGateway();
			enrollmentGateway.setConnection(c);
			return enrollmentGateway.findPassedEnrollmentsByMechanicId(mechanic_id);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public CourseDto findCourseByID(Long course_id){
		try (Connection c = Jdbc.getConnection()){
			CourseGateway courseGateway = Factory.persistance.getCourseGateway();
			courseGateway.setConnection(c);
			return courseGateway.findCourseByID(course_id);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
	
	public List<DedicationDto> findVehicleTypesByCourse(Long course_id){
		try (Connection c = Jdbc.getConnection()){
			DedicationGateway dedicationGateway = Factory.persistance.getDedicationGateway();
			dedicationGateway.setConnection(c);
			return dedicationGateway.findDedicationByCourse(course_id);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

}
