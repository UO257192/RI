package uo.ri.cws.application.service.invoice.create.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoiceDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Invoice;

public class CreateInvoiceFor implements Command<InvoiceDto> {

	private List<String> workOrderIds;
	private WorkOrderRepository workOrderRepository = Factory.repository.forWorkOrder();
	private InvoiceRepository invoiceRepository = Factory.repository.forInvoice();

	public CreateInvoiceFor(List<String> workOrderIds) {
		this.workOrderIds = workOrderIds;
	}

	@Override
	public InvoiceDto execute() throws BusinessException {
		Long number = invoiceRepository.getNextInvoiceNumber();
		Invoice invoice = new Invoice(number, workOrderRepository.findByIds(workOrderIds));
		invoiceRepository.add(invoice);
		return DtoAssembler.toDto(invoice);
	}
	//1194520b-080c-4e0a-951f-d087328721fd

}
