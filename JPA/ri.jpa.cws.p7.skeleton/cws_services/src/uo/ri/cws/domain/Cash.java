package uo.ri.cws.domain;

import java.util.Objects;

/**
 * Cash class. TCHASES table.
 *
 * * @author UO257192
 */
public class Cash extends PaymentMean {

	Cash() {
	};

	/**
	 * Cash class constructor
	 * 
	 * @param cliente Client who pays
	 */
	public Cash(Client cliente) {
		Associations.Pay.link(this, cliente);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PaymentMean that = (PaymentMean) o;
		return getClient().equals(that.getClient());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getClient());
	}

	@Override
	public String toString() {
		return "Cash{ " + super.toString() + " }";
	}
}
