package com.konate.classactivity.HandymanProfile.PresentationLayer;


import com.konate.classactivity.HandymanProfile.DataLayer.AvailabilityCalendar;
import com.konate.classactivity.HandymanProfile.DataLayer.BackgroundCheck;
import com.konate.classactivity.HandymanProfile.DataLayer.SkillSet;
import com.konate.classactivity.HandymanProfile.DataLayer.WorkZone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandymanProfileResponseModel {
    String handymanId;
    String firstName;
    String lastName;
    String email;
    Boolean isActive;
    Boolean isVerified;
    LocalDateTime registrationDate;
    List<SkillSet> skillSets;
    List<WorkZone> workZone;
    //    private AvailabilityCalendar availabilityCalendar;
//    String calendarId;
//    private List<BackgroundCheck> backgroundChecks;
//
//    private String BackgroundStatus;
//    private LocalDateTime BackgroundPerformedDate;
//    private LocalDateTime BackgroundExpiry_date;
    BackgroundCheck backgroundCheck;

}