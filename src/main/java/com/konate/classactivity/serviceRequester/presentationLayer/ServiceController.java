package com.konate.classactivity.serviceRequester.presentationLayer;

import com.konate.classactivity.serviceRequester.businessLayer.ServService;
import com.konate.classactivity.serviceRequester.presentationLayer.service.ServiceRequesterRequestModel;
import com.konate.classactivity.serviceRequester.presentationLayer.service.ServiceRequesterResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-requests")
public class ServiceController {

    private final ServService servService;

    @Autowired
    public ServiceController(ServService servService) {
        this.servService = servService;
    }

    @PostMapping()
    public ResponseEntity<ServiceRequesterResponseModel> createServiceRequest(@RequestBody ServiceRequesterRequestModel serviceRequesterRequestModel) {
        return new ResponseEntity<>(servService.createServiceRequest(serviceRequesterRequestModel), HttpStatus.CREATED);
    }

    @PutMapping("/{serviceRequestId}")
    public ResponseEntity<ServiceRequesterResponseModel> updateServiceRequest(@PathVariable String serviceRequestId,
                                                                              @RequestBody ServiceRequesterRequestModel serviceRequesterRequestModel) {
        return new ResponseEntity<>(servService.updateServiceRequest(serviceRequestId, serviceRequesterRequestModel), HttpStatus.OK);
    }

    @DeleteMapping("/{serviceRequestId}")
    public ResponseEntity<ServiceRequesterResponseModel> deleteServiceRequest(@PathVariable String serviceRequestId) {
        servService.deleteServiceRequest(serviceRequestId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{serviceRequestId}")
    public ResponseEntity<ServiceRequesterResponseModel> getServiceRequest(@PathVariable String serviceRequestId) {
        return new ResponseEntity<>(servService.getServiceRequest(serviceRequestId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ServiceRequesterResponseModel>> getAllServiceRequests() {
        return new ResponseEntity<>(servService.getAllServiceRequests(), HttpStatus.OK);
    }
}







