package uo.ri.cws.application.service.vehicletype;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.VehicleType;

import java.util.List;

public class FindAllVehicleTypes implements Command<List<VehicleTypeDto> >{

    private VehicleTypeRepository vehicleTypeRepository = Factory.repository.forVehicleType();

    @Override
    public List<VehicleTypeDto> execute() throws BusinessException {
        return DtoAssembler.toVehicleTypeDtoList(vehicleTypeRepository.findAll());
    }
}
