package uo.ri.persistance.interventions;

import java.sql.Connection;

public interface InterventionGateway {

	void setConnection(Connection c);
	
	boolean hasInterventions(Long workorderID);
}
