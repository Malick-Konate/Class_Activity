package com.konate.classactivity.serviceRequester.DataAccessLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import static java.util.UUID.randomUUID;

@Embeddable
@Getter
public class ServiceRequestIdentifier {
    @Column(name = "service_id")
    private String serviceId;

    public ServiceRequestIdentifier(String service_id) {
        this.serviceId = service_id;
    }

    public ServiceRequestIdentifier() {
        this.serviceId = randomUUID().toString();
    }
}
