package uo.ri.business.serviceLayer.vehicle;

import uo.ri.business.dto.VehicleDto;
import uo.ri.common.BusinessException;

/**
 * This service is intended to be used by the Foreman
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface VehicleCrudService {

	/**
	 * @param plate number
	 * @return an Optional with the vehicle dto specified be the plate number
	 * 
	 * @throws BusinessException, DOES NOT 
	 */
	VehicleDto findVehicleByPlate(String plate) throws BusinessException;
	
	/**
	 * @param vehicle id
	 * @return an Optional with the vehicle dto specified be the vehicle id
	 * 
	 * @throws BusinessException, DOES NOT 
	 */
	VehicleDto findVehicleByID(Long id) throws BusinessException;
	
}
