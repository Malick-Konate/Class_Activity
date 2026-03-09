package com.konate.classactivity.serviceRequester.businessLayer.customer;

import com.konate.classactivity.serviceRequester.presentationLayer.customer.CustomerRequestModel;
import com.konate.classactivity.serviceRequester.presentationLayer.customer.CustomerResponseModel;

import java.util.List;

public interface CustomerService {

    CustomerResponseModel getOneCustomer(String customerId);

    List<CustomerResponseModel> getAll();

    CustomerResponseModel createCustomer(CustomerRequestModel requestModel);

    CustomerResponseModel updateCustomer(String customerId, CustomerRequestModel requestModel);

    void delete(String cutomerId);

    CustomerResponseModel getByEmail(String email);


}
