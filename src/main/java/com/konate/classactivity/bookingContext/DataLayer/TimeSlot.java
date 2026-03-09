package com.konate.classactivity.bookingContext.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TimeSlot {

    @Column(name = "slot_start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "slot_end_time", nullable = false)
    private LocalTime endTime;
}