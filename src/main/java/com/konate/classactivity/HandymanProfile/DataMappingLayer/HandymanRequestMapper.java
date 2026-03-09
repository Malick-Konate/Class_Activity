package com.konate.classactivity.HandymanProfile.DataMappingLayer;

import com.konate.classactivity.HandymanProfile.DataLayer.AvailableIdentifier;
import com.konate.classactivity.HandymanProfile.DataLayer.HandymanIdentifier;
import com.konate.classactivity.HandymanProfile.DataLayer.HandymanProfile;
import com.konate.classactivity.HandymanProfile.PresentationLayer.HandymanProfileRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//@Mapper(componentModel = "spring")
//public interface HandymanRequestMapper {
//    @Mappings({
//            @Mapping(target = "id", ignore = true),
//            @Mapping(expression = "java(handymanIdentifier)", target = "handymanIdentifier"),
//            @Mapping(expression = "java(availableIdentifier)", target = "availabilityCalendarIdentifier"),
//
//            @Mapping(source = "requestModel.firstName", target = "firstName"),
//            @Mapping(source = "requestModel.lastName", target = "lastName"),
//            @Mapping(source = "requestModel.email", target = "email"),
////            @Mapping(source = "isActive", target = "isActive"),
////            @Mapping(source = "isVerified", target = "isVerified"),
//            @Mapping(source = "requestModel.skillSets", target = "skillSets"),
////            @Mapping(source = "requestModel.registrationDate", target = "registrationDate"),
//            @Mapping(source = "requestModel.workZone", target = "workZone"),

////            @Mapping(source = "requestModel.backgroundChecks", target = "backgroundChecks"),
//
//            @Mapping(source = "requestModel.availabilityCalendar", target = "availabilityCalendar")
//
//
//
//    })
//    HandymanProfile toHandyman(HandymanProfileRequestModel requestModel,
//                               HandymanIdentifier handymanIdentifier,
//                               AvailableIdentifier availableIdentifier);


@Mapper(componentModel = "spring")
public interface HandymanRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(handymanIdentifier)", target = "handymanIdentifier"),
            @Mapping(expression = "java(calendarIdentifier)", target = "availabilityCalendarIdentifier"),
            @Mapping(source = "requestModel.firstName", target = "firstName"),
            @Mapping(source = "requestModel.lastName", target = "lastName"),
            @Mapping(source = "requestModel.email", target = "email"),
            @Mapping(source = "requestModel.skillSets", target = "skillSets"),
            @Mapping(source = "requestModel.workZone", target = "workZone"),
            @Mapping(target = "isActive", constant = "true"),
            @Mapping(target = "isVerified", constant = "false"),
            @Mapping(target = "registrationDate", expression = "java(java.time.LocalDateTime.now())")
    })
    HandymanProfile requestModelToEntity(HandymanProfileRequestModel requestModel,
                                         HandymanIdentifier handymanIdentifier,
                                         AvailableIdentifier calendarIdentifier);
}

