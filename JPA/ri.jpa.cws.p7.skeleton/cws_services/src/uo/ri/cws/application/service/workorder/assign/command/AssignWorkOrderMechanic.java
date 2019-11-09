package uo.ri.cws.application.service.workorder.assign.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.WorkOrder;

public class AssignWorkOrderMechanic implements Command<Void> {
    private WorkOrderRepository workOrderRepository = Factory.repository.forWorkOrder();
    private MechanicRepository mechanicRepository = Factory.repository.forMechanic();
    private String woId;
    private String mechanicId;

    public AssignWorkOrderMechanic(String woId, String mechanicId) {
        this.woId = woId;
        this.mechanicId = mechanicId;
    }

    @Override
    public Void execute() throws BusinessException {
        BusinessCheck.isNotNull(woId);
        BusinessCheck.isNotNull(mechanicId);
        BusinessCheck.isNotEmpty(mechanicId, "Id de mecanico vacio");
        BusinessCheck.isNotEmpty(woId, "Id de workorder vacio");
        Optional<WorkOrder> ow = workOrderRepository.findById(woId);
        Optional<Mechanic> om = mechanicRepository.findById(mechanicId);
        BusinessCheck.isTrue(ow.isPresent(), "El workorder no existe");
        BusinessCheck.isTrue(om.isPresent(), "El mecanico no existe");
        WorkOrder workOrder = ow.get();
        Mechanic mechanic = om.get();
        workOrder.assignTo(mechanic);
        return null;
    }
}
