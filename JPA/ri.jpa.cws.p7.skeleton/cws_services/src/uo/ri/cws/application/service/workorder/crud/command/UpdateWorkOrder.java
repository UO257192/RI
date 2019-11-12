package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import alb.util.assertion.StateCheck;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

public class UpdateWorkOrder implements Command<Void> {

    private WorkOrderRepository workOrderRepository = Factory.repository.forWorkOrder();
    private WorkOrderDto workOrderDto;

    public UpdateWorkOrder(WorkOrderDto workOrderDto) {
        this.workOrderDto = workOrderDto;
    }

    @Override
    public Void execute() throws BusinessException {
        BusinessCheck.isTrue(workOrderDto != null, "La workorder es null");
        Optional<WorkOrder> optionalWorkOrder = workOrderRepository.findById(workOrderDto.id);
        BusinessCheck.isTrue(optionalWorkOrder.isPresent(), "La workorder no existe");
        BusinessCheck.isNotNull(workOrderDto.description, "La descripcion es null");
        BusinessCheck.isNotEmpty(workOrderDto.description, "La descripcion está vacia");
        WorkOrder workOrder = optionalWorkOrder.get();
        StateCheck.isTrue(workOrder.getStatus().equals(WorkOrder.WorkOrderStatus.ASSIGNED) || workOrder.getStatus().equals(WorkOrder.WorkOrderStatus.OPEN));
        BusinessCheck.isTrue(workOrderDto.version.equals(workOrder.getVersion()));
        workOrder.setDescription(workOrderDto.description);
        return null;
    }
}
