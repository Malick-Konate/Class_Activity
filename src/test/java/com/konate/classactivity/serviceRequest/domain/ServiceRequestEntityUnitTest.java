package com.konate.classactivity.serviceRequest.domain;


import com.konate.classactivity.serviceRequester.DataAccessLayer.*;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerIdentifier;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ServiceRequestEntityUnitTest {

    @Test
    void shouldCreateServiceRequest_withAllEmbeddedFields() {
        // Arrange
        ServiceRequestIdentifier serviceId = new ServiceRequestIdentifier();
        CustomerIdentifier customerId = new CustomerIdentifier();

        ProblemDescription problem = new ProblemDescription(
                "Leaking Pipe", "Plumbing", "Medium", "Kitchen sink is leaking");

        DateRange dates = new DateRange(new Date(), new Date());
        SearchCriteria criteria = new SearchCriteria("Plumber", 20, dates, new BigDecimal("4.5"));

        Money budget = new Money(new BigDecimal("150.00"), "CAD");
        Preferences prefs = new Preferences(budget, "NONE", "English", true);

        // Act
        ServiceRequest request = new ServiceRequest();
        request.setServiceRequestIdentifier(serviceId);
        request.setCustomerId(customerId);
        request.setUrgency(UrgencyOfRequest.URGENT);
        request.setStatus(RequestStatus.SUBMITTED);
        request.setProblemDescription(problem);
        request.setSearchCriteria(criteria);
        request.setPreferences(prefs);

        // Assert
        assertNotNull(request.getServiceRequestIdentifier().getServiceId());
        assertEquals("Plumbing", request.getProblemDescription().getCategory());
        assertEquals(new BigDecimal("150.00"), request.getPreferences().getMaxBudget().getAmount());
        assertEquals(UrgencyOfRequest.URGENT, request.getUrgency());
        assertEquals(RequestStatus.SUBMITTED, request.getStatus());
    }

    @Test
    void shouldTestValueObjectEquality_ServiceRequestIdentifier() {
        // Since ServiceRequestIdentifier generates a UUID in the default constructor
        ServiceRequestIdentifier id1 = new ServiceRequestIdentifier();
        ServiceRequestIdentifier id2 = new ServiceRequestIdentifier();

        assertNotEquals(id1.getServiceId(), id2.getServiceId(), "Each default constructor should generate a unique UUID");

        ServiceRequestIdentifier id3 = new ServiceRequestIdentifier("FIXED_ID");
        assertEquals("FIXED_ID", id3.getServiceId());
    }

    @Test
    void shouldVerifyMoneyValueObject_isCorrectlyAssigned() {
        // Arrange
        BigDecimal amount = new BigDecimal("250.50");
        String currency = "CAD";
        Money budget = new Money(amount, currency);

        // Act
        Preferences prefs = new Preferences(budget, "MALE", "French", true);

        // Assert
        assertNotNull(prefs.getMaxBudget());
        assertEquals(amount, prefs.getMaxBudget().getAmount());
        assertEquals("CAD", prefs.getMaxBudget().getCurrency());
    }

    @Test
    void shouldEnsureDateRange_handlesStartAndEndDates() {
        // Arrange
        Date start = new Date();
        Date end = new Date(start.getTime() + 86400000); // +1 day

        // Act
        DateRange range = new DateRange(start, end);
        SearchCriteria criteria = new SearchCriteria("Electrician", 50, range, new BigDecimal("4.8"));

        // Assert
        assertEquals(start, criteria.getAvailabilityWindow().getStartDate());
        assertEquals(end, criteria.getAvailabilityWindow().getEndDate());
        assertEquals(50, criteria.getMaxDistance());
    }
}