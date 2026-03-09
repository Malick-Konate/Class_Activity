package com.konate.classactivity.paymentContext.DataLayer;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InvoiceTotal {

//    @Embedded
    @Column(name = "subtotal")
    private BigDecimal subtotal;

//    @Embedded
    @Column(name = "tax_total")
    private BigDecimal taxTotal;

//    @Embedded
    @Column(name = "discount_total")
    private BigDecimal discountTotal;

//    @Embedded
    @Column(name = "grand_total")
    private BigDecimal grandTotal;

    public InvoiceTotal(BigDecimal subtotal, BigDecimal taxTotal, BigDecimal discountTotal) {
        if (subtotal == null || taxTotal == null || discountTotal == null)
            throw new IllegalArgumentException("InvoiceTotal components must not be null.");

        this.subtotal = subtotal;
        this.taxTotal = taxTotal;
        this.discountTotal = discountTotal;
        // Invariant: grandTotal = subtotal + taxTotal - discountTotal
        this.grandTotal = subtotal.add(taxTotal).subtract(discountTotal);
    }


}