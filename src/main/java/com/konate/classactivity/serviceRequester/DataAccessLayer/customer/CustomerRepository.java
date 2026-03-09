package com.konate.classactivity.serviceRequester.DataAccessLayer.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findAllByCustomerIdentifier_CustomerId(String customerId);

    Customer findCustomerByEmail(String email);

    Customer findAllByEmail(String email);
}
