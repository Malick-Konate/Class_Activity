package com.konate.classactivity.bookingContext.BusinessLayer;

import com.konate.classactivity.bookingContext.PresentationLayer.JobRequestModel;
import com.konate.classactivity.bookingContext.PresentationLayer.JobResponseModel;

import java.util.List;

public interface JobService {
    List<JobResponseModel> getAllJobs();

    JobResponseModel getJob(String jobId);

    void delete(String jobId);

    JobResponseModel createJob(JobRequestModel requestModel);

    JobResponseModel updateJob(String jobId, JobRequestModel requestModel);
}
