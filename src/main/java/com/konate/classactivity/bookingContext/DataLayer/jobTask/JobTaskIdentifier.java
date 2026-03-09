package com.konate.classactivity.bookingContext.DataLayer.jobTask;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class JobTaskIdentifier {
    @Column(name = "job_task_id")
    private String jobtaskId;

    public JobTaskIdentifier(){
        this.jobtaskId = UUID.randomUUID().toString();
    }

    public JobTaskIdentifier(String job_taskId){
        this.jobtaskId = job_taskId;
    }
}
