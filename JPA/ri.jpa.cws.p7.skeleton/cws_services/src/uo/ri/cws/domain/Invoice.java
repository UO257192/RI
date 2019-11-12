package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import alb.util.assertion.Argument;
import alb.util.date.Dates;
import alb.util.math.Round;

/**
 * Invoice class. TINVOICES table.
 * <p>
 * * @author UO257192
 */
public class Invoice extends BaseEntity {
	public enum InvoiceStatus {
		NOT_YET_PAID, PAID
	}

	private Long number;

	private Date date;
	private double amount;
	private double vat;
	private InvoiceStatus status = InvoiceStatus.NOT_YET_PAID;

	private Set<WorkOrder> workOrders = new HashSet<>();
	private Set<Charge> charges = new HashSet<>();

	Invoice() {
	}

	/**
	 * Invoice class constructor
	 * 
	 * @param number Invoice number
	 */
	public Invoice(Long number) {
		this(number, new Date());
	}

	/**
	 * Invoice class constructor
	 * 
	 * @param number Invoice number
	 * @param date   Invoice date
	 */
	public Invoice(Long number, Date date) {
		Argument.isNotNull(date);
		Argument.isNotNull(number);
		this.number = number;
		this.date = new Date(date.getTime());
	}

	/**
	 * Invoice class constructor
	 * 
	 * @param number     Invoice number
	 * @param workOrders WorkOrders to invoice
	 */
	public Invoice(Long number, List<WorkOrder> workOrders) {
		this(number);
		for (WorkOrder workOrder : workOrders) {
			addWorkOrder(workOrder);
		}
	}

	/**
	 * Invoice class constructor
	 * 
	 * @param number     Invoice number
	 * @param date       Invoice date
	 * @param workOrders WorkOrders to invoice
	 */
	public Invoice(Long number, Date date, List<WorkOrder> workOrders) {
		this(number, workOrders);
		this.date = new Date(date.getTime());
	}

	/**
	 *
	 * @return Invoice number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 *
	 * @return copy of Invoice date
	 */
	public Date getDate() {
		return new Date(date.getTime());
	}

	/**
	 * Set a new date to the invoice
	 * 
	 * @param date Invoice date
	 */
	public void setDate(Date date) {
		this.date = new Date(date.getTime());
	}

	/**
	 * Compute and returns the amount
	 * 
	 * @return total amount
	 */
	public double getAmount() {
		computeAmount();
		return amount;
	}

	/**
	 * Set invoice amount
	 * 
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 *
	 * @return Invoice vat
	 */
	public double getVat() {
		return vat;
	}

	/**
	 *
	 * @param vat Invoice vat
	 */
	public void setVat(double vat) {
		this.vat = vat;
	}

	/**
	 *
	 * @return Invoice status
	 */
	public InvoiceStatus getStatus() {
		return status;
	}

	public void setStatus(InvoiceStatus status) {
		this.status = status;
	}

	/**
	 *
	 * @return a copy of workorders to invoice
	 */
	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<WorkOrder>(workOrders);
	}

	/**
	 * Internal use
	 * 
	 * @return workorders to invoic
	 */
	Set<WorkOrder> _getWorkOrders() {
		return workOrders;
	}

	/**
	 *
	 * @return a copy of charges of the invoice
	 */
	public Set<Charge> getCharges() {
		return new HashSet<>(charges);
	}

	/**
	 * Internal use
	 * 
	 * @return charges of the invoice
	 */
	Set<Charge> _getCharges() {
		return charges;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Invoice invoice = (Invoice) o;
		return number.equals(invoice.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public String toString() {
		return "Invoice{" + "number=" + number + ", date=" + date + ", amount=" + amount + ", vat=" + vat + ", status="
				+ status + '}';
	}

	/**
	 * Computed amount and vat (vat depends on the date)
	 */
	private void computeAmount() {
		vat = Dates.fromString("1/7/2012").before(date) ? 21.0 : 18.0;
		amount = 0L;
		for (WorkOrder workOrder : workOrders)
			amount += workOrder.getAmount();
		amount *= 1L + vat / 100L;
		amount = Round.twoCents(amount);
	}

	/**
	 * Adds (double links) the workOrder to the invoice and updates the amount and
	 * vat
	 *
	 * @param workOrder
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
	 * @see State diagrams on the problem statement document
	 */
	public void addWorkOrder(WorkOrder workOrder) {
		if (!this.status.equals(InvoiceStatus.NOT_YET_PAID))
			throw new IllegalStateException("La factura debe estar en estado SIN_ABONAR");
		if (!workOrder.getStatus().equals(WorkOrder.WorkOrderStatus.FINISHED))
			throw new IllegalStateException("La avería debe estar en estado TERMINADA");
		Associations.ToInvoice.link(workOrder, this);
		workOrder.markAsInvoiced();
		this.computeAmount();
	}

	/**
	 * Removes a work order from the invoice and recomputes amount and vat
	 *
	 * @param workOrder
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
	 * @see State diagrams on the problem statement document
	 */
	public void removeWorkOrder(WorkOrder workOrder) {
		if (!this.status.equals(InvoiceStatus.NOT_YET_PAID))
			throw new IllegalStateException("La factura debe estar en estado SIN_ABONAR");
		Associations.ToInvoice.unlink(workOrder, this);
		workOrder.markBackToFinished();
		this.computeAmount();
	}

	/**
	 * Marks the invoice as PAID, but
	 *
	 * @throws IllegalStateException if - Is already settled - Or the amounts paid
	 *                               with charges to payment means do not cover the
	 *                               total of the invoice
	 */
	public void settle() {
		this.status = InvoiceStatus.PAID;
	}

}
