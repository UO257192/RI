package uo.ri.persistance.interventions;

import java.sql.Connection;

public interface InterventionGateway {

	/**
	 * Set the database connection
	 * 
	 * @param database connection
	 */
	void setConnection(Connection c);

	/**
	 * Find if a specific workorder has intervetions
	 * 
	 * @param id of the workorder
	 * @return true if it has interventions : false if not
	 */
	boolean hasInterventions(Long workorderID);
}
