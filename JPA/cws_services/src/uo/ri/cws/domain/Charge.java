package uo.ri.cws.domain;

public class Charge {
	private Invoice invoice;
	private PaymentMean paymentMean;
	private double amount;
	
	public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {
		this.amount = amount;
		// store the amount
		// increment the paymentMean accumulated ( paymentMean.pay( -amount) )
		// link invoice, this and paymentMean
	}

	/**
	 * Unlinks this charge and restores the value to the payment mean
	 * @throws IllegalStateException if the invoice is already settled
	 */
	public void rewind() {
		// assert the invoice is not in PAID status
		// decrement the payment mean accumulated ( paymentMean.pay( -amount) )
		// unlink invoice, this and paymentMean
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public PaymentMean getPaymentMean() {
		return paymentMean;
	}

	public double getAmount() {
		return amount;
	}
	
	
	
}
