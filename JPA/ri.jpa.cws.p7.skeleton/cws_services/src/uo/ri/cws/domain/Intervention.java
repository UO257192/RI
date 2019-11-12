package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Intervention class. TINTERVENTIONS table.
 * <p>
 * * @author UO257192
 */
public class Intervention extends BaseEntity {
	private Mechanic mechanic;
	private WorkOrder workOrder;

	private Date date;
	private int minutes;

	private Set<Substitution> substitucions = new HashSet<>();

	Intervention() {

	}

	/**
	 * Intervention class constructor
	 * 
	 * @param mechanic  Mechanic
	 * @param workOrder WorkOrder
	 */
	public Intervention(Mechanic mechanic, WorkOrder workOrder) {
		this.date = new Date();
		Associations.Intervene.link(workOrder, this, mechanic);
	}

	/**
	 * Intervention class constructor
	 * 
	 * @param mechanic  Mechanic associated to the intervention
	 * @param workOrder WorkOrder associated to the intervention
	 * @param minutes   Duration (mins)
	 */
	public Intervention(Mechanic mechanic, WorkOrder workOrder, int minutes) {
		this(mechanic, workOrder);
		this.minutes = minutes;
	}

	/**
	 *
	 * @return WorkOrder associated to the intervention
	 */
	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	/**
	 * Internal use Set WorkOrder associated to the intervention
	 * 
	 * @param workOrder WorkOrder associated to the intervention
	 */
	void _setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	/**
	 *
	 * @return Mechanic associated to the intervention
	 */
	public Mechanic getMechanic() {
		return mechanic;
	}

	/**
	 * Internal use Set Mechanic associated to the intervention
	 * 
	 * @param mechanic Mechanic associated to the intervention
	 */
	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	/**
	 *
	 * @return Minutes of the intervention
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 *
	 * @return a copy of the substitutions for the intervention
	 */
	public Set<Substitution> getSustitutions() {
		return new HashSet<>(substitucions);
	}

	/**
	 * Internal use
	 * 
	 * @return the substitutions for the intervention
	 */
	Set<Substitution> _getSubstitutions() {
		return substitucions;
	}

	/**
	 *
	 * @return a copy of the date
	 */
	public Date getDate() {
		return new Date(date.getTime());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Intervention that = (Intervention) o;
		return workOrder.equals(that.workOrder) && mechanic.equals(that.mechanic);
	}

	@Override
	public int hashCode() {
		return Objects.hash(workOrder, mechanic);
	}

	@Override
	public String toString() {
		return "Intervention{" + "workOrder=" + workOrder + ", mechanic=" + mechanic + ", date=" + date + ", minutes="
				+ minutes + '}';
	}

	/**
	 * Sum amount of all substitutions
	 * 
	 * @return total amount
	 */
	public double getAmount() {
		double amount = 0L;
		for (Substitution substitution : substitucions)
			amount += substitution.getImporte();
		amount += workOrder.getVehicle().getVehicleType().getPricePerHour() * ((double) minutes / 60L);
		return amount;
	}
}
