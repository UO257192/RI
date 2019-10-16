package uo.ri.business.serviceLayer.workorder.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.business.dto.ampliacion.CertificateDto;
import uo.ri.business.dto.ampliacion.WorkOrderDto;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.business.transactionScripts.foreman.AddWorkOrder;
import uo.ri.business.transactionScripts.foreman.AssignMechanicToWorkorder;
import uo.ri.business.transactionScripts.foreman.FindWorkOrderByID;
import uo.ri.business.transactionScripts.foreman.RemoveWorkOrder;
import uo.ri.business.transactionScripts.foreman.UpdateWorkOrderDescription;
import uo.ri.common.BusinessException;

public class WorkOrderServiceImpl implements WorkOrderService {

	@Override
	public WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException {
		AddWorkOrder add = new AddWorkOrder(dto);
		return add.execute();
	}

	@Override
	public void updateWorkOrder(WorkOrderDto dto) throws BusinessException {
		UpdateWorkOrderDescription u = new UpdateWorkOrderDescription(dto);
		u.execute();
	}

	@Override
	public void deleteWorkOrder(Long id) throws BusinessException {
		RemoveWorkOrder rw = new RemoveWorkOrder(id);
		rw.execute();
	}

	@Override
	public Optional<WorkOrderDto> findWorkOrderById(Long woId) throws BusinessException {
		FindWorkOrderByID fwbi = new FindWorkOrderByID(woId);
		return Optional.of(fwbi.execute());
	}

	@Override
	public List<WorkOrderDto> findUnfinishedWorkOrders() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByVehicleId(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CertificateDto> findCertificatesByVehicleTypeId(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignWorkOrderToMechanic(Long woId, Long mechanicId) throws BusinessException {
		AssignMechanicToWorkorder as = new AssignMechanicToWorkorder(woId, mechanicId);
		as.execute();
	}

}
