package uo.ri.cws.domain;

public class Substitution {
	private SparePart sparePart;
	private Intervention intervention;
	private int quantity;
	
	public Substitution(SparePart sparePart, Intervention intervention) {
		super();
		this.sparePart = sparePart;
		this.intervention = intervention;
	}
	public SparePart getSparePart() {
		return sparePart;
	}
	public Intervention getIntervention() {
		return intervention;
	}
	public int getQuantity() {
		return quantity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((intervention == null) ? 0 : intervention.hashCode());
		result = prime * result + ((sparePart == null) ? 0 : sparePart.hashCode());
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
		Substitution other = (Substitution) obj;
		if (intervention == null) {
			if (other.intervention != null)
				return false;
		} else if (!intervention.equals(other.intervention))
			return false;
		if (sparePart == null) {
			if (other.sparePart != null)
				return false;
		} else if (!sparePart.equals(other.sparePart))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Substitution [sparePart=" + sparePart + ", intervention=" + intervention + ", quantity=" + quantity
				+ "]";
	}
	
	

	
	
}
