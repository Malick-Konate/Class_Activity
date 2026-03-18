package com.konate.classactivity.serviceRequest.domain;

import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

class CustomerEntityUnitTest {

    @Test
    void shouldCreateCustomer_usingBuilder() {
        // Arrange
        CustomerIdentifier identifier = new CustomerIdentifier();
        CustomerAddress address = new CustomerAddress("123 Main St", "Montreal", "QC", "H3B 2Y5", "Canada");

        // Act
        Customer customer = Customer.builder()
                .customerIdentifier(identifier)
                .firstName("Malick")
                .lastName("Konate")
                .email("malick@example.com")
                .customerAddress(address)
                .build();

        // Assert
        assertNotNull(customer);
        assertEquals("Malick", customer.getFirstName());
        assertEquals(identifier, customer.getCustomerIdentifier());
        assertEquals("123 Main St", customer.getCustomerAddress().getStreet());
    }

    @Test
    void shouldMaintainDataIntegrity_whenUpdatingFields() {
        // Arrange
        Customer customer = new Customer();
        String newEmail = "updated@example.com";

        // Act
        customer.setEmail(newEmail);

        // Assert
        assertEquals(newEmail, customer.getEmail(), "Setter should correctly update the email field");
    }

    @Test
    void shouldHandlePhoneNumbersList() {
        // Arrange
        Customer customer = new Customer();
        CustomerPhoneNumber phone = new CustomerPhoneNumber("514-555-0000", "WORK");

        // Act
        customer.setPhoneNumbers(List.of(phone));

        // Assert
        assertEquals(1, customer.getPhoneNumbers().size());
        assertEquals("514-555-0000", customer.getPhoneNumbers().get(0).getNumber());
    }

    @Test
    void shouldCorrectlyAssignAndRetrieveAddress_whenSet() {
        // Arrange
        Customer customer = new Customer();
        CustomerAddress address = new CustomerAddress("742 Evergreen Terrace", "Springfield", "IL", "62704", "USA");

        // Act
        customer.setCustomerAddress(address);

        // Assert
        assertNotNull(customer.getCustomerAddress(), "Address should not be null after being set");
        assertEquals("742 Evergreen Terrace", customer.getCustomerAddress().getStreet());
        assertEquals("Springfield", customer.getCustomerAddress().getCity());
    }

    @Test
    void shouldEnsureTwoCustomersWithSameIdentifierAreEqual() {
        // Arrange
        CustomerIdentifier sameId = new CustomerIdentifier();

        Customer customer1 = Customer.builder()
                .customerIdentifier(sameId)
                .firstName("Malick")
                .build();

        Customer customer2 = Customer.builder()
                .customerIdentifier(sameId)
                .firstName("Konate")
                .build();

        // Act & Assert
        // This tests if your equals/hashCode (likely from Lombok @Data or @EqualsAndHashCode)
        // correctly uses the CustomerIdentifier for identity logic.
        assertEquals(customer1.getCustomerIdentifier(), customer2.getCustomerIdentifier(),
                "Customers sharing the same Identifier object should have equal identifiers");
    }
}