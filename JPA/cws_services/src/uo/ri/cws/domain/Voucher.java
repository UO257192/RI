package uo.ri.cws.domain;

public class Voucher extends PaymentMean {
	private String code;
	private double available = 0.0;
	private String description;

	public Voucher(String code) {
		super();
		this.code = code;
	}
	
	public Voucher(Client client, String code) {
		this(code);
		Associations.Pay.link(client, this);
	}
	
	public Voucher(String code, double available, String description) {
		this(code);
		this.available = available;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public double getAvailable() {
		return available;
	}

	public String getDescription() {
		return description;
	}
	
	void updateDisponible() {
		this.available = available - this.getAvailable();
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
		Voucher other = (Voucher) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	/**
	 * Augments the accumulated and decrements the available
	 * 
	 * @throws IllegalStateException if not enough available to pay
	 */
	@Override
	public void pay(double amount) {

	}

}
