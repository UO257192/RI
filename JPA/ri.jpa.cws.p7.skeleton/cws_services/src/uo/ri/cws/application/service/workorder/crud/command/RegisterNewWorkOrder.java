package uo.ri.cws.application.service.workorder.crud.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.WorkOrder;

import java.util.Optional;

public class RegisterNewWorkOrder implements Command<WorkOrderDto> {

    private WorkOrderDto workOrderDto;
    private VehicleRepository vehicleRepository = Factory.repository.forVehicle();
    private WorkOrderRepository workOrderRepository = Factory.repository.forWorkOrder();

    public RegisterNewWorkOrder(WorkOrderDto workOrderDto) {
        this.workOrderDto = workOrderDto;
    }

    @Override
    public WorkOrderDto execute() throws BusinessException {
        BusinessCheck.isTrue(workOrderDto.description.trim().length() > 0, "Description is blank");
        Optional<Vehicle> ovehicle = vehicleRepository.findById(workOrderDto.vehicleId);
        BusinessCheck.isTrue(ovehicle.isPresent(), "El vehiculo no existe");
        WorkOrder workOrder = new WorkOrder(ovehicle.get(), workOrderDto.description);
        workOrderRepository.add(workOrder);
        workOrderDto.id = workOrder.getId();
        return workOrderDto;
    }
}
