package com.konate.classactivity.bookingContext.DataLayer.appointment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Getter
@Embeddable
public class AppointmentIdentifier {
    @Column(name = "apppointment_id")
    private String appointmentId;

    public AppointmentIdentifier(String appointmentId){
        this.appointmentId = appointmentId;
    }

    public AppointmentIdentifier(){
        this.appointmentId = UUID.randomUUID().toString();
    }
}
