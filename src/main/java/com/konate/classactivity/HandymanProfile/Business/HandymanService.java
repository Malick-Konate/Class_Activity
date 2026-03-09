package com.konate.classactivity.HandymanProfile.Business;

import com.konate.classactivity.HandymanProfile.DataLayer.AvailabilityCalendar;
import com.konate.classactivity.HandymanProfile.PresentationLayer.CalendarRequestModel;
import com.konate.classactivity.HandymanProfile.PresentationLayer.CalendarResponseModel;
import com.konate.classactivity.HandymanProfile.PresentationLayer.HandymanProfileRequestModel;
import com.konate.classactivity.HandymanProfile.PresentationLayer.HandymanProfileResponseModel;

import java.util.List;

public interface HandymanService {
    List<HandymanProfileResponseModel> getAllHandymanProfiles();
    HandymanProfileResponseModel getHandymanProfile(String handymanId);
    HandymanProfileResponseModel createHandymanProfile(HandymanProfileRequestModel requestDTO);
    HandymanProfileResponseModel updateHandymanProfile(String handymanId, HandymanProfileRequestModel requestDTO);
    void deleteHandymanProfile(String handymanId);

    HandymanProfileResponseModel getEmail(String email);

    List<CalendarResponseModel> getAvailabilityCalendar(String handymanId);
    CalendarResponseModel getAvailabilityCalendarById(String handymanId, String calendarId);
    CalendarResponseModel createAvailabilityCalendar(String handymanId, CalendarRequestModel requestDTO);
    CalendarResponseModel updateAvailabilityCalendar(String handymanId, String calendarId, CalendarRequestModel requestDTO);
    void deleteAvailabilityCalendar(String handymanId, String calendarId);
}
