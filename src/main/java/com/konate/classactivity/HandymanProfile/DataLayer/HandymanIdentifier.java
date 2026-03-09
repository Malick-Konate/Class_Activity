package com.konate.classactivity.HandymanProfile.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import static java.util.UUID.randomUUID;

@Embeddable
@Getter
public class HandymanIdentifier {
    @Column(name = "handyman_id")
    private String handymanId;

    public HandymanIdentifier() {
        this.handymanId = randomUUID().toString();
    }

    public HandymanIdentifier(String handymanId) {
        this.handymanId = handymanId;
    }
}
