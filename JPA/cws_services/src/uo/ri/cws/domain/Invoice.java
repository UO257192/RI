package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import alb.util.assertion.Argument;
import alb.util.assertion.StateCheck;

public class Invoice {
	public enum InvoiceStatus { NOT_YET_PAID, PAID }

	private Long number;
	
	private Date date;
	private double amount;
	private double vat;
	private InvoiceStatus status = InvoiceStatus.NOT_YET_PAID;
	
	/*
	 * Relacion Toinvoice
	 */
	private Set<WorkOrder> workorders = new HashSet<WorkOrder>();

	public Invoice(Long number) {
		this(number, new Date());
	}

	public Invoice(Long number, Date date) {
		// check arguments (always), through IllegalArgumentException
		Argument.isNotNull(number);
		Argument.isNotNull(date);
		// store the number
		this.number = number;
		// store a copy of the date
		this.date = new Date(date.getTime());
	}

	public Invoice(Long number, List<WorkOrder> workOrders) {
		this(number, new Date());
		for (WorkOrder workOrder : workOrders) {
			this.addWorkOrder(workOrder);
		}
	}

	public Invoice(Long number, Date date, List<WorkOrder> workOrders) {

	}

	/**
	 * Computed amount and vat (vat depends on the date)
	 */
	private void computeAmount() {

	}

	/**
	 * Adds (double links) the workOrder to the invoice and updates the amount and vat 
	 * @param workOrder
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID  
	 */
	public void addWorkOrder(WorkOrder workOrder) {
		StateCheck.isTrue(status.equals(InvoiceStatus.PAID));
		workOrder.markAsInvoiced();
		Associations.ToInvoice.link(this, workOrder);
		computeAmount();
	}

	/**
	 * Removes a work order from the invoice and recomputes amount and vat
	 * @param workOrder
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID  
	 */
	public void removeWorkOrder(WorkOrder workOrder) {

	}

	/**
	 * Marks the invoice as PAID, but
	 * @throws IllegalStateException if
	 * 	- Is already settled 
	 *  - Or the amounts paid with charges to payment means do not cover 
	 *  	the total of the invoice  
	 */
	public void settle() {

	}

	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<WorkOrder>(workorders);
	}
	
	 Set<WorkOrder> _getWorkOrders() {
		return workorders;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Invoice [number=" + number + ", date=" + date + ", amount=" + amount + ", vat=" + vat + ", status="
				+ status + "]";
	}
	
}