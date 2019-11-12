package uo.ri.cws.domain;

/**
 * Substitution class. TSUBSTITUTIONS table.
 * <p>
 * * @author UO257192
 */
public class Substitution extends BaseEntity {
	private int quantity;
	private Intervention intervention;
	private SparePart sparePart;

	Substitution() {
	};

	/**
	 * Substitution class constructor
	 * 
	 * @param sparePart    Substitution sparepart
	 * @param intervention Substitution intervention
	 */
	public Substitution(SparePart sparePart, Intervention intervention) {
		super();
		Associations.Sustitute.link(intervention, this, sparePart);
	}

	/**
	 * Substitution class constructor
	 * 
	 * @param sparePart    Substitution sparepart
	 * @param intervention Substitution intervention
	 * @param quantity     SparePart quantity
	 */
	public Substitution(SparePart sparePart, Intervention intervention, int quantity) {
		this(sparePart, intervention);
		if (quantity < 1)
			throw new IllegalArgumentException("La cantidad no puede ser inferior a 1");
		this.quantity = quantity;
	}

	/**
	 *
	 * @return Substitution sparepart
	 */
	public SparePart getSparePart() {
		return sparePart;
	}

	/**
	 * Internal use Set the sparepart to the Substitution
	 * 
	 * @param sparePart Substitution sparepart
	 */
	void _setSparePart(SparePart sparePart) {
		this.sparePart = sparePart;
	}

	/**
	 *
	 * @return Substitution intervention
	 */
	public Intervention getIntervention() {
		return intervention;
	}

	/**
	 * Internal use Set the intervention to the Substitution
	 * 
	 * @param intervention
	 */
	void _setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	/**
	 *
	 * @return Substitution quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 *
	 * @return Substitution quantity
	 */
	public double getImporte() {
		return (double) quantity * sparePart.getPrice();
	}
}
