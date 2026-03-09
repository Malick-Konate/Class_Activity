package com.konate.classactivity.serviceRequester.dataMappingLayer;

import com.konate.classactivity.serviceRequester.DataAccessLayer.ServiceRequest;
import com.konate.classactivity.serviceRequester.DataAccessLayer.ServiceRequestIdentifier;
import com.konate.classactivity.serviceRequester.presentationLayer.service.ServiceRequesterRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ServiceRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(identifier)", target = "serviceRequestIdentifier"),
            @Mapping(source = "requestModel.preferredDate", target = "preferredDate"),
            @Mapping(source = "requestModel.problemDescription", target = "problemDescription"),
            @Mapping(source = "requestModel.searchCriteria", target = "searchCriteria"),
            @Mapping(source = "requestModel.preferences", target = "preferences"),
            @Mapping(source = "requestModel.urgency", target = "urgency")


    })
    ServiceRequest toServiceRequest(ServiceRequesterRequestModel requestModel,
                                    ServiceRequestIdentifier identifier);
}
