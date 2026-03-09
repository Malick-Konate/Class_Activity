package com.konate.classactivity.bookingContext.BusinessLayer.jobTask;

import com.konate.classactivity.bookingContext.PresentationLayer.jobTask.JobTaskRequestModel;
import com.konate.classactivity.bookingContext.PresentationLayer.jobTask.JobTaskResponseModel;

import java.util.List;

public interface JobTaskService {
    List<JobTaskResponseModel> getAll_by_jobId(String jobId);

    JobTaskResponseModel get_jobTask_by_job(String jobId, String taskId);

    void deleteTask(String jobId, String taskId);

    JobTaskResponseModel create_jobTask(String jobId, JobTaskRequestModel requestModel);

    JobTaskResponseModel update_jobTask(String jobId, String taskId, JobTaskRequestModel requestModel);
}
