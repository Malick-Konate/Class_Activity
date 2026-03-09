package com.konate.classactivity.HandymanProfile.PresentationLayer;


import com.konate.classactivity.HandymanProfile.DataLayer.AvailabilityCalendar;
import com.konate.classactivity.HandymanProfile.DataLayer.BackgroundCheck;
import com.konate.classactivity.HandymanProfile.DataLayer.SkillSet;
import com.konate.classactivity.HandymanProfile.DataLayer.WorkZone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandymanProfileRequestModel {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Work zone is required")
    private List<WorkZone> workZone;

    private List<SkillSet> skillSets;

    //    private AvailabilityCalendar availabilityCalendar;
    private String calendarId;

    private Boolean isActive;
    private Boolean isVerified;
//    private LocalDateTime registrationDate;
    private BackgroundCheck backgroundCheck;
}