package uo.ri.cws.domain;

import uo.ri.cws.domain.Invoice.InvoiceStatus;

public class Charge {
	private Invoice invoice;
	private PaymentMean paymentMean;
	private double amount;
	
	public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {
		this.amount = amount;
		// store the amount
		// increment the paymentMean accumulated ( paymentMean.pay( -amount) )
		// link invoice, this and paymentMean
		
		if(paymentMean instanceof Voucher)
			if(((Voucher) paymentMean).getAvailable() < amount)
				throw new IllegalStateException("El medio de pago indicado no tiene suficiente dinero");
		
		// incrementar el importe en el acumulado del medio de pago
		paymentMean.setAcumulated(paymentMean.getAccumulated() + amount);
		if(paymentMean instanceof Voucher)
			((Voucher) paymentMean).updateDisponible();
		
		// guardar el importe
		this.amount = amount;
		// enlazar (link) factura, este cargo y medioDePago
		Associations.Charges.link(paymentMean,this,invoice);
	}

	/**
	 * Unlinks this charge and restores the value to the payment mean
	 * @throws IllegalStateException if the invoice is already settled
	 */
	public void rewind() {
		// verificar que la factura no esta ABONADA
		if(this.invoice.getStatus().equals(InvoiceStatus.PAID))
			throw new IllegalStateException("La factura no puede estar ABONADA");
		// decrementar acumulado en medio de pago (medioPago.pagar( -importe ))
		this.paymentMean.setAcumulated(this.paymentMean.getAccumulated() - this.amount);
		// desenlazar factura, cargo y medio de pago
		Associations.Charges.unlink(this);
	}
	

	void _setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	void _setPaymentMean(PaymentMean paymentMean) {
		this.paymentMean = paymentMean;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
		result = prime * result + ((paymentMean == null) ? 0 : paymentMean.hashCode());
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
		Charge other = (Charge) obj;
		if (invoice == null) {
			if (other.invoice != null)
				return false;
		} else if (!invoice.equals(other.invoice))
			return false;
		if (paymentMean == null) {
			if (other.paymentMean != null)
				return false;
		} else if (!paymentMean.equals(other.paymentMean))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Charge [invoice=" + invoice + ", paymentMean=" + paymentMean + ", amount=" + amount + "]";
	}
	
	
	
}
