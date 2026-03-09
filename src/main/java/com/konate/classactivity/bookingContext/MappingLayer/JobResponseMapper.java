package com.konate.classactivity.bookingContext.MappingLayer;

import com.konate.classactivity.bookingContext.DataLayer.Job;
import com.konate.classactivity.bookingContext.PresentationLayer.JobResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface JobResponseMapper {
    @Mappings({
            @Mapping(source = "jobIdentifier.jobId", target = "jobId"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "scheduledStartTime", target = "scheduledStartTime"),
            @Mapping(source = "actualStartTime", target = "actualStartTime"),
            @Mapping(source = "actualEndTime", target = "actualEndTime"),
            @Mapping(source = "estimatedDuration", target = "estimatedDuration"),
            @Mapping(source = "notes", target = "notes"),
            @Mapping(source = "assignment", target = "assignment"),
            @Mapping(source = "jobLocation", target = "jobLocation"),
            @Mapping(source = "schedule", target = "schedule"),
            @Mapping(source = "serviceCategory", target = "serviceCategory"),
            @Mapping(source = "materialRequirements", target = "materialRequirements")

    })
    JobResponseModel toResponseModel(Job job);

    List<JobResponseModel> listJobToResponseModelList(List<Job> jobList);
}
