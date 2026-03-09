package com.konate.classactivity.HandymanProfile.DataMappingLayer;

import com.konate.classactivity.HandymanProfile.DataLayer.HandymanProfile;
import com.konate.classactivity.HandymanProfile.PresentationLayer.HandymanProfileResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HandymanResponseMapper {
    @Mappings({
            @Mapping(source = "handymanIdentifier.handymanId", target = "handymanId"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "isActive", target = "isActive"),
            @Mapping(source = "isVerified", target = "isVerified"),
            @Mapping(source = "skillSets", target = "skillSets"),
            @Mapping(source = "registrationDate", target = "registrationDate"),
            @Mapping(source = "workZone", target = "workZone"),
            @Mapping(source = "backgroundCheck", target = "backgroundCheck")

//            @Mapping(source = "availabilityCalendarIdentifier.calendarId", target = "calendarId")

    })
    HandymanProfileResponseModel toResponseModel(HandymanProfile profile);

    List<HandymanProfileResponseModel> entityListToResponseModelList (List<HandymanProfile> list);
}
