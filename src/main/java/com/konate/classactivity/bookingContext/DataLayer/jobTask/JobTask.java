package com.konate.classactivity.bookingContext.DataLayer.jobTask;

import com.konate.classactivity.bookingContext.DataLayer.JobIdentifier;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_tasks")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JobTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private JobTaskIdentifier jobTaskIdentifier;


    @Embedded
    private JobIdentifier jobIdentifier;

    @Column(name = "task_description", nullable = false)
    private String taskDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private JobTaskStatus status;

    @Column(name = "estimated_time_minutes")
    private Integer estimatedTime;

    @Column(name = "actual_time_minutes")
    private Integer actualTime;

    @Column(name = "requires_special_tools")
    private Boolean requiresSpecialTools;
}