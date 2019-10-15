package uo.ri.persistance.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.InvoiceGateway;

public class InvoiceGatewayImpl implements InvoiceGateway {
	private Connection c;

	@Override
	public void add(InvoiceDto invoice) {
		PreparedStatement pst = null;
		String SQL_INSERT_INVOICE = Conf.getInstance().getProperty("SQL_INSERT_INVOICE");
		try {
			pst = c.prepareStatement(SQL_INSERT_INVOICE);
			pst.setLong(1, invoice.number);
			pst.setDate(2, new java.sql.Date(invoice.date.getTime()));
			pst.setDouble(3, invoice.vat);
			pst.setDouble(4, invoice.total);
			pst.setString(5, invoice.status);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}

	}

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public Long generateInvoiceNumber() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_LAST_INVOICE_NUMBER = Conf.getInstance().getProperty("SQL_LAST_INVOICE_NUMBER");
		Long id = 1L;
		try {
			pst = c.prepareStatement(SQL_LAST_INVOICE_NUMBER);
			rs = pst.executeQuery();
			id = rs.next() ? rs.getLong(1) + 1 : 1L;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
		return id;
	}

	@Override
	public long getLastInvoiceID(InvoiceDto invoice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getGeneratedKey(long invoice_number) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_RETRIEVE_GENERATED_KEY = Conf.getInstance().getProperty("SQL_RETRIEVE_GENERATED_KEY");
		Long id = 1L;
		try {
			pst = c.prepareStatement(SQL_RETRIEVE_GENERATED_KEY);
			pst.setLong(1, invoice_number);
			rs = pst.executeQuery();
			rs.next();
			id = rs.getLong(1);

		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
		return id;
	}
	
	


}
