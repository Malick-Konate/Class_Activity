package com.konate.classactivity.bookingContext.PresentationLayer;

import com.konate.classactivity.bookingContext.DataLayer.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequestModel {
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

    String customerEmail;

    String handymanEmail;
}
