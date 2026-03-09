package com.konate.classactivity.HandymanProfile.PresentationLayer;

import com.konate.classactivity.HandymanProfile.DataLayer.CalendarEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarRequestModel {
    List<CalendarEntry> calendarEntries;
}
