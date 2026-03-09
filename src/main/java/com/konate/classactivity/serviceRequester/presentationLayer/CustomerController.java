package com.konate.classactivity.serviceRequester.presentationLayer;

import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.Customer;
import com.konate.classactivity.serviceRequester.businessLayer.customer.CustomerService;
import com.konate.classactivity.serviceRequester.presentationLayer.customer.CustomerRequestModel;
import com.konate.classactivity.serviceRequester.presentationLayer.customer.CustomerResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerAssembler customerAssembler;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerAssembler customerAssembler) {
        this.customerService = customerService;
        this.customerAssembler = customerAssembler;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseModel>> getAll(){
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public  ResponseEntity<EntityModel<CustomerResponseModel>> getById(@PathVariable String customerId){

        CustomerResponseModel customer = customerService.getOneCustomer(customerId);

        EntityModel<CustomerResponseModel> customerResponseModel = customerAssembler.toModel(customer);


        return new ResponseEntity<>(customerResponseModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseModel> create(@RequestBody CustomerRequestModel requestModel){
        return new ResponseEntity<>(customerService.createCustomer(requestModel), HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponseModel> update(@PathVariable String customerId, @RequestBody CustomerRequestModel requestModel){
        return  new ResponseEntity<>(customerService.updateCustomer(customerId, requestModel), HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable String customerId){
        customerService.delete(customerId);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
