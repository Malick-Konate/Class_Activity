package com.konate.classactivity.HandymanProfile.PresentationLayer;

import com.konate.classactivity.HandymanProfile.DataLayer.CalendarEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponseModel {
    LocalDateTime lastUpdated;

    List<CalendarEntry> calendarEntries;
}
