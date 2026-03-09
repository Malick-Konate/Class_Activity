package com.konate.classactivity.bookingContext.PresentationLayer.jobTask;

import com.konate.classactivity.bookingContext.DataLayer.jobTask.JobTaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobTaskRequestModel {
    String taskDescription;

    JobTaskStatus status;

    Integer estimatedTime;

    Integer actualTime;

    Boolean requiresSpecialTools;

}
