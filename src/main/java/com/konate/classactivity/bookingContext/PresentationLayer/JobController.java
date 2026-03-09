package com.konate.classactivity.bookingContext.PresentationLayer;

import com.konate.classactivity.bookingContext.BusinessLayer.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {
    private final JobService service;
    @Autowired
    public JobController(JobService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobResponseModel>> getAllJobs(){
        return new ResponseEntity<>(service.getAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponseModel> getJob(@PathVariable String jobId){
        return new ResponseEntity<>(service.getJob(jobId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable String jobId){
        service.delete(jobId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<JobResponseModel> create_job(@RequestBody JobRequestModel requestModel){
        return new ResponseEntity<>(service.createJob(requestModel), HttpStatus.CREATED);
    }

    @PutMapping("/update/{jobId}")
    public ResponseEntity<JobResponseModel> update_job(@PathVariable String jobId, @RequestBody JobRequestModel requestModel){
        return new ResponseEntity<>(service.updateJob(jobId, requestModel), HttpStatus.OK);
    }
}
