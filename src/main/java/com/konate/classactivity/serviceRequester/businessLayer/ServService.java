package com.konate.classactivity.serviceRequester.businessLayer;

import com.konate.classactivity.serviceRequester.presentationLayer.service.ServiceRequesterRequestModel;
import com.konate.classactivity.serviceRequester.presentationLayer.service.ServiceRequesterResponseModel;

import java.util.List;

public interface ServService {
    ServiceRequesterResponseModel createServiceRequest(ServiceRequesterRequestModel serviceRequesterRequestModel);
    ServiceRequesterResponseModel updateServiceRequest(String serviceRequestId, ServiceRequesterRequestModel serviceRequesterRequestModel);
    void deleteServiceRequest(String serviceRequestId);
    ServiceRequesterResponseModel getServiceRequest(String serviceRequestId);
    List<ServiceRequesterResponseModel> getAllServiceRequests();

    ServiceRequesterResponseModel getByCustomerId(String customerId);
}
