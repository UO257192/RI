package uo.ri.cws.application.service.workorder.assign;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.service.workorder.AssignWorkOrderService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;

import java.util.List;

public class AssignWorkOrderServiceImpl implements AssignWorkOrderService {
    @Override
    public void assignWorkOrderToMechanic(String woId, String mechanicId) throws BusinessException {

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
