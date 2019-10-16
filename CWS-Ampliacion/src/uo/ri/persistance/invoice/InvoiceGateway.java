package uo.ri.persistance.invoice;

import java.sql.Connection;

import uo.ri.business.dto.InvoiceDto;

public interface InvoiceGateway {
	void setConnection(Connection c);
	
	void add(InvoiceDto invoice);
	
	Long generateInvoiceNumber();
	
	long getGeneratedKey(long invoice_number);
}
