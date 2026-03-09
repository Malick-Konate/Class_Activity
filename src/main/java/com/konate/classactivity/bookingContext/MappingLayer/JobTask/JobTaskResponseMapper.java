package com.konate.classactivity.bookingContext.MappingLayer.JobTask;

import com.konate.classactivity.bookingContext.DataLayer.jobTask.JobTask;
import com.konate.classactivity.bookingContext.PresentationLayer.jobTask.JobTaskResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobTaskResponseMapper {
    @Mappings({
            @Mapping(source = "jobTaskIdentifier.jobtaskId", target = "jobTaskIdentifier"),
            @Mapping(source = "taskDescription", target = "taskDescription"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "estimatedTime", target = "estimatedTime"),
            @Mapping(source = "actualTime", target = "actualTime"),
            @Mapping(source = "requiresSpecialTools", target = "requiresSpecialTools")


    })
    JobTaskResponseModel toResponseModel(JobTask jobTask);

    List<JobTaskResponseModel> listJobToResponseModelList(List<JobTask> list);
}
