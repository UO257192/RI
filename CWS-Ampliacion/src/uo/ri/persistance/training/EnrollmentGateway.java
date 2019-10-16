package uo.ri.persistance.training;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.ampliacion.EnrollmentDto;

public interface EnrollmentGateway {
	
	void setConnection(Connection c);
	
	List<EnrollmentDto> findPassedEnrollmentsByMechanicId(Long mechanic_id);
	
	List<Long> findPassedCoursessByMechanicId(Long mechanic_id);
	
	List<Long> findPassedMechanicIDS();
	
	int findAttendanceForCourseAndMechanic(Long courseID, Long mechanicId);
}
