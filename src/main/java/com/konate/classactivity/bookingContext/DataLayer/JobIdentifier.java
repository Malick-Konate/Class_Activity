package com.konate.classactivity.bookingContext.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class JobIdentifier {
    @Column(name = "job_id")
    private String jobId;

    public JobIdentifier(String jobId){
        this.jobId = jobId;
    }

    public JobIdentifier(){
        this.jobId = UUID.randomUUID().toString();
    }
}
