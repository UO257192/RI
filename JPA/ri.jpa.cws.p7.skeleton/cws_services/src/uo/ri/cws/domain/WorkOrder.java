package uo.ri.cws.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * WorkOrder class. TWORKORDERS table.
 * <p>
 * * @author UO257192
 */
public class WorkOrder extends BaseEntity{
	public enum WorkOrderStatus {
		OPEN, ASSIGNED, FINISHED, INVOICED
	}

	private Date date;
	private String description;
	private double amount = 0.0;
	private WorkOrderStatus status = WorkOrderStatus.OPEN;

	private Vehicle vehicle;
	private Mechanic mechanic;
	private Invoice invoice;

	private Set<Intervention> interventions = new HashSet<>();

	/**
	 * WorkOrder class constructor
	 * @param vehicle WorkOrder vehicle
	 */
	public WorkOrder(Vehicle vehicle) {
		super();
		this.date = new Date();
		Associations.Order.link(vehicle, this);
	}

	/**
	 * WorkOrder class constructor
	 * @param date WorkOrder date
	 * @param vehicle WorkOrder vehicle
	 */
	public WorkOrder(Date date, Vehicle vehicle) {
		this.date = date;
		this.vehicle = vehicle;
		Associations.Order.link(vehicle, this);
	}

	/**
	 * WorkOrder class constructor
	 * @param vehicle WorkOrder vehicle
	 * @param description WorkOrder description
	 */
	public WorkOrder(Vehicle vehicle, String description) {
		this(new Date(), vehicle);
		this.description = description;
	}

	WorkOrder() {
	}

	/**
	 * Set new description to the workorder
	 * @param description WorkOrder description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 *
	 * @return a copy of the date
	 */
	public Date getDate() {
		return new Date(date.getTime());
	}

	/**
	 *
	 * @return WorkOrder description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Computes and return amount
	 * @return WorkOrder amount
	 */
	public double getAmount() {
        computeAmount();
		return amount;
	}

	/**
	 *
	 * @return WorkOrder status
	 */
	public WorkOrderStatus getStatus() {
		return status;
	}

	/**
	 *
	 * @return WorkOrder vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * Internal use
	 * Set Vehicle to the workorder
	 * @param vehicle WorkOrder vehicle
	 */
	void _setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 *
	 * @return WorkOrder mechanic assigned
	 */
	public Mechanic getMechanic() {
		return mechanic;
	}

	/**
	 * Internal use
	 * Set mechanic assigned to the WorkOrder
	 * @param mechanic WorkOrder mechanic
	 */
	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	/**
	 *
	 * @return WorkOrder invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * Internal use
	 * Set WorkOrder invoice
	 * @param invoice WorkOrder invoice
	 */
	void _setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * Internal use
	 * @return the WorkOrder interventions
	 */
	Set<Intervention> _getInterventions() {
		return interventions;
	}
	/**
	 *
	 * @return a copy of the WorkOrder interventions
	 */
	public Set<Intervention> getInterventions() {
		return new HashSet<>(interventions);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
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
		WorkOrder other = (WorkOrder) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (vehicle == null) {
			return other.vehicle == null;
		} else return vehicle.equals(other.vehicle);
	}

	@Override
	public String toString() {
		return "WorkOrder [date=" + date.toString() + ", description=" + description + ", amount=" + amount + ", status=" + status
				+ ", vehicle=" + vehicle + ", mechanic=" + mechanic + ", invoice=" + invoice + "]";
	}

	/**
	 * Changes it to INVOICED state given the right conditions This method is called
	 * from Invoice.addWorkOrder(...)
	 * 
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not FINISHED, or - The
	 *                               work order is not linked with the invoice
	 */
	public void markAsInvoiced() {
		if(!this.status.equals(WorkOrderStatus.FINISHED))
			throw new IllegalStateException("La averia no está en estado TERMINADA");
		if(this.invoice == null)
			throw new IllegalStateException("La avería no está enlazada con una factura");
		this.status = WorkOrderStatus.INVOICED;
	}

	/**
	 * Changes it to FINISHED state given the right conditions and computes the
	 * amount
	 * 
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not in ASSIGNED state,
	 *                               or - The work order is not linked with a
	 *                               mechanic
	 */
	public void markAsFinished() {
		if(!this.status.equals(WorkOrderStatus.ASSIGNED))
			throw new IllegalStateException("La avería no está en estado ASIGNADA");
		if(this.mechanic == null)
			throw new IllegalStateException("La avería no está enlazada con un mecánico");
		this.computeAmount();
		Associations.Assign.unlink(mechanic, this);
		this.status = WorkOrderStatus.FINISHED;
	}

	/**
	 * Changes it back to FINISHED state given the right conditions This method is
	 * called from Invoice.removeWorkOrder(...)
	 * 
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not INVOICED, or - The
	 *                               work order is still linked with the invoice
	 */
	public void markBackToFinished() {
		if(!this.status.equals(WorkOrderStatus.INVOICED))
			throw new IllegalStateException("La avería debe estar en estado FACTURADA");
		if(this.invoice != null)
			throw new IllegalStateException("La avería aún está enlazada con la factura");
		this.status = WorkOrderStatus.FINISHED;
	}

	/**
	 * Links (assigns) the work order to a mechanic and then changes its status to
	 * ASSIGNED
	 * 
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not in OPEN status, or -
	 *                               The work order is already linked with another
	 *                               mechanic
	 */
	public void assignTo(Mechanic mechanic) {
		if(!this.status.equals(WorkOrderStatus.OPEN))
			throw new IllegalStateException("Solo se puede saignar una avería ABIERTA");
		Associations.Assign.link(mechanic, this);
		this.status = WorkOrderStatus.ASSIGNED;
	}

	/**
	 * Unlinks (deassigns) the work order and the mechanic and then changes its
	 * status back to OPEN
	 * 
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not in ASSIGNED status
	 */
	public void desassign() {
		if(!this.status.equals(WorkOrderStatus.ASSIGNED))
			throw new IllegalStateException("La avería no está en estado ASIGNADA");
		Associations.Assign.unlink(mechanic, this);
		this.status = WorkOrderStatus.OPEN;
	}

	/**
	 * In order to assign a work order to another mechanic is first have to be moved
	 * back to OPEN state and unlinked from the previous mechanic.
	 * 
	 * @see state diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not in FINISHED status
	 */
	public void reopen() {
		if(!this.status.equals(WorkOrderStatus.FINISHED))
			throw new IllegalStateException("La avería no está en estado TERMINADA");
		this.status = WorkOrderStatus.OPEN;
	}

	/**
	 * Calculates total amount of the interventions
	 */
	private void computeAmount(){
		amount = 0L;
		for(Intervention intervention: interventions)
			amount += intervention.getAmount();
	}
}
