package com.konate.classactivity.bookingContext.MappingLayer;

import com.konate.classactivity.HandymanProfile.DataLayer.HandymanIdentifier;
import com.konate.classactivity.bookingContext.DataLayer.Job;
import com.konate.classactivity.bookingContext.DataLayer.JobIdentifier;
import com.konate.classactivity.bookingContext.PresentationLayer.JobRequestModel;
import com.konate.classactivity.serviceRequester.DataAccessLayer.ServiceRequestIdentifier;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerIdentifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface JobRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(jobIdentifier)", target = "jobIdentifier"),
            @Mapping(expression = "java(customerIdentifier)", target = "customerIdentifier"),
            @Mapping(expression = "java(handymanIdentifier)", target = "handymanIdentifier"),
            @Mapping(expression = "java(serviceRequestIdentifier)", target = "serviceRequestIdentifier"),
            @Mapping(source = "requestModel.status", target = "status"),
            @Mapping(source = "requestModel.createdAt", target = "createdAt"),
            @Mapping(source = "requestModel.scheduledStartTime", target = "scheduledStartTime"),
            @Mapping(source = "requestModel.actualStartTime", target = "actualStartTime"),
            @Mapping(source = "requestModel.actualEndTime", target = "actualEndTime"),
            @Mapping(source = "requestModel.estimatedDuration", target = "estimatedDuration"),
            @Mapping(source = "requestModel.notes", target = "notes"),
            @Mapping(source = "requestModel.assignment", target = "assignment"),
            @Mapping(source = "requestModel.jobLocation", target = "jobLocation"),
            @Mapping(source = "requestModel.schedule", target = "schedule"),
            @Mapping(source = "requestModel.serviceCategory", target = "serviceCategory"),
            @Mapping(source = "requestModel.materialRequirements", target = "materialRequirements")




    })
    Job toJob(JobRequestModel requestModel,
              JobIdentifier jobIdentifier,
              CustomerIdentifier customerIdentifier,
              HandymanIdentifier handymanIdentifier,
              ServiceRequestIdentifier serviceRequestIdentifier);
}
