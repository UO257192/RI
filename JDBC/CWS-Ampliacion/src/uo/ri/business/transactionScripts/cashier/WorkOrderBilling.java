package uo.ri.business.transactionScripts.cashier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import alb.util.date.Dates;
import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.invoice.InvoiceGateway;
import uo.ri.persistance.workorder.WorkOrderGateway;

public class WorkOrderBilling {
	private List<Long> workOrderIDS;

	public WorkOrderBilling(List<Long> workOrderIDS) {
		this.workOrderIDS = workOrderIDS;
	}

	public InvoiceDto execute() throws BusinessException {
		InvoiceDto invoiceDto = new InvoiceDto();
		testRepairs(workOrderIDS);
		invoiceDto.number = generateInvoiceNumber();
		invoiceDto.date = Dates.today();
		double amount = calculateTotalInvoice(workOrderIDS); // not vat included
		invoiceDto.vat = vatPercentage(amount, invoiceDto.date);
		invoiceDto.total = amount * (1 + invoiceDto.vat / 100); // vat included
		long idInvoice = createInvoice(invoiceDto.number, invoiceDto.date, invoiceDto.vat, invoiceDto.total);
		invoiceDto.status = "NOT_YET_PAID";
		linkWorkorderInvoice(idInvoice, workOrderIDS);
		updateWorkOrderStatus(workOrderIDS, "INVOICED");
		return invoiceDto;
	}

	private void updateWorkOrderStatus(List<Long> breakdownIds, String status){
		try (Connection c = Jdbc.getConnection()) {
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			c.setAutoCommit(false);
			gateway.setConnection(c);
			for (Long breakdownId : breakdownIds) {
				gateway.updateWorkOrderStatus(breakdownId, status);
			}
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	private void linkWorkorderInvoice(long invoiceId, List<Long> workOrderIDS) {
		try (Connection c = Jdbc.getConnection()) {
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			c.setAutoCommit(false);
			gateway.setConnection(c);
			for (Long breakdownId : workOrderIDS) {
				gateway.linkWorkOrderInvoice(invoiceId, breakdownId);
			}
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	private long getGeneratedKey(long numberInvoice) {
		long id = 1L;
		try (Connection c = Jdbc.getConnection()) {
			InvoiceGateway gateway = Factory.persistance.getInvoiceGateway();
			c.setAutoCommit(false);
			gateway.setConnection(c);
			id = gateway.getGeneratedKey(numberInvoice);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
		return id;
	}

	private long createInvoice(long numberInvoice, Date dateInvoice, double vat, double total) {
		try (Connection c = Jdbc.getConnection()) {
			InvoiceGateway gateway = Factory.persistance.getInvoiceGateway();
			c.setAutoCommit(false);
			gateway.setConnection(c);
			InvoiceDto dto = new InvoiceDto();
			dto.date = new java.sql.Date(dateInvoice.getTime());
			dto.number = numberInvoice;
			dto.vat = vat;
			dto.total = total;
			dto.status = "NOT_YET_PAID";
			gateway.add(dto);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}

		return getGeneratedKey(numberInvoice);
	}

	private double vatPercentage(double totalInvoice, Date dateInvoice) {
		return Dates.fromString("1/7/2012").before(dateInvoice) ? 21.0 : 18.0;
	}

	private void testRepairs(List<Long> workOrderIDS) throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			gateway.setConnection(c);
			for (Long workOrderID : workOrderIDS) {
				String status = gateway.checkWorkOrderStatus(workOrderID);
				if (status.equals("")) {
					c.rollback();
					throw new BusinessException("Workorder " + workOrderID + " doesn't exist");
				}
				if ("INVOICED".equalsIgnoreCase(status)) {
					c.rollback();
					throw new BusinessException("Workorder " + workOrderID + " is already invoiced");
				}
				if (!"FINISHED".equalsIgnoreCase(status)) {
					c.rollback();
					throw new BusinessException("Workorder " + workOrderID + " is not finished yet");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	private Long generateInvoiceNumber() {
		Long id;
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			InvoiceGateway gateway = Factory.persistance.getInvoiceGateway();
			gateway.setConnection(c);
			id = gateway.generateInvoiceNumber();
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
		return id;
	}

	protected double calculateTotalInvoice(List<Long> workOrderIDS) throws BusinessException{
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

	private double checkTotalLabor(Long workOrderID) throws BusinessException{
		double totalLabor;
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			gateway.setConnection(c);
			totalLabor = gateway.checkTotalLabor(workOrderID);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
		return totalLabor;
	}

	private double checkTotalParts(Long workOrderID){
		double totalParts;
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			gateway.setConnection(c);
			totalParts = gateway.checkTotalParts(workOrderID);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
		return totalParts;
	}

	private void updateWorkorderTotal(Long workOrderID, double total) {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			gateway.setConnection(c);
			gateway.updateWorkorderTotal(workOrderID, total);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

}