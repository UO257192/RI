package uo.ri.cws.domain;

import alb.util.date.Dates;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * CreditCard class. TCREDITCARDS table.
 * <p>
 * * @author UO257192
 */
public class CreditCard extends PaymentMean {
    private String number;
    private String type;
    private Date validThru;

    CreditCard() {
    }

    ;

    /**
     * CreditCard class constructor
     *
     * @param number CreditCard number
     */
    public CreditCard(String number) {
        super();
        this.number = number;
    }

    /**
     * CreditCard class constructor
     *
     * @param number    CreditCard number
     * @param type      CreditCard type
     * @param validThru CreditCard validthru
     */
    public CreditCard(String number, String type, Date validThru) {
        this(number);
        if (validThru.getTime() < Dates.today().getTime())
            throw new IllegalStateException("La tarjeta ha expirado");
        this.type = type;
        this.validThru = new Date(validThru.getTime());
    }

    /**
     * @return CreditCard number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @return CreditCard type
     */
    public String getType() {
        return type;
    }

    /**
     * @return CreditCard validthru
     */
    public Date getValidThru() {
        return new Date(validThru.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", validThru=" + validThru +
                '}';
    }
}
