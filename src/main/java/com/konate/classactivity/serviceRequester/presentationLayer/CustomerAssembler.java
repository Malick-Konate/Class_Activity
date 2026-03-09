package com.konate.classactivity.serviceRequester.presentationLayer;

import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.Customer;
import com.konate.classactivity.serviceRequester.presentationLayer.customer.CustomerRequestModel;
import com.konate.classactivity.serviceRequester.presentationLayer.customer.CustomerResponseModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerAssembler implements RepresentationModelAssembler<CustomerResponseModel, EntityModel<CustomerResponseModel>> {
    @Override
    public EntityModel<CustomerResponseModel> toModel(CustomerResponseModel dto) {
        EntityModel<CustomerResponseModel> model = EntityModel.of(dto,
                linkTo(methodOn(CustomerController.class).getById(dto.getCustomerId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).getAll()).withRel("customers"),
                linkTo(methodOn(CustomerController.class).create(null)).withRel("create customer")
        );
        return model;
    }
}
