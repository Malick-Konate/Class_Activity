package com.konate.classactivity.serviceRequester.DataAccessLayer.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import static java.util.UUID.randomUUID;

@Embeddable
@Getter
public class CustomerIdentifier {
    @Column(name = "customer_id")
    private String customerId;

    public CustomerIdentifier(String customerId){
        this.customerId = customerId;
    }

    public CustomerIdentifier(){
        this.customerId = randomUUID().toString();
    }
}
