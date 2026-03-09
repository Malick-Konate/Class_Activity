package com.konate.classactivity.bookingContext.DataLayer;

import com.konate.classactivity.HandymanProfile.DataLayer.HandymanIdentifier;
import com.konate.classactivity.serviceRequester.DataAccessLayer.ServiceRequestIdentifier;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerIdentifier;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "jobs")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private JobIdentifier jobIdentifier;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private JobStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 1. This is the direct field in Job
    @Column(name = "job_scheduled_start_time")
    private LocalDateTime scheduledStartTime;

    @Column(name = "actual_start_time")
    private LocalDateTime actualStartTime;

    @Column(name = "actual_end_time")
    private LocalDateTime actualEndTime;

    @Column(name = "job_estimated_duration") // Force this name
    private Integer estimatedDuration;

    @Column(name = "notes", length = 2000)
    private String notes;

    // Cross-context identifiers (opaque here)
//    @Column(name = "customer_id", columnDefinition = "UUID")
    @Embedded
    private CustomerIdentifier customerIdentifier;

    @Embedded
    private HandymanIdentifier handymanIdentifier;

    @Embedded
    private ServiceRequestIdentifier serviceRequestIdentifier;

    @Embedded
    private ServiceCategory serviceCategory;

    @Embedded
    private Schedule schedule;

    @Embedded
    private Assignment assignment;

    @Embedded
    private JobLocation jobLocation;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "job_material_requirements",
            joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    )
    private List<MaterialRequirement> materialRequirements;



}
