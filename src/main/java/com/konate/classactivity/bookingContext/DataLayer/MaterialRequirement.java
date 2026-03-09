package com.konate.classactivity.bookingContext.DataLayer;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MaterialRequirement {

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "quantity")
    private Integer quantity;

//    @Embedded
//    private Money estimatedCost;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "provided_by")
    private String providedBy;
}