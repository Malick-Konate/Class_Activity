package com.konate.classactivity.bookingContext.PresentationLayer;

import com.konate.classactivity.bookingContext.DataLayer.*;
import com.konate.classactivity.serviceRequester.DataAccessLayer.ProblemDescription;
import com.konate.classactivity.serviceRequester.DataAccessLayer.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseModel extends RepresentationModel<JobResponseModel> {
    String jobId;
    JobStatus status;
    LocalDateTime createdAt;
    LocalDateTime scheduledStartTime;
    LocalDateTime actualStartTime;
    LocalDateTime actualEndTime;
    Integer estimatedDuration;
    String notes;
    Assignment assignment;
    JobLocation jobLocation;
    Schedule schedule;
    ServiceCategory serviceCategory;
    List<MaterialRequirement> materialRequirements;


    String handymanName;
    String handymanEmail;
    Boolean isActive;


    String customerName;
    String customerEmail;


    RequestStatus requestStatus;
    ProblemDescription problemDescription;



}
