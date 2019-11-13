package uo.ri.cws.application.repository;

import java.util.Optional;

import uo.ri.cws.domain.Vehicle;

public interface VehicleRepository extends Repository<Vehicle> {

	/**
	 *
	 * @param plate Vehicle plate
	 * @return Vehicle
	 */
	Optional<Vehicle> findByPlate(String plate);

}
