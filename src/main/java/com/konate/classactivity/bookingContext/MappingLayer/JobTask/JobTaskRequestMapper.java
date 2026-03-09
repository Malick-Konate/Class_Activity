package com.konate.classactivity.bookingContext.MappingLayer.JobTask;

import com.konate.classactivity.bookingContext.DataLayer.JobIdentifier;
import com.konate.classactivity.bookingContext.DataLayer.jobTask.JobTask;
import com.konate.classactivity.bookingContext.DataLayer.jobTask.JobTaskIdentifier;
import com.konate.classactivity.bookingContext.PresentationLayer.jobTask.JobTaskRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface JobTaskRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(jobIdentifier)", target = "jobIdentifier"),
            @Mapping(expression = "java(identifier)", target = "jobTaskIdentifier"),
            @Mapping(source = "requestModel.taskDescription", target = "taskDescription"),
            @Mapping(source = "requestModel.status", target = "status"),
            @Mapping(source = "requestModel.estimatedTime", target = "estimatedTime"),
            @Mapping(source = "requestModel.actualTime", target = "actualTime"),
            @Mapping(source = "requestModel.requiresSpecialTools", target = "requiresSpecialTools")

    })
    JobTask toJobTask(JobTaskRequestModel requestModel,
                      JobTaskIdentifier identifier,
                      JobIdentifier jobIdentifier);
}
