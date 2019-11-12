package uo.ri.cws.domain;

import java.util.Objects;

/**
 * Charge class. TCHARGES table.
 * <p>
 * * @author UO257192
 */
public class Charge extends BaseEntity {
    private Invoice invoice;
    private PaymentMean paymentMean;
    private double amount = 0.0;

    Charge() {
    }

    /**
     * Charge class constructor
     *
     * @param invoice     Invoice associated to the Charge
     * @param paymentMean PaymentMean associated to the Charge
     * @param amount      Charge amount
     */
    public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {
        if (paymentMean instanceof Voucher)
            if (((Voucher) paymentMean).getDisponible() < amount)
                throw new IllegalStateException("El medio de pago indicado no tiene suficiente dinero");
        paymentMean.setAccumulated(paymentMean.getAccumulated() + amount);
        if (paymentMean instanceof Voucher)
            ((Voucher) paymentMean).updateAvailable();
        this.amount = amount;
        Associations.Charges.link(paymentMean, this, invoice);
    }

    /**
     * Unlinks this charge and restores the value to the payment mean
     *
     * @throws IllegalStateException if the invoice is already settled
     */
    public void rewind() {
        if (this.invoice.getStatus().equals(Invoice.InvoiceStatus.PAID))
            throw new IllegalStateException("La factura no puede estar ABONADA");
        this.paymentMean.setAccumulated(this.paymentMean.getAccumulated() - this.amount);
        Associations.Charges.unlink(this);
    }

    /**
     * @return Invoice associated to the Charge
     */
    public Invoice getInvoice() {
        return invoice;
    }

    /**
     * Internal use.
     * Set Invoice associated to the Charge.
     *
     * @param invoice new Invoice associated to the Charge
     */
    void _setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    /**
     * @return PaymentMean associated to the Charge
     */
    public PaymentMean getPaymentMean() {
        return paymentMean;
    }

    /**
     * Interal use.
     * Set PaymentMean associated to the Charge.
     *
     * @param paymentMean new PaymentMean associated to the Charge
     */
    void _setPaymentMean(PaymentMean paymentMean) {
        this.paymentMean = paymentMean;
    }

    /**
     * @return Charge amount
     */
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
