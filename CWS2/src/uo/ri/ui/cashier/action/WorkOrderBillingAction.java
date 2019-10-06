package uo.ri.ui.cashier.action;

import java.util.ArrayList;
import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.impl.InvoiceServiceImpl;
import uo.ri.common.BusinessException;
import uo.ri.ui.util.Printer;

public class WorkOrderBillingAction implements Action {
	@Override
	public void execute() throws BusinessException {
		List<Long> workOrderIds = new ArrayList<Long>();

		// type work order ids to be invoiced in the invoice
		do {
			Long id = Console.readLong("Type work order ids ? ");
			workOrderIds.add(id);
		} while (nextWorkorder());
		InvoiceServiceImpl invoiceServiceImpl = new InvoiceServiceImpl();
		Printer.printInvoice(invoiceServiceImpl.createInvoiceFor(workOrderIds));
		//displayInvoice(invoiceServiceImpl.createInvoiceFor(workOrderIds));

	}

	private void displayInvoice(InvoiceDto invoiceDto) {

		Console.printf("Invoice number: %d\n", invoiceDto.number);
		Console.printf("\tDate: %1$td/%1$tm/%1$tY\n", invoiceDto.date);
		Console.printf("\tAmount: %.2f €\n", invoiceDto.total / (1 + invoiceDto.vat / 100));
		Console.printf("\tVAT: %.1f %% \n", invoiceDto.vat);
		Console.printf("\tTotal (including VAT): %.2f €\n", invoiceDto.total);
	}

	private boolean nextWorkorder() {
		return Console.readString(" Any other workorder? (y/n) ").equalsIgnoreCase("y");
	}

}
