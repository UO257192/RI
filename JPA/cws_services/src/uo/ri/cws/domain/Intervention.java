package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import alb.util.assertion.Argument;

///link con substitution sparepart
public class Intervention {

	// link de ambos aqui
	private WorkOrder workOrder;
	private Mechanic mechanic;

	private Date date;
	private int minutes;
	
	private Set<Substitution> substitutions = new HashSet<Substitution>();

	public Intervention(Mechanic mechanic, WorkOrder workOrder, int i) {
		Argument.isNotNull(mechanic);
		Argument.isNotNull(workOrder);
		Argument.isTrue(i > 0);
		Associations.Intervene.link(workOrder, this, mechanic);
	}

	Set<Substitution> _getSustitutions() {
		return substitutions;
	}
	
	public Set<Substitution> getSustitutions() {
		return new HashSet<Substitution>(substitutions);
	}
	void _setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public Date getDate() {
		return date;
	}

	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((mechanic == null) ? 0 : mechanic.hashCode());
		result = prime * result + ((workOrder == null) ? 0 : workOrder.hashCode());
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
		Intervention other = (Intervention) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (mechanic == null) {
			if (other.mechanic != null)
				return false;
		} else if (!mechanic.equals(other.mechanic))
			return false;
		if (workOrder == null) {
			if (other.workOrder != null)
				return false;
		} else if (!workOrder.equals(other.workOrder))
			return false;
		return true;
	}
	
	public double getAmount() {
		double amount = 0L;
		for(Substitution sustitucion : substitutions)
			amount += sustitucion.getAmount();
		amount += ((double)workOrder.getVehicle().getVehicleType().getPricePerHour())* ((double)minutes/60L);
		return amount;
	}

}
