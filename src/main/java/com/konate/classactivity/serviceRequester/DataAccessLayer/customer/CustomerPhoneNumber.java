package com.konate.classactivity.serviceRequester.DataAccessLayer.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class CustomerPhoneNumber {
    @Column(name = "number")
    private String number;

    @Column(name = "type")
    private String type;

    public  CustomerPhoneNumber() {
    }
    public CustomerPhoneNumber(String number, String type) {
        this.number = number;
        this.type = type;
    }
}
