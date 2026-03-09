package com.konate.classactivity.bookingContext.PresentationLayer.jobTask;

import com.konate.classactivity.bookingContext.BusinessLayer.jobTask.JobTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class JobTaskController {

    private final JobTaskService service;


    @Autowired
    public JobTaskController(JobTaskService service) {
        this.service = service;
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<List<JobTaskResponseModel>> getAllByJob(@PathVariable String jobId){
        return new ResponseEntity<>(service.getAll_by_jobId(jobId), HttpStatus.OK);
    }

    @GetMapping("/{jobId}/{taskId}")
    public ResponseEntity<JobTaskResponseModel> get_one(@PathVariable String jobId, @PathVariable String taskId){
        return new ResponseEntity<>(service.get_jobTask_by_job(jobId, taskId), HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}/{taskId}")
    public ResponseEntity<Void> delete(@PathVariable String jobId, @PathVariable String taskId){
        service.deleteTask(jobId, taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{jobId}/{taskId}")
    public ResponseEntity<JobTaskResponseModel> update(@PathVariable String jobId, @PathVariable String taskId, @RequestBody JobTaskRequestModel requestModel){
        return new ResponseEntity<>(service.update_jobTask(jobId, taskId, requestModel), HttpStatus.OK);
    }

    @PostMapping("/{jobId}")
    public ResponseEntity<JobTaskResponseModel> create(@PathVariable String jobId, @RequestBody JobTaskRequestModel requestMode){
        return new ResponseEntity<>(service.create_jobTask(jobId, requestMode), HttpStatus.CREATED);
    }

}
