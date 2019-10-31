package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tSubstitutions", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "SPAREPART_ID", "INTERVENTION_ID" }) })
public class Substitution extends BaseEntity{
	@ManyToOne
	private SparePart sparePart;
	@ManyToOne
	private Intervention intervention;
	private int quantity;

	Substitution() {};
	
	public Substitution(SparePart sparePart, Intervention intervention) {
		super();
		Associations.Sustitute.link(intervention, this, sparePart);
	}

	public Substitution(SparePart sparePart, Intervention intervention, int quantity) {
		this(sparePart,intervention);
		if(quantity < 1)
			throw new IllegalArgumentException("La cantidad no puede ser inferior a 1");
		this.quantity = quantity;
	}

	public SparePart getSparePart() {
		return sparePart;
	}

	void _setSparePart(SparePart sparePart) {
		this.sparePart = sparePart;
	}

	public Intervention getIntervention() {
		return intervention;
	}

	void _setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getImporte() {
		return (double) quantity * sparePart.getPrice();
	}
}