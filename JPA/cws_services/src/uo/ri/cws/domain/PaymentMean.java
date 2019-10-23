package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

public abstract class PaymentMean {
	private double accumulated = 0.0;

	/*
	 * Relacion pays
	 */
	private Client client;

	public Client getClient() {
		return client;
	}

	void _setClient(Client client) {
		this.client = client;
	}
	
	private Set<Charge> charges = new HashSet<Charge>();

	public void pay(double importe) {
		this.accumulated += importe;
	}
	
	
	public void setAcumulated(double accumulated) {
		this.accumulated = accumulated;
	}
	
	public double getAccumulated() {
		return accumulated;
	}

	public void pagar(double importe) {
		this.accumulated += importe;
	}

	Set<Charge> _getCharges() {
		return charges;
	}
	
	public Set<Charge> getCharges() {
		return new HashSet<Charge>(charges);
	}

}
