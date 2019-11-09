package uo.ri.cws.application.service.workorder.assign;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.service.workorder.AssignWorkOrderService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.service.workorder.assign.command.AssignWorkOrderMechanic;
import uo.ri.cws.application.util.command.CommandExecutor;

import java.util.List;

public class AssignWorkOrderServiceImpl implements AssignWorkOrderService {

    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public void assignWorkOrderToMechanic(String woId, String mechanicId) throws BusinessException {
        executor.execute(new AssignWorkOrderMechanic(woId, mechanicId));
    }

    @Override
    public List<CertificateDto> findCertificatesByVehicleTypeId(String id) throws BusinessException {
        return null;
    }

    @Override
    public List<WorkOrderDto> findUnfinishedWorkOrders() throws BusinessException {
        return null;
    }
}
