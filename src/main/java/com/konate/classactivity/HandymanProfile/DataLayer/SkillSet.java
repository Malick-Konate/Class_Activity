package com.konate.classactivity.HandymanProfile.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkillSet {

    @Column(name = "skill_name")
    private String skillName;

    @Enumerated(EnumType.STRING)
    @Column(name = "proficiency_level")
    private ProficiencyLevel proficiencyLevel;

    public void validate() {
        if (skillName == null || skillName.trim().isEmpty()) {
            throw new IllegalArgumentException("Skill name is required");
        }
        if (proficiencyLevel == null) {
            throw new IllegalArgumentException("Proficiency level is required");
        }
    }

    public boolean isVerified() {
        // A skill is considered verified if it's at least INTERMEDIATE level
        return proficiencyLevel == ProficiencyLevel.INTERMEDIATE
                || proficiencyLevel == ProficiencyLevel.ADVANCED
                || proficiencyLevel == ProficiencyLevel.EXPERT;
    }
}
