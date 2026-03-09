package com.konate.classactivity.serviceRequester.dataMappingLayer;

import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.Customer;
import com.konate.classactivity.serviceRequester.presentationLayer.customer.CustomerResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {
    @Mappings({
            @Mapping(source = "customerIdentifier.customerId", target = "customerId"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "registrationDate", target = "registrationDate"),
            @Mapping(source = "customerAddress", target = "customerAddress"),
            @Mapping(source = "phoneNumbers", target = "phoneNumbers")
            //
    })
    CustomerResponseModel toResponseModel(Customer customer);

    List<CustomerResponseModel> entityListToResponseModelList(List<Customer> customerResponseModels);

}
