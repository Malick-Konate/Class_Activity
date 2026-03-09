package com.konate.classactivity.serviceRequester.dataMappingLayer;
import com.konate.classactivity.serviceRequester.DataAccessLayer.ServiceRequest;
import com.konate.classactivity.serviceRequester.presentationLayer.service.ServiceRequesterResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Mapper(componentModel = "spring")
public interface ServiceResponseMapper {

    @Mappings({
            @Mapping(source = "serviceRequestIdentifier.serviceId", target = "serviceRequestId"),
            @Mapping(source = "urgency", target = "urgency"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "preferredDate", target = "preferredDate"),
            @Mapping(source = "problemDescription", target = "problemDescription"),
            @Mapping(source = "searchCriteria", target = "searchCriteria"),
            @Mapping(source = "preferences", target = "preferences")

    })
    ServiceRequesterResponseModel toServiceResponse (ServiceRequest serviceRequest);

    List<ServiceRequesterResponseModel> entityListResponseModels (List<ServiceRequest> serviceRequests);

}
