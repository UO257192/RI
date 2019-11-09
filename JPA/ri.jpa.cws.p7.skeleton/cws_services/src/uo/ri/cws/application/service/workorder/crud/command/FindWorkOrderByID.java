package uo.ri.cws.application.service.workorder.crud.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.WorkOrder;

import java.util.Optional;

public class FindWorkOrderByID implements Command<Optional<WorkOrderDto>> {

    private String id;
    private WorkOrderRepository workOrderRepository = Factory.repository.forWorkOrder();

    public FindWorkOrderByID(String id) {
        this.id = id;
    }

    @Override
    public Optional<WorkOrderDto> execute() throws BusinessException {
        Optional<WorkOrder> om = workOrderRepository.findById(id);
        WorkOrder m = om.isPresent() ? om.get() : null;
        return m == null ? Optional.empty() : Optional.of(DtoAssembler.toDto(m));
    }
}
