package uo.ri.cws.domain;

import java.util.Date;

import alb.util.date.Dates;

public class CreditCard extends PaymentMean {

	private String number;
	private String type;
	private Date validThru;

	public CreditCard(String number) {
		super();
		this.number = number;
	}

	public CreditCard(String number, String type, Date validThru) {
		this(number);
		if (validThru.getTime() < Dates.today().getTime())
			throw new IllegalStateException("La tarjeta ha expirado");
		this.type = type;
		this.validThru = new Date(validThru.getTime());
	}

	public String getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}

	public Date getValidThru() {
		return validThru;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		CreditCard other = (CreditCard) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreditCard [number=" + number + ", type=" + type + ", validThru=" + validThru + "]";
	}

}
