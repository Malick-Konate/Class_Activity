package com.konate.classactivity.serviceRequester.DataAccessLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;

@Embeddable
@Getter
public class Preferences {
    @Embedded
    private Money maxBudget;

    @Column(name = "preferred_handyman_gender")
    private String preferredHandymanGender;

    @Column(name = "language_preference")
    private String languagePreference;

    @Column(name = "require_insurance")
    private Boolean requireInsurance;

    public Preferences() {
    }
    public Preferences(Money maxBudget, String preferredHandymanGender, String languagePreference, Boolean requireInsurance) {
        this.maxBudget = maxBudget;
        this.preferredHandymanGender = preferredHandymanGender;
        this.languagePreference = languagePreference;
        this.requireInsurance = requireInsurance;
    }
}
