package com.konate.classactivity.serviceRequester.DataAccessLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;

import java.math.BigDecimal;

@Embeddable
@Getter
public class SearchCriteria {


    @Column(name = "service_category")
    private String serviceCategory;

    @Column(name = "max_distance")
    private Integer maxDistance;
    @Embedded
    private DateRange availabilityWindow;

    @Column(name = "min_rating")
    private BigDecimal minRating;

    public SearchCriteria() {
    }
    public SearchCriteria(String serviceCategory, Integer maxDistance, DateRange availabilityWindow, BigDecimal minRating){
        this.serviceCategory = serviceCategory;
        this.maxDistance = maxDistance;
        this.availabilityWindow = availabilityWindow;
        this.minRating = minRating;
    }











}
