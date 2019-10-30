package uo.ri.business.serviceLayer.vehicle.impl;

import uo.ri.business.dto.VehicleDto;
import uo.ri.business.serviceLayer.vehicle.VehicleCrudService;
import uo.ri.business.transactionScripts.administrator.vehicle.FindVehicleByID;
import uo.ri.business.transactionScripts.administrator.vehicle.FindVehicleByPlate;
import uo.ri.common.BusinessException;

public class VehicleCrudServiceImpl implements VehicleCrudService {

	@Override
	public VehicleDto findVehicleByPlate(String plate) throws BusinessException {
		FindVehicleByPlate fvbp = new FindVehicleByPlate(plate);
		return fvbp.execute();
	}

	@Override
	public VehicleDto findVehicleByID(Long id) throws BusinessException {
		FindVehicleByID vechicle = new FindVehicleByID(id);
		return vechicle.execute();
	}

}
