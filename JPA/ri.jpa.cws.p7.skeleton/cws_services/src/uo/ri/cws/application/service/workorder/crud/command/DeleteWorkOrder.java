package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

public class DeleteWorkOrder implements Command<Void> {

    private WorkOrderRepository workOrderRepository = Factory.repository.forWorkOrder();
    public String id;

    public DeleteWorkOrder(String id) {
        this.id = id;
    }

    @Override
    public Void execute() throws BusinessException {
        Optional<WorkOrder> optionalWorkOrder = workOrderRepository.findById(id);
        BusinessCheck.isTrue(optionalWorkOrder.isPresent(), "La workorder no existe");
        WorkOrder workOrder = optionalWorkOrder.get();
        BusinessCheck.isTrue(workOrder.getInterventions().isEmpty(), "La workorder tiene intervenciones");
        workOrderRepository.remove(workOrder);
        return null;
    }
}
