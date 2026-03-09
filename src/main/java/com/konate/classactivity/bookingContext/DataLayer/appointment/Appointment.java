package com.konate.classactivity.bookingContext.DataLayer.appointment;

import com.konate.classactivity.HandymanProfile.DataLayer.HandymanIdentifier;
import com.konate.classactivity.bookingContext.DataLayer.JobIdentifier;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerIdentifier;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private AppointmentIdentifier appointmentIdentifier;


    private JobIdentifier jobIdentifier;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AppointmentStatus status;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @Column(name = "cancellation_reason", length = 1000)
    private String cancellationReason;

    @Column(name = "rescheduled_from")
    private LocalDateTime rescheduledFrom;

//    @Column(name = "customer_id", columnDefinition = "UUID")
//    private UUID customerId;

    @Embedded
    private CustomerIdentifier customerIdentifier;

//    @Column(name = "handyman_id", columnDefinition = "UUID")
//    private UUID handymanId;

    @Embedded
    private HandymanIdentifier handymanIdentifier;
}