package uo.ri.cws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Voucher class. TVOUCHERS table.
 * <p>
 * * @author UO257192
 */
public class Voucher extends PaymentMean {
	private String code;
	private double available;
	private String description;

	Voucher() {
	}

	/**
	 * Voucher class constructor
	 * @param code Voucher code
	 */
	public Voucher(String code) {
		super();
		this.code = code;
	}

	/**
	 *  Voucher class constructor
	 * @param client Voucher client
	 * @param code Voucher code
	 */
	public Voucher(Client client, String code) {
		this(code);
		Associations.Pay.link(this,client);
	}

	/**
	 * Voucher class constructor
	 * @param code Voucher code
	 * @param available Voucher available amount
	 * @param description Voucher description
	 */
	public Voucher(String code, double available, String description) {
		this(code);
		this.available = available;
		this.description = description;
	}

	/**
	 * Voucher class constructor
	 * @param code Voucher code
	 * @param available Voucher available amount
	 */
	public Voucher(String code, double available) {
		this(code);
		this.available = available;
	}

	/**
	 *
	 * @return Voucher code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * For test
	 * @return Voucher available
	 */
	public double getDisponible() {
		return available;
	}

	/**
	 *
	 * @return Voucher description
	 */
	public String getDescripcion() {
		return description;
	}

	/**
	 * Set a new description for the Voucher
	 * @param description Voucher description
	 */
	public void setDescripcion(String description) {
		this.description = description;
	}

	/**
	 * Update available amount
	 */
	void updateAvailable() {
		this.available = available - this.getAccumulated();
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

	@Override
	public String toString() {
		return "Voucher [code=" + code + ", available=" + available + ", description=" + description + "]";
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
