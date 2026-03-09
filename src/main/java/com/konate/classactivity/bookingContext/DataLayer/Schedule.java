package com.konate.classactivity.bookingContext.DataLayer;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Schedule {

    @Column(name = "scheduled_date", nullable = false)
    private LocalDate scheduledDate;

//    @Embedded
//    private TimeSlot timeSlot; // This is what we override above using "timeSlot.startTime"

    @Column(name = "slot_start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "slot_end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "schedule_duration", nullable = false)// Specific name to avoid "estimated_duration" conflict
    private Integer estimatedDuration;

    @Column(name = "buffer_time_minutes")
    private Integer bufferTime;
}