package com.konate.classactivity.serviceRequester.businessLayer;

import com.konate.classactivity.Exceptions.InvalidInputException;
import com.konate.classactivity.Exceptions.NotFoundException;
import com.konate.classactivity.serviceRequester.DataAccessLayer.RequestStatus;
import com.konate.classactivity.serviceRequester.DataAccessLayer.ServiceRequest;
import com.konate.classactivity.serviceRequester.DataAccessLayer.ServiceRequestIdentifier;
import com.konate.classactivity.serviceRequester.DataAccessLayer.ServiceRequesterRepository;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.Customer;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerRepository;
import com.konate.classactivity.serviceRequester.businessLayer.customer.CustomerService;
import com.konate.classactivity.serviceRequester.dataMappingLayer.CustomerResponseMapper;
import com.konate.classactivity.serviceRequester.dataMappingLayer.ServiceRequestMapper;
import com.konate.classactivity.serviceRequester.dataMappingLayer.ServiceResponseMapper;
import com.konate.classactivity.serviceRequester.presentationLayer.customer.CustomerResponseModel;
import com.konate.classactivity.serviceRequester.presentationLayer.service.ServiceRequesterRequestModel;
import com.konate.classactivity.serviceRequester.presentationLayer.service.ServiceRequesterResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServServiceImpl implements ServService {
    private final ServiceRequesterRepository repository;

    private final ServiceRequestMapper requestMapper;

    private final ServiceResponseMapper responseMapper;

//    private final CustomerRepository customerRepository;

    private final CustomerService customerService;

    public ServServiceImpl(ServiceRequesterRepository repository, ServiceRequestMapper requestMapper, ServiceResponseMapper responseMapper, CustomerService customerService) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
//        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @Override
    public ServiceRequesterResponseModel createServiceRequest( ServiceRequesterRequestModel serviceRequesterRequestModel) {
//        Customer customer = customerRepository.findAllByCustomerIdentifier_CustomerId(customerId);
//        if (customer == null) {
//            throw  new NotFoundException("Customer not found at this Id: " + customerId);
//        }
        try{
            ServiceRequest service = requestMapper.toServiceRequest(serviceRequesterRequestModel,
                    new ServiceRequestIdentifier());
            CustomerResponseModel customer = customerService.getByEmail(serviceRequesterRequestModel.getCustomerEmail());
            service.setStatus(RequestStatus.SUBMITTED);
            ServiceRequest savedService = repository.save(service);
            ServiceRequesterResponseModel responseModel = responseMapper.toServiceResponse(savedService);
            responseModel.setCustomerEmail(customer.getEmail());
            return responseModel;

        }catch (Exception e){
            throw new InvalidInputException("Could not create a service request: " + e.getMessage());

        }
    }

    @Override
    public ServiceRequesterResponseModel updateServiceRequest(String serviceRequestId, ServiceRequesterRequestModel serviceRequesterRequestModel) {
//        Customer customer = customerRepository.findAllByCustomerIdentifier_CustomerId(customerId);
//        if (customer == null) {
//            throw  new NotFoundException("Customer not found at this Id: " + customerId);
//        }
        ServiceRequest service = repository.findAllByServiceRequestIdentifier_ServiceId(serviceRequestId);
        if (service == null) {
            throw   new NotFoundException("Service request not found at this Id: " + serviceRequestId);
        }
        service.setPreferences(serviceRequesterRequestModel.getPreferences());
        service.setPreferredDate(serviceRequesterRequestModel.getPreferredDate());
        service.setProblemDescription(serviceRequesterRequestModel.getProblemDescription());
        service.setSearchCriteria(serviceRequesterRequestModel.getSearchCriteria());
        service.setStatus(RequestStatus.DRAFT);
        service.setUrgency(serviceRequesterRequestModel.getUrgency());

        ServiceRequest updated = repository.save(service);
        CustomerResponseModel customer = customerService.getByEmail(serviceRequesterRequestModel.getCustomerEmail());
        ServiceRequesterResponseModel responseModel = responseMapper.toServiceResponse(updated);
        responseModel.setCustomerEmail(customer.getEmail());
        return responseModel;
    }

    @Override
    public void deleteServiceRequest(String serviceRequestId) {
//        Customer customer = customerRepository.findAllByCustomerIdentifier_CustomerId(customerId);
//        if (customer == null) {
//            throw  new NotFoundException("Customer not found at this Id: " + customerId);
//        }
        ServiceRequest service = repository.findAllByServiceRequestIdentifier_ServiceId(serviceRequestId);
        if (service == null) {
            throw   new NotFoundException("Service request not found at this Id: " + serviceRequestId);
        }
        repository.delete(service);
    }

    @Override
    public ServiceRequesterResponseModel getServiceRequest(String serviceRequestId) {
//        Customer customer = customerRepository.findAllByCustomerIdentifier_CustomerId(customerId);
//        if (customer == null) {
//            throw  new NotFoundException("Customer not found at this Id: " + customerId);
//        }
        ServiceRequest service = repository.findAllByServiceRequestIdentifier_ServiceId(serviceRequestId);
        if (service == null) {
            throw   new NotFoundException("Service request not found at this Id: " + serviceRequestId);
        }
        CustomerResponseModel customer = customerService.getOneCustomer(service.getCustomerId().getCustomerId());

        ServiceRequesterResponseModel responseModel = responseMapper.toServiceResponse(service);
        responseModel.setCustomerEmail(customer.getEmail());

        return responseModel;
    }

    @Override
    public List<ServiceRequesterResponseModel> getAllServiceRequests() {
//        Customer customer = customerRepository.findAllByCustomerIdentifier_CustomerId(customerId);
//        if (customer == null) {
//            throw  new NotFoundException("Customer not found at this Id: " + customerId);
//        }
        List<ServiceRequest> list = repository.findAll();
        List<ServiceRequesterResponseModel> responseModels = new ArrayList<>();
        for (ServiceRequest serviceRequest : list) {
            ServiceRequesterResponseModel responseModel = responseMapper.toServiceResponse(serviceRequest);
            CustomerResponseModel customer = customerService.getOneCustomer(serviceRequest.getCustomerId().getCustomerId());

            responseModel.setCustomerEmail(customer.getEmail());
            responseModels.add(responseModel);

        }

        return responseModels;
    }

    @Override
    public ServiceRequesterResponseModel getByCustomerId(String customerId) {
        CustomerResponseModel customer = customerService.getOneCustomer(customerId);
        ServiceRequest service = repository.findAllByCustomerId_CustomerId(customerId);
        if (service == null) {
            throw   new NotFoundException("Service request not found at this customer Id: " + customerId);
        }


        return responseMapper.toServiceResponse(service);
    }
}
