package uo.ri.business.impl.cashier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import alb.util.date.Dates;
import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.common.BusinessException;

public class WorkOrderBilling {
	private static final String SQL_CHECK_WORKORDER_STATUS = "select status from TWorkOrders where id = ?";
	private static final String SQL_PARTS_TOTAL = "select sum(s.quantity * r.price) "
			+ "	from  TSubstitutions s, TSpareParts r " + "	where s.sparepart_id = r.id " + "		and s.labor_id = ?";

	private static final String SQL_LABOR_TOTAL = "select sum(i.minutes * tv.pricePerHour / 60) "
			+ "	from TWorkOrders a, TLabors i, TVehicles v, TVehicleTypes tv" + "	where i.workorder_id = a.id "
			+ "		and a.vehicle_id = v.id" + "		and v.vehicletype_id = tv.id" + "		and a.id = ?"
			+ "		and a.status = ''";

	private static final String SQL_UPDATE_WORKORDER_AMOUNT = "update TWorkOrders set amount = ? where id = ?";

	private static final String SQL_LAST_INVOICE_NUMBER = "select max(invoice_number) from TInvoices";

	private static final String SQL_INSERT_INVOICE = "insert into TInvoices(invoice_number, invoice_date, vat, amount, status) "
			+ "	values(?, ?, ?, ?, ?)";

	private static final String SQL_WORKORDER_INVOICE_ASSOC = "update TWorkOrders set invoice_id = ? where id = ?";

	private static final String SQL_UPDATE_WORKORDER_STATUS = "update TWorkOrders set status = ? where id = ?";

	private static final String SQL_RETRIEVE_GENERATED_KEY = "select id from TInvoices where invoice_number = ?";

	private Connection connection;
	
	private List<Long> workOrderIDS;
	
	public WorkOrderBilling(List<Long> workOrderIDS) {
		this.workOrderIDS = workOrderIDS;
	}
	
	public InvoiceDto execute() throws BusinessException {
		InvoiceDto invoiceDto = new InvoiceDto();
		try {
			connection = Jdbc.getConnection();
			connection.setAutoCommit(false);
			testRepairs(workOrderIDS);

			invoiceDto.number = generateInvoiceNumber();
			invoiceDto.date = Dates.today();
			double amount = calculateTotalInvoice(workOrderIDS); // not vat included
			invoiceDto.vat = vatPercentage(amount, invoiceDto.date);
			invoiceDto.total = amount * (1 + invoiceDto.vat / 100); // vat included
			

			long idInvoice = createInvoice(invoiceDto.number, invoiceDto.date, invoiceDto.vat, invoiceDto.total);
			invoiceDto.status =  "NOT_YET_PAID";
			linkWorkorderInvoice(idInvoice, workOrderIDS);
			updateWorkOrderStatus(workOrderIDS, "INVOICED");
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			;
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			;
			throw e;
		} finally {
			Jdbc.close(connection);
		}
		return invoiceDto;
	}
	

	private void updateWorkOrderStatus(List<Long> breakdownIds, String status) throws SQLException {

		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(SQL_UPDATE_WORKORDER_STATUS);

			for (Long breakdownId : breakdownIds) {
				pst.setString(1, status);
				pst.setLong(2, breakdownId);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
	}

	private void linkWorkorderInvoice(long invoiceId, List<Long> workOrderIDS) throws SQLException {

		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(SQL_WORKORDER_INVOICE_ASSOC);

			for (Long breakdownId : workOrderIDS) {
				pst.setLong(1, invoiceId);
				pst.setLong(2, breakdownId);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
	}

	private long getGeneratedKey(long numberInvoice) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Long id = 1L;
		try {
			pst = connection.prepareStatement(SQL_RETRIEVE_GENERATED_KEY);
			pst.setLong(1, numberInvoice);
			rs = pst.executeQuery();
			rs.next();
			id = rs.getLong(1);

		} finally {
			Jdbc.close(rs, pst);
		}
		return id;
	}

	private long createInvoice(long numberInvoice, Date dateInvoice, double vat, double total) throws SQLException {

		PreparedStatement pst = null;
		Long id = 1L;
		try {
			pst = connection.prepareStatement(SQL_INSERT_INVOICE);
			pst.setLong(1, numberInvoice);
			pst.setDate(2, new java.sql.Date(dateInvoice.getTime()));
			pst.setDouble(3, vat);
			pst.setDouble(4, total);
			pst.setString(5, "NOT_YET_PAID");
			pst.executeUpdate();
			id = getGeneratedKey(numberInvoice); // New invoice id

		} finally {
			Jdbc.close(pst);
		}
		return id;
	}

	private double vatPercentage(double totalInvoice, Date dateInvoice) {
		return Dates.fromString("1/7/2012").before(dateInvoice) ? 21.0 : 18.0;
	}

	private void testRepairs(List<Long> workOrderIDS) throws BusinessException, SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = connection.prepareStatement(SQL_CHECK_WORKORDER_STATUS);

			for (Long workOrderID : workOrderIDS) {
				pst.setLong(1, workOrderID);

				rs = pst.executeQuery();
				if (rs.next() == false) {
					throw new BusinessException("Workorder " + workOrderID + " doesn't exist");
				}

				String status = rs.getString(1);
				if (!"FINISHED".equalsIgnoreCase(status)) {
					throw new BusinessException("Workorder " + workOrderID + " is not finished yet");
				}

			}
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	private Long generateInvoiceNumber() throws SQLException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Long id = 1L;
		try {
			pst = connection.prepareStatement(SQL_LAST_INVOICE_NUMBER);
			rs = pst.executeQuery();
			id = rs.next() ? rs.getLong(1) + 1 : 1L;
		} finally {
			Jdbc.close(rs, pst);
		}
		return id;
	}

	protected double calculateTotalInvoice(List<Long> workOrderIDS) throws BusinessException, SQLException {
		double totalInvoice = 0.0;
		for (Long workOrderID : workOrderIDS) {
			double laborTotal = checkTotalLabor(workOrderID);
			double sparesTotal = checkTotalParts(workOrderID);
			double workTotal = laborTotal + sparesTotal;

			updateWorkorderTotal(workOrderID, workTotal);

			totalInvoice += workTotal;
		}
		return totalInvoice;
	}

	private double checkTotalLabor(Long workOrderID) throws BusinessException, SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_LABOR_TOTAL);
			pst.setLong(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("Workorder does not exist or it can not be charged");
			}

			return rs.getDouble(1);

		} finally {
			Jdbc.close(rs, pst);
		}

	}

	private double checkTotalParts(Long workOrderID) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_PARTS_TOTAL);
			pst.setLong(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // There is no part replaced in this breakdown
			}

			return rs.getDouble(1);

		} finally {
			Jdbc.close(rs, pst);
		}
	}

	private void updateWorkorderTotal(Long workOrderID, double total) throws SQLException {
		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement(SQL_UPDATE_WORKORDER_AMOUNT);
			pst.setDouble(1, total);
			pst.setLong(2, workOrderID);
			pst.executeUpdate();
		} finally {
			Jdbc.close(pst);
		}
	}

}