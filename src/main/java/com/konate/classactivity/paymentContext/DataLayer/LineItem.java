package com.konate.classactivity.paymentContext.DataLayer;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LineItem {
    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

//    @Embedded
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

//    @Embedded
    @Column(name = "subtotal")
    private BigDecimal subtotal;

    public LineItem(String description, int quantity, BigDecimal unitPrice) {
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("LineItem description must not be blank.");
        if (quantity <= 0)
            throw new IllegalArgumentException("LineItem quantity must be greater than zero.");
        if (unitPrice == null)
            throw new IllegalArgumentException("LineItem unitPrice must not be null.");

        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }


}
