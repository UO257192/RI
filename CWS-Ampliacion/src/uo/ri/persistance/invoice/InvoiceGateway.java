package uo.ri.persistance.invoice;

import java.sql.Connection;

import uo.ri.business.dto.InvoiceDto;

public interface InvoiceGateway {

	/**
	 * Set the database connection
	 * 
	 * @param database connection
	 */
	void setConnection(Connection c);

	/**
	 * Add a new invoice to the system with the data specified in the dto. The id
	 * value will be ignored
	 * 
	 * @param invoice dto
	 */
	void add(InvoiceDto invoice);

	/**
	 * Find the last invoice id created
	 * 
	 * @return last invoice id
	 */
	Long generateInvoiceNumber();

	/**
	 * Find the id of a invoice by an specific invoice number
	 * 
	 * @return last invoice id
	 */
	long getGeneratedKey(long invoice_number);
}
