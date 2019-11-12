package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * SparePart class. TSPAREPARTS table.
 * <p>
 * * @author UO257192
 */
public class SparePart extends BaseEntity{
	private String code;
	private String description;
	private double price;

	private Set<Substitution> substitutions = new HashSet<>();

	/**
	 *  SparePart class constructor
	 * @param code SparePart code
	 */
	public SparePart(String code) {
		super();
		this.code = code;
	}

	/**
	 * SparePart class constructor
	 * @param code SparePart code
	 * @param description SparePart description
	 * @param price SparePart price
	 */
	public SparePart(String code, String description, double price) {
		this(code);
		this.description = description;
		this.price = price;
	}

	SparePart() {

	}

	/**
	 *
	 * @return SparePart code
	 */
	public String getCode() {
		return code;
	}
	/**
	 *
	 * @return SparePart description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 *
	 * @return SparePart price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Internal use
	 * @return substitutions in which the sparePart is used
	 */
	Set<Substitution> _getSubstitutions() {
		return substitutions;
	}
	/**
	 *
	 * @return copy of substitutions in which the sparePart is used
	 */
	public Set<Substitution> getSustituciones() {
		return new HashSet<Substitution>(substitutions);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		SparePart other = (SparePart) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SparePart [code=" + code + ", description=" + description + ", price=" + price + "]";
	}

}
