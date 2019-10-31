package uo.ri.persistance.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.conf.Conf;
import uo.ri.persistance.WorkOrderGateway;

public class WorkOrderGatewayImpl implements WorkOrderGateway {
	private Connection c;

	@Override
	public String checkWorkOrderStatus(Long workOrderID) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_CHECK_WORKORDER_STATUS = Conf.getInstance().getProperty("SQL_CHECK_WORKORDER_STATUS");
		try {
			pst = c.prepareStatement(SQL_CHECK_WORKORDER_STATUS);
			pst.setLong(1, workOrderID);
			rs = pst.executeQuery();
			if (rs.next() == false) {
				return "";
			}
			return rs.getString(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public void updateWorkorderTotal(Long workOrderID, double total) {
		PreparedStatement pst = null;
		String SQL_UPDATE_WORKORDER_AMOUNT = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER_AMOUNT");
		try {
			pst = c.prepareStatement(SQL_UPDATE_WORKORDER_AMOUNT);
			pst.setDouble(1, total);
			pst.setLong(2, workOrderID);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void linkWorkOrderInvoice(long invoiceId, Long workOrderID) {
		String SQL_WORKORDER_INVOICE_ASSOC = Conf.getInstance().getProperty("SQL_WORKORDER_INVOICE_ASSOC");
		PreparedStatement pst = null;
		try {
			pst = c.prepareStatement(SQL_WORKORDER_INVOICE_ASSOC);
				pst.setLong(1, invoiceId);
				pst.setLong(2, workOrderID);
				pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void updateWorkOrderStatus(Long breakdownId, String status) {
		PreparedStatement pst = null;
		String SQL_UPDATE_WORKORDER_STATUS = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER_STATUS");
		try {
			pst = c.prepareStatement(SQL_UPDATE_WORKORDER_STATUS);
			pst.setString(1, status);
			pst.setLong(2, breakdownId);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public double checkTotalLabor(Long workOrderID) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_LABOR_TOTAL = Conf.getInstance().getProperty("SQL_LABOR_TOTAL");
		try {
			pst = c.prepareStatement(SQL_LABOR_TOTAL);
			pst.setLong(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return -1;
			}

			return rs.getDouble(1);

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public double checkTotalParts(Long workOrderID) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_PARTS_TOTAL = Conf.getInstance().getProperty("SQL_PARTS_TOTAL");
		try {
			pst = c.prepareStatement(SQL_PARTS_TOTAL);
			pst.setLong(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // There is no part replaced in this breakdown
			}

			return rs.getDouble(1);

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
