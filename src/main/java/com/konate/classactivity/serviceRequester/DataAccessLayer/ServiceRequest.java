package com.konate.classactivity.serviceRequester.DataAccessLayer;

import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerIdentifier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_request")
//@Builder
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private ServiceRequestIdentifier serviceRequestIdentifier;

    @Embedded
    private CustomerIdentifier customerId;

    @Column(name = "urgency")
    @Enumerated(EnumType.STRING)
    private UrgencyOfRequest urgency;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "preferred_date")
    private Date preferredDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Embedded
    private ProblemDescription problemDescription;

    @Embedded
    private SearchCriteria searchCriteria;

    @Embedded
    private Preferences preferences;

}
