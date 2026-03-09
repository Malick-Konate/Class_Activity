package com.konate.classactivity.serviceRequester.dataMappingLayer;

import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.Customer;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerIdentifier;
import com.konate.classactivity.serviceRequester.presentationLayer.customer.CustomerRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(customerIdentifier)", target = "customerIdentifier"),
            @Mapping(source = "requestModel.firstName", target = "firstName"),
            @Mapping(source = "requestModel.lastName", target = "lastName"),
            @Mapping(source = "requestModel.email", target = "email"),
            @Mapping(source = "requestModel.registrationDate", target = "registrationDate"),
            @Mapping(source = "requestModel.customerAddress", target = "customerAddress"),
            @Mapping(source = "requestModel.phoneNumbers", target = "phoneNumbers")


    })
    Customer toCustomer(CustomerRequestModel requestModel,
                        CustomerIdentifier customerIdentifier);
}
