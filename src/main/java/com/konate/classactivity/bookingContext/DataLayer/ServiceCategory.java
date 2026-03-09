package com.konate.classactivity.bookingContext.DataLayer;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ServiceCategory {

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "subcategory")
    private String subcategory;

    @Column(name = "category_description", length = 1000)
    private String description;

//    @Embedded
//    private Money money;
//    @Column(name = "amount", nullable = false)
//    private BigDecimal amount;
//
//    @Column(name = "currency", nullable = false, length = 3)
//    private String currency;
}