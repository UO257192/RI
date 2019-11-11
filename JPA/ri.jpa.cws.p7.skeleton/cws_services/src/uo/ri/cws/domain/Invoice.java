package uo.ri.cws.domain;

import alb.util.assertion.Argument;
import alb.util.date.Dates;
import alb.util.math.Round;

import javax.persistence.*;
import java.util.*;

public class Invoice extends BaseEntity{
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

    public Invoice(Long number) {
        this(number, new Date());
    }

    public Invoice(Long number, Date date) {
        Argument.isNotNull(date);
        Argument.isNotNull(number);
        this.number = number;
        this.date = new Date(date.getTime());
    }

    public Invoice(Long number, List<WorkOrder> workOrders) {
        this(number);
        for (WorkOrder workOrder : workOrders) {
            addWorkOrder(workOrder);
        }
    }

    public Invoice(Long number, Date date, List<WorkOrder> workOrders) {
        this(number, workOrders);
        this.date = new Date(date.getTime());
    }

    public Long getNumber() {
        return number;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    public double getAmount() {
        computeAmount();
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public Set<WorkOrder> getWorkOrders() {
        return new HashSet<WorkOrder>(workOrders);
    }

    Set<WorkOrder> _getWorkOrders() {
        return workOrders;
    }

    public Set<Charge> getCharges() {
        return new HashSet<>(charges);
    }

    Set<Charge> _getCharges() {
        return charges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return number.equals(invoice.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "number=" + number +
                ", date=" + date +
                ", amount=" + amount +
                ", vat=" + vat +
                ", status=" + status +
                '}';
    }

    /**
     * Computed amount and vat (vat depends on the date)
     */
    private void computeAmount() {
		vat = Dates.fromString("1/7/2012").before(date) ? 21.0 : 18.0;
		amount = 0L;
		for(WorkOrder workOrder : workOrders)
			amount += workOrder.getAmount();
		amount *= 1L+vat/100L;
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
		if(!this.status.equals(InvoiceStatus.NOT_YET_PAID))
			throw new IllegalStateException("La factura debe estar en estado SIN_ABONAR");
		if(!workOrder.getStatus().equals(WorkOrder.WorkOrderStatus.FINISHED))
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
		if(!this.status.equals(InvoiceStatus.NOT_YET_PAID))
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
