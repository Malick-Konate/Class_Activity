package com.konate.classactivity.HandymanProfile.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class AvailableIdentifier {
    @Column(name = "calendar_id")
    private String calendarId;

    public AvailableIdentifier(){
        this.calendarId = UUID.randomUUID().toString();
    }

    public AvailableIdentifier(String calendarId){
        this.calendarId = calendarId;
    }
}
