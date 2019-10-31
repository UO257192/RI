package uo.ri.cws.application.service.invoice.create.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import alb.util.date.Dates;
import alb.util.jdbc.Jdbc;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoiceDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.WorkOrder;

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
