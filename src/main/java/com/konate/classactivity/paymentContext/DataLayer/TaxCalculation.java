package com.konate.classactivity.paymentContext.DataLayer;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TaxCalculation {

    @Column(name = "tax_rate")
    private BigDecimal taxRate;

    @Column(name = "tax_type")
    private String taxType;

    @Column(name = "jurisdiction")
    private String jurisdiction;

//    @Embedded
    @Column(name = "tax_amount")
    private BigDecimal taxAmount;

    public TaxCalculation(BigDecimal taxRate, BigDecimal taxAmount, String taxType, String jurisdiction) {
        if (taxRate == null || taxRate.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Tax rate must be non-negative.");
        if (taxAmount == null)
            throw new IllegalArgumentException("Tax amount must not be null.");
        if (taxType == null || taxType.isBlank())
            throw new IllegalArgumentException("Tax type must not be blank.");
        if (jurisdiction == null || jurisdiction.isBlank())
            throw new IllegalArgumentException("Jurisdiction must not be blank.");

        this.taxRate = taxRate;
        this.taxAmount = taxAmount;
        this.taxType = taxType;
        this.jurisdiction = jurisdiction;
    }


}