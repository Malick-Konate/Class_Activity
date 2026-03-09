package com.konate.classactivity.bookingContext.DataLayer.jobTask;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobTaskRepository extends JpaRepository<JobTask, Integer> {
    List<JobTask> findAllByJobIdentifier_JobId(String jobIdentifierJobId);

//    JobTask findAllByJobTaskIdentifier_Job_taskId(String jobTaskId );

    JobTask findAllByJobTaskIdentifier_JobtaskId(String jobTaskIdentifierJobtaskId);
}
