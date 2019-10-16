package uo.ri.business.serviceLayer.invoice.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.dto.BreakdownDto;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.serviceLayer.invoice.InvoiceService;
import uo.ri.business.transactionScripts.cashier.WorkOrderBilling;
import uo.ri.common.BusinessException;

public class InvoiceServiceImpl implements InvoiceService {

	@Override
	public InvoiceDto createInvoiceFor(List<Long> workOrderIDS) throws BusinessException {
		WorkOrderBilling workOrderBilling = new WorkOrderBilling(workOrderIDS);
		return workOrderBilling.execute();
	}

	@Override
	public InvoiceDto findInvoice(Long numeroFactura) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMeanDto> findPayMethodsForInvoice(Long idFactura) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void settleInvoice(Long idFactura, Map<Long, Double> cargos) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BreakdownDto> findRepairsByClient(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
