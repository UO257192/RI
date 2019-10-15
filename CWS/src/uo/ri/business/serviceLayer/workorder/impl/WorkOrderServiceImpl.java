package uo.ri.business.serviceLayer.workorder.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.common.BusinessException;

public class WorkOrderServiceImpl implements WorkOrderService {

	@Override
	public WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateWorkOrder(WorkOrderDto dto) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteWorkOrder(Long id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<WorkOrderDto> findWorkOrderById(Long woId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

}
