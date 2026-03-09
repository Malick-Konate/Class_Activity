package com.konate.classactivity.HandymanProfile.DataLayer;

import com.konate.classactivity.Exceptions.HandymanNotEligibleException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "handyman_profiles")
@Builder
@AllArgsConstructor
public class HandymanProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private HandymanIdentifier handymanIdentifier;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "handyman_skills",
            joinColumns = @JoinColumn(name = "handyman_id", referencedColumnName = "handyman_id")
    )
    private List<SkillSet> skillSets;


    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
    //    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "city", column = @Column(name = "work_zone_city")),
//            @AttributeOverride(name = "province", column = @Column(name = "work_zone_province")),
//            @AttributeOverride(name = "maxTravelDistance", column = @Column(name = "max_travel_distance"))
//    })
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "work_zones",
            joinColumns = @JoinColumn(name = "handyman_id", referencedColumnName = "handyman_id")
    )
    private List<WorkZone> workZone;


    @Embedded
    private BackgroundCheck backgroundCheck;

    @Embedded
    private AvailableIdentifier availabilityCalendarIdentifier; // Link via ID


    public void activate() {
        if (!hasVerifiedSkill()) {
            throw new HandymanNotEligibleException(
                    "Cannot activate handyman: No verified skills found. " +
                            "At least one skill with INTERMEDIATE level or higher is required."
            );
        }

//        if (!hasPassedBackgroundCheck()) {
//            throw new HandymanNotEligibleException(
//                    "Cannot activate handyman: No valid background check found. " +
//                            "A passed and non-expired background check is required."
//            );
//        }

        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void verify() {
        this.isVerified = true;
    }

    public void unverify() {
        this.isVerified = false;
        this.isActive = false; // If unverified, must also be inactive
    }

    public void addSkill(SkillSet skillSet) {
        skillSet.validate();
        this.skillSets.add(skillSet);
    }

    public void removeSkill(String skillName) {
        this.skillSets.removeIf(skill -> skill.getSkillName().equalsIgnoreCase(skillName));

        // Check if still eligible to be active after removing skill
        if (this.isActive && !hasVerifiedSkill()) {
            this.isActive = false;
        }
    }

//    public void updateWorkZone(WorkZone newWorkZone) {
//        newWorkZone.validate();
//        this.workZone = newWorkZone;
//    }

    public void updateEmail(String newEmail) {
        validateEmail(newEmail);
        this.email = newEmail;
    }

//    public void addBackgroundCheck(BackgroundCheck backgroundCheck) {
//        this.backgroundChecks.add(backgroundCheck);
//    }

//    public void updateAvailabilityCalendar(AvailabilityCalendar newCalendar) {
//
//        this.availabilityCalendar = newCalendar;
//
//    }

    // Helper methods for invariant checking

    private boolean hasVerifiedSkill() {
        return skillSets.stream().anyMatch(SkillSet::isVerified);
    }

//    private boolean hasPassedBackgroundCheck() {
//        return backgroundChecks.stream().anyMatch(BackgroundCheck::isPassed);
//    }

    // Validation methods

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private void validateName(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
    }
}

