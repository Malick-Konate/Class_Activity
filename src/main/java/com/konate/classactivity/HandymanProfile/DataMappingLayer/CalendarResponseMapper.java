package com.konate.classactivity.HandymanProfile.DataMappingLayer;

import com.konate.classactivity.HandymanProfile.DataLayer.AvailabilityCalendar;
import com.konate.classactivity.HandymanProfile.PresentationLayer.CalendarResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CalendarResponseMapper {
    @Mappings({
            @Mapping(target = "lastUpdated", source = "lastUpdated"),
            @Mapping(target = "calendarEntries", source = "calendarEntries")
    })
    CalendarResponseModel toCalendarResponseModel(AvailabilityCalendar availabilityCalendar);

    List<CalendarResponseModel> toCalendarResponseModels(List<AvailabilityCalendar> availabilityCalendars);
}
