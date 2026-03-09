package com.konate.classactivity.serviceRequester.presentationLayer.customer;

import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerAddress;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerPhoneNumber;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseModel {
    String customerId;

    String firstName;

    String lastName;

    String email;

    Date registrationDate;

    CustomerAddress customerAddress;

    List<CustomerPhoneNumber> phoneNumbers;

}
