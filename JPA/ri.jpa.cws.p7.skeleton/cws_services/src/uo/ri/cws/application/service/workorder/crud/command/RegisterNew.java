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

public class RegisterNew implements Command<WorkOrderDto> {

    private WorkOrderDto workOrderDto;
    private VehicleRepository vehicleRepository = Factory.repository.forVehicle();
    private WorkOrderRepository workOrderRepository = Factory.repository.forWorkOrder();

    public RegisterNew(WorkOrderDto workOrderDto) {
        this.workOrderDto = workOrderDto;
    }

    @Override
    public WorkOrderDto execute() throws BusinessException {
        BusinessCheck.isTrue(workOrderDto.description.trim().length() > 0, "Description is blank");
        Vehicle vehicle = vehicleRepository.findById(workOrderDto.vehicleId).get();
        WorkOrder workOrder = new WorkOrder(vehicle);
        workOrderRepository.add(workOrder);
        return DtoAssembler.toDto(workOrderRepository.findById(workOrder.getId()).get());
    }
}
