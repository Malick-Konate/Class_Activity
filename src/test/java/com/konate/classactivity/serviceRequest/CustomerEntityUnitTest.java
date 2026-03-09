package com.konate.classactivity.serviceRequest;

import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Sql({"/data.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerEntityUnitTest {

    @Autowired
    private CustomerRepository customerRepository;

    // Derived from your data.sql file
    private final String VALID_CUSTOMER_ID = "CUSTOMER_100";

    @Test
    void whenCustomersExist_thenReturnAllCustomers() {
        // Act
        List<Customer> customers = customerRepository.findAll();

        // Assert
        assertNotNull(customers, "The customer list should not be null");
        assertFalse(customers.isEmpty(), "The customer list should not be empty");
        // data.sql inserts exactly 10 customers
        assertEquals(10, customers.size(), "There should be exactly 10 customers loaded");
    }

    @Test
    void whenValidCustomer_thenSaveCustomer() {
        // Arrange
        Customer newCustomer = buildSampleCustomer();

        // Act
        Customer savedCustomer = customerRepository.save(newCustomer);

        // Assert
        assertNotNull(savedCustomer, "The saved customer should not be null");
        assertNotNull(savedCustomer.getId(), "The database should generate a primary key ID");
        assertEquals("Malick", savedCustomer.getFirstName());
        assertEquals("malick@example.com", savedCustomer.getEmail());
        assertNotNull(savedCustomer.getCustomerIdentifier().getCustomerId(), "CustomerIdentifier should be generated");

        // Verifying embedded and collection components
        assertEquals("123 Main St", savedCustomer.getCustomerAddress().getStreet());
        assertEquals(1, savedCustomer.getPhoneNumbers().size());
    }

    @Test
    void whenCustomerDeleted_thenCustomerShouldNotExist() {
        // Arrange - fetch an existing customer from the DB
        Customer existingCustomer = customerRepository.findAll().stream()
                .filter(c -> c.getCustomerIdentifier().getCustomerId().equals(VALID_CUSTOMER_ID))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Customer not found"));

        // Act
        customerRepository.delete(existingCustomer);

        // Assert
        boolean exists = customerRepository.findAll().stream()
                .anyMatch(c -> c.getCustomerIdentifier().getCustomerId().equals(VALID_CUSTOMER_ID));
        assertFalse(exists, "The customer should no longer exist in the database after deletion");
    }

    private Customer buildSampleCustomer() {
        return Customer.builder()
                .customerIdentifier(new CustomerIdentifier()) // Auto-generates UUID
                .firstName("Malick")
                .lastName("Konate")
                .email("malick@example.com")
                .registrationDate(new Date())
                .customerAddress(new CustomerAddress("123 Main St", "Montreal", "QC", "H3B 2Y5", "Canada"))
                .phoneNumbers(List.of(new CustomerPhoneNumber("514-555-9999", "MOBILE")))
                .build();
    }
}