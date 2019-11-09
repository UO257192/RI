package uo.ri.cws.application.service.workorder.crud;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.service.workorder.crud.command.RegisterNew;
import uo.ri.cws.application.util.command.CommandExecutor;

import java.util.List;
import java.util.Optional;

public class WorkOrderCrudServiceImpl implements WorkOrderCrudService {

    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException {
        return executor.execute(new RegisterNew(dto));
    }

    @Override
    public void updateWorkOrder(WorkOrderDto dto) throws BusinessException {

    }

    @Override
    public void deleteWorkOrder(String id) throws BusinessException {

    }

    @Override
    public Optional<WorkOrderDto> findWorkOrderById(String woId) throws BusinessException {
        return Optional.empty();
    }

    @Override
    public List<WorkOrderDto> findWorkOrdersByVehicleId(String id) throws BusinessException {
        return null;
    }

    @Override
    public List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
        return null;
    }
}
