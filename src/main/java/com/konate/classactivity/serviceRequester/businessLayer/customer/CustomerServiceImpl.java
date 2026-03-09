package com.konate.classactivity.serviceRequester.businessLayer.customer;

import com.konate.classactivity.Exceptions.InvalidInputException;
import com.konate.classactivity.Exceptions.NotFoundException;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.Customer;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerIdentifier;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerPhoneNumber;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerRepository;
import com.konate.classactivity.serviceRequester.dataMappingLayer.CustomerRequestMapper;
import com.konate.classactivity.serviceRequester.dataMappingLayer.CustomerResponseMapper;
import com.konate.classactivity.serviceRequester.presentationLayer.customer.CustomerRequestModel;
import com.konate.classactivity.serviceRequester.presentationLayer.customer.CustomerResponseModel;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements  CustomerService {

    private final CustomerRepository repository;

    private final CustomerRequestMapper requestMapper;

    private final CustomerResponseMapper responseMapper;

    public CustomerServiceImpl(CustomerRepository repository, CustomerRequestMapper requestMapper, CustomerResponseMapper responseMapper) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public CustomerResponseModel getOneCustomer(String customerId) {
        Customer customer = repository.findAllByCustomerIdentifier_CustomerId(customerId);
        if (customer == null)
            throw new NotFoundException("Customer do not exist at this id: " + customerId);


        return responseMapper.toResponseModel(customer);
    }

    @Override
    public List<CustomerResponseModel> getAll() {
        List<Customer> customers = repository.findAll();
        return responseMapper.entityListToResponseModelList(customers);
    }

    @Override
    public CustomerResponseModel createCustomer(CustomerRequestModel requestModel) {
        if (requestModel == null)
            throw new InvalidInputException("the request cannot be null");

        Customer customerEntity = requestMapper.toCustomer(
                requestModel,
                new CustomerIdentifier()
        );

        customerEntity.setRegistrationDate(new Date());

        Customer savedCustomer = repository.save(customerEntity);
        return responseMapper.toResponseModel(savedCustomer);
    }

    @Override
    public CustomerResponseModel updateCustomer(String customerId, CustomerRequestModel requestModel) {
        Customer customer = repository.findAllByCustomerIdentifier_CustomerId(customerId);
        if (customer == null)
            throw new NotFoundException("Customer do not exist at this id: " + customerId);
        if (requestModel == null)
            throw new InvalidInputException("the request cannot be null");

        customer.setEmail(requestModel.getEmail());
        customer.setFirstName(requestModel.getFirstName());
        customer.setLastName(requestModel.getLastName());
        List<CustomerPhoneNumber> phoneNumbers = requestModel.getPhoneNumbers();
        if (phoneNumbers != null && !phoneNumbers.isEmpty()) {
            customer.setPhoneNumbers(phoneNumbers);
        }
        customer.setCustomerAddress(requestModel.getCustomerAddress());

        Customer updated = repository.save(customer);
        return responseMapper.toResponseModel(updated);
    }

    @Override
    public void delete(String customerId) {
        Customer customer = repository.findAllByCustomerIdentifier_CustomerId(customerId);
        if (customer == null)
            throw new NotFoundException("Customer do not exist at this id: " + customerId);

        repository.delete(customer);

    }

    @Override
    public CustomerResponseModel getByEmail(String email) {
        Customer customer = repository.findCustomerByEmail(email);
        if (customer == null)
            throw  new NotFoundException("Customer do not exist at this id: " + email);

        return responseMapper.toResponseModel(customer);
    }
}
