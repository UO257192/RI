package uo.ri.cws.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tPaymentMeans")
public abstract class PaymentMean extends BaseEntity{
	private double accumulated = 0.0;

	@ManyToOne
	private Client client;
	@OneToMany(mappedBy = "paymentMean")
	private Set<Charge> charges = new HashSet<>();

	public double getAccumulated() {
		return accumulated;
	}

	public void setAccumulated(double accumulated) {
		this.accumulated = accumulated;
	}

	public Client getClient() {
		return client;
	}

	void _setClient(Client client) {
		this.client = client;
	}

	Set<Charge> _getCharges() {
		return charges;
	}

	public Set<Charge> getCharges() {
		return new HashSet<Charge>(charges);
	}

	public void pay(double importe) {
		this.accumulated += importe;
	}

	@Override
	public String toString() {
		return "PaymentMean [accumulated=" + accumulated + ", client=" + client + "]";
	}
}
