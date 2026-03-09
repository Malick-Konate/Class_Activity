package com.konate.classactivity.paymentContext.DataLayer;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class PlatformFee {

    @Column(name = "fee_percentage")
    private BigDecimal feePercentage;

    @Column(name = "fee_type")
    private String feeType;
//    @Embedded
    @Column(name = "fee_amount")
    private BigDecimal feeAmount;

    public PlatformFee(BigDecimal feePercentage, BigDecimal feeAmount, String feeType) {
        if (feePercentage == null || feePercentage.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Fee percentage must be non-negative.");
        if (feeAmount == null)
            throw new IllegalArgumentException("Fee amount must not be null.");
        if (feeType == null || feeType.isBlank())
            throw new IllegalArgumentException("Fee type must not be blank.");

        this.feePercentage = feePercentage;
        this.feeAmount = feeAmount;
        this.feeType = feeType;
    }


}