package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

@Entity
@Table(name = "tCharges", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "invoice_id", "paymentMean_id"
        })
})
public class Charge extends BaseEntity{
    @ManyToOne
    private Invoice invoice;
    @ManyToOne
    private PaymentMean paymentMean;
    private double amount = 0.0;

	public Charge() {};

    public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {
		if(paymentMean instanceof Voucher)
			if(((Voucher) paymentMean).getDisponible() < amount)
				throw new IllegalStateException("El medio de pago indicado no tiene suficiente dinero");
		paymentMean.setAccumulated(paymentMean.getAccumulated() + amount);
		if(paymentMean instanceof Voucher)
			((Voucher) paymentMean).updateAvailable();
		this.amount = amount;
		Associations.Charges.link(paymentMean,this,invoice);
    }

    /**
     * Unlinks this charge and restores the value to the payment mean
     *
     * @throws IllegalStateException if the invoice is already settled
     */
    public void rewind() {
		if(this.invoice.getStatus().equals(Invoice.InvoiceStatus.PAID))
			throw new IllegalStateException("La factura no puede estar ABONADA");
		this.paymentMean.setAccumulated(this.paymentMean.getAccumulated() - this.amount);
		Associations.Charges.unlink(this);
    }

	public Invoice getInvoice() {
		return invoice;
	}

	void _setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public PaymentMean getPaymentMean() {
		return paymentMean;
	}

	void _setPaymentMean(PaymentMean paymentMean) {
		this.paymentMean = paymentMean;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Charge charge = (Charge) o;
		return invoice.equals(charge.invoice) &&
				paymentMean.equals(charge.paymentMean);
	}

	@Override
	public int hashCode() {
		return Objects.hash(invoice, paymentMean);
	}

	@Override
	public String toString() {
		return "Charge{" +
				"invoice=" + invoice +
				", paymentMean=" + paymentMean +
				", amount=" + amount +
				'}';
	}
}
