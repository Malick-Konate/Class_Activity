package com.konate.classactivity.paymentContext.DataLayer;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;


@Embeddable
@NoArgsConstructor
@Getter
public class Money {

    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "currency", length = 3)
    private String currency;

    public Money(BigDecimal amount, String currency) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Money amount must be non-negative.");
        if (currency == null || currency.isBlank())
            throw new IllegalArgumentException("Currency must not be blank.");
        this.amount = amount;
        this.currency = currency.toUpperCase();
    }

    public Money add(Money other) {
        assertSameCurrency(other);
        return new Money(this.amount.add(other.amount), this.currency);
    }

    public Money subtract(Money other) {
        assertSameCurrency(other);
        return new Money(this.amount.subtract(other.amount), this.currency);
    }

    public Money multiply(BigDecimal factor) {
        return new Money(this.amount.multiply(factor), this.currency);
    }

    private void assertSameCurrency(Money other) {
        if (!this.currency.equals(other.currency))
            throw new IllegalArgumentException("Currency mismatch: " + this.currency + " vs " + other.currency);
    }

    public BigDecimal getAmount() { return amount; }
    public String getCurrency() { return currency; }

}
