package uo.ri.persistance;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.ampliacion.EnrollmentDto;

public interface EnrollmentGateway {
	
	void setConnection(Connection c);
	
	List<EnrollmentDto> findPassedEnrollmentsByMechanicId(Long mechanic_id);

}
