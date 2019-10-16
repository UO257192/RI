package uo.ri.persistance.mechanic;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.MechanicDto;

public interface MechanicGateway {
	
	void setConnection(Connection c);
	
	void add(MechanicDto mechanic);
	
	void delete(Long mechanic_id);
	
	void update(MechanicDto mechanic);
	
	List<MechanicDto> findAll();	
	
	MechanicDto findByID(Long idMechanic);
	
	MechanicDto findByDNI(String dni);
}
