package uo.ri.cws.domain;

public class Cash extends PaymentMean {

	public Cash(Client cliente) {
		Associations.Pay.link(cliente, this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getClient() == null) ? 0 : getClient().hashCode());
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
		PaymentMean other = (PaymentMean) obj;
		if (getClient() == null) {
			if (other.getClient() != null)
				return false;
		} else if (!getClient().equals(other.getClient()))
			return false;
		return true;
	}
}
