package com.konate.classactivity.serviceRequester.DataAccessLayer.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customer")
@Builder
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private CustomerIdentifier customerIdentifier;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Embedded
    private CustomerAddress customerAddress;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "customer_phone_number",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    )
    private List<CustomerPhoneNumber> phoneNumbers;
}
