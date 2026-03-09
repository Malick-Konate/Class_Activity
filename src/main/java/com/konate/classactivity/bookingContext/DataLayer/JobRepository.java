package com.konate.classactivity.bookingContext.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findAllByJobIdentifier_JobId(String jobIdentifierJobId);
}
