package com.konate.classactivity.serviceRequester.DataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequesterRepository extends JpaRepository<ServiceRequest, Integer> {
    ServiceRequest findAllByServiceRequestIdentifier_ServiceId(String serviceId);
//    List<ServiceRequest> findAllByCustomerId_CustomerId(String customerId);

    ServiceRequest findAllByCustomerId_CustomerId(String customerIdCustomerId);
}
