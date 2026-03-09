package com.konate.classactivity.HandymanProfile.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class BackgroundIdentifier {
    @Column(name = "background_id")
    private String backgroundId;

    public BackgroundIdentifier(String backgroundId){
        this.backgroundId = backgroundId;
    }

    public BackgroundIdentifier(){
        this.backgroundId = UUID.randomUUID().toString();
    }
}
