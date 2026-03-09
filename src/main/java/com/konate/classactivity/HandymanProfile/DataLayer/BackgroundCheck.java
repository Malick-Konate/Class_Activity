package com.konate.classactivity.HandymanProfile.DataLayer;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.LocalDateTime;
import java.util.UUID;
@Embeddable
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "background_checks")
public class BackgroundCheck {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

//    @Embedded
//    private BackgroundIdentifier checkId;
//
//    @Embedded
//    private HandymanIdentifier handymanIdentifier; // Link back to Handyman

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BackgroundCheckStatus status;

    @Column(name = "performed_date")
    private LocalDateTime performedDate;

    @Column(name = "expiry_date")
    private LocalDateTime expiry_date;


    public BackgroundCheck(){
        this.status = BackgroundCheckStatus.PENDING;
        this.performedDate = LocalDateTime.now();
        this.expiry_date = LocalDateTime.now().plusYears(1);
    }

    public BackgroundCheck(BackgroundCheckStatus status, LocalDateTime performedDate, LocalDateTime expiry_date){
        this.status = status;
        this.performedDate = performedDate;
        this.expiry_date = expiry_date;
    }
}