package com.konate.classactivity.HandymanProfile.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkZone {
    @Column(name = "city")
    private String city;

    @Column(name = "province")
    private String province;

    @Column(name = "max_travel_distance")
    private Integer maxTravelDistance; // in kilometers

    public void validate() {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City is required");
        }
        if (province == null || province.trim().isEmpty()) {
            throw new IllegalArgumentException("Province is required");
        }
        if (maxTravelDistance == null || maxTravelDistance <= 0) {
            throw new IllegalArgumentException("Max travel distance must be positive");
        }
    }

}
