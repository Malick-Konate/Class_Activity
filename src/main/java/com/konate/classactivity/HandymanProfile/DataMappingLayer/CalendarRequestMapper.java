package com.konate.classactivity.HandymanProfile.DataMappingLayer;

import com.konate.classactivity.HandymanProfile.DataLayer.AvailabilityCalendar;
import com.konate.classactivity.HandymanProfile.DataLayer.AvailableIdentifier;
import com.konate.classactivity.HandymanProfile.DataLayer.HandymanIdentifier;
import com.konate.classactivity.HandymanProfile.PresentationLayer.CalendarRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CalendarRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "lastUpdated", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "calendarEntries", source = "requestModel.calendarEntries"),
            @Mapping(target = "availabilityCalendarIdentifier", expression = "java(availableIdentifier)"),
            @Mapping(target = "handymanIdentifier", expression = "java(handymanIdentifier)")



    })
    AvailabilityCalendar toAvailabilityCalendar(CalendarRequestModel requestModel,
                                                AvailableIdentifier availableIdentifier,
                                                HandymanIdentifier handymanIdentifier);
}
