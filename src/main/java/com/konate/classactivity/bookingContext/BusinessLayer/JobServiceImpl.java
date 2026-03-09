package com.konate.classactivity.bookingContext.BusinessLayer;

import com.konate.classactivity.Exceptions.InvalidInputException;
import com.konate.classactivity.Exceptions.NotFoundException;
import com.konate.classactivity.HandymanProfile.Business.HandymanService;
import com.konate.classactivity.HandymanProfile.DataLayer.HandymanIdentifier;
import com.konate.classactivity.HandymanProfile.PresentationLayer.HandymanProfileResponseModel;
import com.konate.classactivity.bookingContext.DataLayer.Job;
import com.konate.classactivity.bookingContext.DataLayer.JobIdentifier;
import com.konate.classactivity.bookingContext.DataLayer.JobRepository;
import com.konate.classactivity.bookingContext.MappingLayer.JobRequestMapper;
import com.konate.classactivity.bookingContext.MappingLayer.JobResponseMapper;
import com.konate.classactivity.bookingContext.PresentationLayer.JobController;
import com.konate.classactivity.bookingContext.PresentationLayer.JobRequestModel;
import com.konate.classactivity.bookingContext.PresentationLayer.JobResponseModel;
import com.konate.classactivity.serviceRequester.DataAccessLayer.ServiceRequestIdentifier;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerIdentifier;
import com.konate.classactivity.serviceRequester.businessLayer.ServService;
import com.konate.classactivity.serviceRequester.businessLayer.customer.CustomerService;
import com.konate.classactivity.serviceRequester.presentationLayer.customer.CustomerResponseModel;
import com.konate.classactivity.serviceRequester.presentationLayer.service.ServiceRequesterResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class JobServiceImpl implements JobService{

    private final JobRequestMapper requestMapper;

    private final JobResponseMapper responseMapper;

    private final JobRepository repository;

    private final CustomerService customerService;

    private final HandymanService handymanService;

    private final ServService serviceRequest;

    public JobServiceImpl(JobRequestMapper requestMapper, JobResponseMapper responseMapper, JobRepository repository, CustomerService customerService, HandymanService handymanService, ServService serviceRequest) {
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.repository = repository;
        this.customerService = customerService;
        this.handymanService = handymanService;
        this.serviceRequest = serviceRequest;
    }

    @Override
    public List<JobResponseModel> getAllJobs() {
        List<Job> jobs = repository.findAll();
        List<JobResponseModel> responseModel = new ArrayList<>();
        for (Job job: jobs){
            CustomerResponseModel customer = customerService.getOneCustomer(job.getCustomerIdentifier().getCustomerId());
            HandymanProfileResponseModel handyman = handymanService.getHandymanProfile(job.getHandymanIdentifier().getHandymanId());
            ServiceRequesterResponseModel serviceRequester = serviceRequest.getServiceRequest(job.getServiceRequestIdentifier().getServiceId());

            JobResponseModel jobResponseModel = responseMapper.toResponseModel(job);
            jobResponseModel.setCustomerEmail(customer.getEmail());
            jobResponseModel.setCustomerName(customer.getFirstName() + " " + customer.getLastName());

            jobResponseModel.setHandymanName(handyman.getFirstName() + " " + handyman.getLastName());
            jobResponseModel.setHandymanEmail(handyman.getEmail());
            jobResponseModel.setIsActive(handyman.getIsActive());

            jobResponseModel.setRequestStatus(serviceRequester.getStatus());
            jobResponseModel.setProblemDescription(serviceRequester.getProblemDescription());


            jobResponseModel.add(linkTo(methodOn(JobController.class)
                    .getJob(job.getJobIdentifier().getJobId())).withSelfRel());

            responseModel.add(jobResponseModel);
        }


        return responseModel;
    }

    @Override
    public JobResponseModel getJob(String jobId) {
        Job job = repository.findAllByJobIdentifier_JobId(jobId);
        if (job == null){
            throw new NotFoundException("Sorry but the job is not found at the given id: " + jobId);
        }
        CustomerResponseModel customer = customerService.getOneCustomer(job.getCustomerIdentifier().getCustomerId());
        HandymanProfileResponseModel handyman = handymanService.getHandymanProfile(job.getHandymanIdentifier().getHandymanId());
        ServiceRequesterResponseModel serviceRequester = serviceRequest.getServiceRequest(job.getServiceRequestIdentifier().getServiceId());

        JobResponseModel jobResponseModel = responseMapper.toResponseModel(job);
        jobResponseModel.setCustomerEmail(customer.getEmail());
        jobResponseModel.setCustomerName(customer.getFirstName() + " " + customer.getLastName());

        jobResponseModel.setHandymanName(handyman.getFirstName() + " " + handyman.getLastName());
        jobResponseModel.setHandymanEmail(handyman.getEmail());
        jobResponseModel.setIsActive(handyman.getIsActive());

        jobResponseModel.setRequestStatus(serviceRequester.getStatus());
        jobResponseModel.setProblemDescription(serviceRequester.getProblemDescription());

        jobResponseModel.add(linkTo(methodOn(JobController.class)
                .getJob(job.getJobIdentifier().getJobId())).withSelfRel());



        return jobResponseModel;
    }

    @Override
    public void delete(String jobId) {
        Job job = repository.findAllByJobIdentifier_JobId(jobId);
        if (job == null){
            throw new NotFoundException("Sorry but the job is not found at the given id: " + jobId);
        }

        repository.delete(job);
    }

    @Override
    public JobResponseModel createJob(JobRequestModel requestModel) {
        if (requestModel == null){
            throw new InvalidInputException("Sorry but the request cannot be null: " + requestModel);
        }
        CustomerResponseModel customer = customerService.getByEmail(requestModel.getCustomerEmail());
        HandymanProfileResponseModel handyman = handymanService.getEmail(requestModel.getHandymanEmail());
        ServiceRequesterResponseModel serviceRequester = serviceRequest.getByCustomerId(customer.getCustomerId());


        Job newJob = requestMapper.toJob(
                requestModel,
                new JobIdentifier(),
                new CustomerIdentifier(customer.getCustomerId()),
                new HandymanIdentifier(handyman.getHandymanId()),
                new ServiceRequestIdentifier(serviceRequester.getServiceRequestId())
        );

        Job savedJob = repository.save(newJob);
        JobResponseModel jobResponseModel = responseMapper.toResponseModel(savedJob);
        jobResponseModel.setCustomerEmail(customer.getEmail());
        jobResponseModel.setCustomerName(customer.getFirstName() + " " + customer.getLastName());

        jobResponseModel.setHandymanName(handyman.getFirstName() + " " + handyman.getLastName());
        jobResponseModel.setHandymanEmail(handyman.getEmail());
        jobResponseModel.setIsActive(handyman.getIsActive());

        jobResponseModel.setRequestStatus(serviceRequester.getStatus());
        jobResponseModel.setProblemDescription(serviceRequester.getProblemDescription());
        jobResponseModel.add(linkTo(methodOn(JobController.class)
                .getJob(savedJob.getJobIdentifier().getJobId())).withSelfRel());


        return jobResponseModel;
    }

    @Override
    public JobResponseModel updateJob(String jobId, JobRequestModel requestModel) {
        Job job = repository.findAllByJobIdentifier_JobId(jobId);
        if (job == null){
            throw new NotFoundException("Sorry but the job is not found at the given id: " + jobId);
        }
        if (requestModel == null){
            throw new InvalidInputException("Sorry but the request cannot be null: " + requestModel);
        }
        CustomerResponseModel customer = customerService.getByEmail(requestModel.getCustomerEmail());
        HandymanProfileResponseModel handyman = handymanService.getEmail(requestModel.getHandymanEmail());
        ServiceRequesterResponseModel serviceRequester = serviceRequest.getByCustomerId(customer.getCustomerId());

        job.setJobLocation(requestModel.getJobLocation());
        job.setAssignment(requestModel.getAssignment());
        job.setServiceCategory(requestModel.getServiceCategory());
        job.setSchedule(requestModel.getSchedule());
        job.setStatus(requestModel.getStatus());
        job.setCreatedAt(requestModel.getCreatedAt());
        job.setScheduledStartTime(requestModel.getScheduledStartTime());
        job.setActualStartTime(requestModel.getActualStartTime());
        job.setActualEndTime(requestModel.getActualEndTime());
        job.setEstimatedDuration(requestModel.getEstimatedDuration());
        job.setNotes(requestModel.getNotes());
        job.setMaterialRequirements(requestModel.getMaterialRequirements());

        Job savedJob = repository.save(job);
        JobResponseModel jobResponseModel = responseMapper.toResponseModel(savedJob);
        jobResponseModel.setCustomerEmail(customer.getEmail());
        jobResponseModel.setCustomerName(customer.getFirstName() + " " + customer.getLastName());

        jobResponseModel.setHandymanName(handyman.getFirstName() + " " + handyman.getLastName());
        jobResponseModel.setHandymanEmail(handyman.getEmail());
        jobResponseModel.setIsActive(handyman.getIsActive());

        jobResponseModel.setRequestStatus(serviceRequester.getStatus());
        jobResponseModel.setProblemDescription(serviceRequester.getProblemDescription());
        jobResponseModel.add(linkTo(methodOn(JobController.class)
                .getJob(savedJob.getJobIdentifier().getJobId())).withSelfRel());

//this is done
        return jobResponseModel;
    }
}
