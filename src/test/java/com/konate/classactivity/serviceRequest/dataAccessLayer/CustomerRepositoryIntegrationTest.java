package com.konate.classactivity.serviceRequest.dataAccessLayer;

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
public class CustomerRepositoryIntegrationTest {

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



    @Test
    void shouldFindCustomer_byEmail() {

        String existingEmail = "george@example.com";

        // Act
        Customer found = customerRepository.findCustomerByEmail(existingEmail);

        // Assert
        assertNotNull(found, "Customer should be found by a valid email");
        assertEquals(existingEmail, found.getEmail());
    }

    @Test
    void shouldReturnEmpty_whenEmailDoesNotExist() {
        // Arrange
        String nonExistentEmail = "ghost@doesnotexist.com";

        // Act
        Customer found = customerRepository.findCustomerByEmail(nonExistentEmail);

        // Assert
        assertNull(found, "Repository should return null when no customer matches the email");
    }

    @Test
    void shouldReturnTrue_whenEmailAlreadyExists() {


        String existingEmail = "george@example.com";

        // Act
        Customer found = customerRepository.findCustomerByEmail(existingEmail);

        // Assert
        assertNotNull(found, "The email should already exist in the database");
    }

    @Test
    void shouldReturnFalse_whenEmailDoesNotExist() {
        // Act
        Customer found = customerRepository.findCustomerByEmail("new-user@test.com");

        // Assert
        assertNull(found, "The email should not exist in the database");
    }

    @Test
    void shouldReturnEmptyList_whenFindAllByEmail_withNonExistentEmail() {
        // Act - Testing the 'findAllByEmail' variant
        List<Customer> customers = (List<Customer>) customerRepository.findAllByEmail("unknown@test.com");

        // Assert
        assertTrue(customers == null || ((List<?>)customers).isEmpty(),
                "Should return an empty result if email is not found");
    }

    @Test
    void shouldFindCustomer_byCustomerId() {
        // Act
        Customer found = customerRepository.findAllByCustomerIdentifier_CustomerId(VALID_CUSTOMER_ID);

        // Assert
        assertNotNull(found);
        assertEquals(VALID_CUSTOMER_ID, found.getCustomerIdentifier().getCustomerId());
    }

    private Customer buildSampleCustomer() {
        return Customer.builder()
                .customerIdentifier(new CustomerIdentifier())
                .firstName("Malick")
                .lastName("Konate")
                .email("malick@example.com")
                .registrationDate(new Date())
                .customerAddress(new CustomerAddress("123 Main St", "Montreal", "QC", "H3B 2Y5", "Canada"))
                .phoneNumbers(List.of(new CustomerPhoneNumber("514-555-9999", "MOBILE")))
                .build();
    }
}