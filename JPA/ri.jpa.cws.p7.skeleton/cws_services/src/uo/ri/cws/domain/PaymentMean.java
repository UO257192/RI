package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * PaymentMean class. TPAYMENTMEANS table.
 * <p>
 * * @author UO257192
 */
public abstract class PaymentMean extends BaseEntity{
	private double accumulated = 0.0;

	private Client client;
	private Set<Charge> charges = new HashSet<>();

	/**
	 *
	 * @return Accumulated value
	 */
	public double getAccumulated() {
		return accumulated;
	}

	/**
	 * Set Accumulated value
	 * @param accumulated PaymentMean accumulated
	 */
	public void setAccumulated(double accumulated) {
		this.accumulated = accumulated;
	}

	/**
	 *
	 * @return Client associated to the paymentmean
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Associate Client to the paymentmean
	 * @param client Client associated to the paymentmean
	 */
	void _setClient(Client client) {
		this.client = client;
	}

	/**
	 * Internal use
	 * @return charges
	 */
	Set<Charge> _getCharges() {
		return charges;
	}

	/**
	 *
	 * @return copy of the charges
	 */
	public Set<Charge> getCharges() {
		return new HashSet<Charge>(charges);
	}

	/**
	 * Add import to accumulated amount
	 * @param amount Amount to add
	 */
	public void pay(double amount) {
		this.accumulated += amount;
	}

	@Override
	public String toString() {
		return "PaymentMean [accumulated=" + accumulated + ", client=" + client + "]";
	}
}
