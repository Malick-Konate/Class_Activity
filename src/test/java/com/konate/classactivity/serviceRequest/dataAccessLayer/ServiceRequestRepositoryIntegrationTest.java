package com.konate.classactivity.serviceRequest.dataAccessLayer;


import com.konate.classactivity.serviceRequester.DataAccessLayer.*;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerIdentifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Sql({"/data.sql"}) // Ensure you have service_request data in your data.sql
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ServiceRequestRepositoryIntegrationTest {

    @Autowired
    private ServiceRequesterRepository serviceRequestRepository;

    @Test
    void whenServiceRequestSaved_thenItShouldBeRetrievable() {
        // Arrange
        ServiceRequest request = createSampleRequest();

        // Act
        ServiceRequest saved = serviceRequestRepository.save(request);

        // Assert
        assertNotNull(saved.getId());
        ServiceRequest fetched = serviceRequestRepository.findById(saved.getId()).orElse(null);

        assertNotNull(fetched);
        assertEquals(UrgencyOfRequest.EMERGENCY, fetched.getUrgency());
        assertEquals("CAD", fetched.getPreferences().getMaxBudget().getCurrency());
        assertEquals("General Repair", fetched.getProblemDescription().getTitle());
    }

    @Test
    void shouldFindServiceRequest_byBusinessIdentifier() {
        // Arrange
        ServiceRequest request = createSampleRequest();
        String businessId = request.getServiceRequestIdentifier().getServiceId();
        serviceRequestRepository.save(request);

        // Act
        ServiceRequest found = serviceRequestRepository.findAllByServiceRequestIdentifier_ServiceId(businessId);

        // Assert
        assertNotNull(found);
        assertEquals(businessId, found.getServiceRequestIdentifier().getServiceId());
    }


    @Test
    void shouldFindServiceRequests_byUrgencyLevel() {
        // Arrange
        ServiceRequest lowUrgencyRequest = createSampleRequest();
        lowUrgencyRequest.setUrgency(UrgencyOfRequest.LOW);
        serviceRequestRepository.save(lowUrgencyRequest);

        // Act
        // Assuming your data.sql doesn't already have LOW urgency requests,
        // we check for at least the one we just saved.
        long count = serviceRequestRepository.findAll().stream()
                .filter(r -> r.getUrgency() == UrgencyOfRequest.LOW)
                .count();

        // Assert
        assertTrue(count >= 1, "Should find at least one low urgency service request");
    }

    @Test
    void shouldVerifyNestedEmbeddedPersistence_MoneyInsidePreferences() {
        // Arrange
        ServiceRequest request = createSampleRequest();
        BigDecimal specificBudget = new BigDecimal("999.99");
        request.setPreferences(new Preferences(new Money(specificBudget, "USD"), "FEMALE", "English", true));

        ServiceRequest saved = serviceRequestRepository.save(request);

        // Act
        ServiceRequest fetched = serviceRequestRepository.findById(saved.getId()).orElseThrow();

        // Assert
        // This ensures the nesting (ServiceRequest -> Preferences -> Money) survived the DB trip
        assertNotNull(fetched.getPreferences().getMaxBudget());
        assertEquals(specificBudget, fetched.getPreferences().getMaxBudget().getAmount());
        assertEquals("USD", fetched.getPreferences().getMaxBudget().getCurrency());
    }

    private ServiceRequest createSampleRequest() {
        return new ServiceRequest(
                null,
                new ServiceRequestIdentifier(),
                new CustomerIdentifier(),
                UrgencyOfRequest.EMERGENCY,
                new Date(),
                new Date(),
                RequestStatus.DRAFT,
                new ProblemDescription("General Repair", "Home", "Low", "Fix door hinge"),
                new SearchCriteria("Handyman", 10, new DateRange(new Date(), new Date()), new BigDecimal("4.0")),
                new Preferences(new Money(new BigDecimal("100.00"), "CAD"), "ANY", "French", false)
        );
    }
}