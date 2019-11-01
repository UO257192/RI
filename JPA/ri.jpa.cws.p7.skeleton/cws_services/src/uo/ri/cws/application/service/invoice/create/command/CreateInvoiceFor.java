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
		invoiceRepository.add(new Invoice(number, workOrderRepository.findByIds(workOrderIds)));
		return DtoAssembler.toDto(invoiceRepository.findByNumber(number).get());
	}
	//1194520b-080c-4e0a-951f-d087328721fd
    //704cd802-e3f4-421e-b4f3-d9b9856e2f2d
    //a68ce67f-c9ef-4a6a-aa46-a1a4ad148f42
    //ca5de9b6-5bc7-42f1-82e6-e99c63d6d4f2


}
