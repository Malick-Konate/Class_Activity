package com.konate.classactivity.serviceRequester.DataAccessLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable // Because of 1 to many relationship, it all gets put in the same column
public class ProblemDescription {
    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "estimated_complexity")
    private String estimatedComplexity;

    @Column(name = "details")
    private String details;

    public ProblemDescription(){

    }

    public ProblemDescription(String title, String category, String estimatedComplexity, String details){
        this.title = title;
        this.category = category;
        this.estimatedComplexity = estimatedComplexity;
        this.details = details;
    }
}


