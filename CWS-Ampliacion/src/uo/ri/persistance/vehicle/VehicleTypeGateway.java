package uo.ri.persistance.vehicle;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.VehicleTypeDto;

public interface VehicleTypeGateway {

	/**
	 * Set the database connection
	 * 
	 * @param database connection
	 */
	void setConnection(Connection c);
	
	/**
	 * @return the list of all vehicle types registered in the system. It might be an
	 *         empty list if there is no vehicle type
	 * 
	 */
	List<VehicleTypeDto> findAll();
	
	VehicleTypeDto findTypeByID(Long type);
	
}
