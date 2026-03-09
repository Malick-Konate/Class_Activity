package com.konate.classactivity.bookingContext.BusinessLayer.jobTask;

import com.konate.classactivity.Exceptions.InvalidInputException;
import com.konate.classactivity.Exceptions.NotFoundException;
import com.konate.classactivity.bookingContext.DataLayer.Job;
import com.konate.classactivity.bookingContext.DataLayer.JobIdentifier;
import com.konate.classactivity.bookingContext.DataLayer.JobRepository;
import com.konate.classactivity.bookingContext.DataLayer.jobTask.JobTask;
import com.konate.classactivity.bookingContext.DataLayer.jobTask.JobTaskIdentifier;
import com.konate.classactivity.bookingContext.DataLayer.jobTask.JobTaskRepository;
import com.konate.classactivity.bookingContext.MappingLayer.JobTask.JobTaskRequestMapper;
import com.konate.classactivity.bookingContext.MappingLayer.JobTask.JobTaskResponseMapper;
import com.konate.classactivity.bookingContext.PresentationLayer.jobTask.JobTaskRequestModel;
import com.konate.classactivity.bookingContext.PresentationLayer.jobTask.JobTaskResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class JobTaskServiceImpl implements  JobTaskService {

    private final JobTaskResponseMapper responseMapper;

    private final JobTaskRequestMapper requestMapper;

    private final JobTaskRepository repository;

    private final JobRepository jobRepository;

    public JobTaskServiceImpl(JobTaskResponseMapper responseMapper, JobTaskRequestMapper requestMapper, JobTaskRepository repository, JobRepository jobRepository) {
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
        this.repository = repository;
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobTaskResponseModel> getAll_by_jobId(String jobId) {
        Job job = jobRepository.findAllByJobIdentifier_JobId(jobId);
        if (job == null) {
            throw new NotFoundException("Sorry but the job is not found at this id: " + jobId);
        }
        List<JobTask> jobTask = repository.findAllByJobIdentifier_JobId(jobId);

        return responseMapper.listJobToResponseModelList(jobTask);
    }

    @Override
    public JobTaskResponseModel get_jobTask_by_job(String jobId, String taskId) {
        Job job = jobRepository.findAllByJobIdentifier_JobId(jobId);
        if (job == null) {
            throw new NotFoundException("Sorry but the job is not found at this id: " + jobId);
        }
        JobTask jobTask = repository.findAllByJobTaskIdentifier_JobtaskId(taskId);
        if (jobTask == null)
            throw new NotFoundException("Sorry but the job task  is not found at this id: " + taskId);
        if(!Objects.equals(job.getJobIdentifier().getJobId(), jobTask.getJobIdentifier().getJobId()))
            throw new NotFoundException("job task: " + taskId + " not found in the job: " + jobId);


        return responseMapper.toResponseModel(jobTask);
    }

    @Override
    public void deleteTask(String jobId, String taskId) {
        Job job = jobRepository.findAllByJobIdentifier_JobId(jobId);
        if (job == null) {
            throw new NotFoundException("Sorry but the job is not found at this id: " + jobId);
        }
        JobTask jobTask = repository.findAllByJobTaskIdentifier_JobtaskId(taskId);
        if (jobTask == null)
            throw new NotFoundException("Sorry but the job task  is not found at this id: " + taskId);
        if(!Objects.equals(job.getJobIdentifier().getJobId(), jobTask.getJobIdentifier().getJobId()))
            throw new NotFoundException("job task: " + taskId + " not found in the job: " + jobId);

        repository.delete(jobTask);
    }

    @Override
    public JobTaskResponseModel create_jobTask(String jobId, JobTaskRequestModel requestModel) {
        Job job = jobRepository.findAllByJobIdentifier_JobId(jobId);
        if (job == null) {
            throw new NotFoundException("Sorry but the job is not found at this id: " + jobId);
        }

        if (requestModel == null)
            throw new InvalidInputException("Sorry but the request cannot be null: " + requestModel);

        JobTask newJobTask = requestMapper.toJobTask(
                requestModel,
                new JobTaskIdentifier(),
                new JobIdentifier(jobId)
        );

        JobTask saved = repository.save(newJobTask);
        return responseMapper.toResponseModel(saved);
    }

    @Override
    public JobTaskResponseModel update_jobTask(String jobId, String taskId, JobTaskRequestModel requestModel) {

        Job job = jobRepository.findAllByJobIdentifier_JobId(jobId);
        if (job == null) {
            throw new NotFoundException("Sorry but the job is not found at this id: " + jobId);
        }
        JobTask jobTask = repository.findAllByJobTaskIdentifier_JobtaskId(taskId);
        if (jobTask == null)
            throw new NotFoundException("Sorry but the job task  is not found at this id: " + taskId);
        if(!Objects.equals(job.getJobIdentifier().getJobId(), jobTask.getJobIdentifier().getJobId()))
            throw new NotFoundException("job task: " + taskId + " not found in the job: " + jobId);

        if (requestModel == null)
            throw new InvalidInputException("Sorry but the request cannot be null: " + requestModel);

        jobTask.setTaskDescription(requestModel.getTaskDescription());
        jobTask.setStatus(requestModel.getStatus());
        jobTask.setActualTime(requestModel.getActualTime());
        jobTask.setRequiresSpecialTools(requestModel.getRequiresSpecialTools());
        jobTask.setEstimatedTime(requestModel.getEstimatedTime());

        JobTask saved = repository.save(jobTask);
        return responseMapper.toResponseModel(saved);
    }
}
