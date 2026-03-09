package com.konate.classactivity.HandymanProfile.DataLayer;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "availability_calendars")
@Data
@NoArgsConstructor
public class AvailabilityCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private AvailableIdentifier availabilityCalendarIdentifier;

    @Embedded
    private HandymanIdentifier handymanIdentifier;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "calendar_entries",
            joinColumns = @JoinColumn(name = "calendar_id", referencedColumnName = "calendar_id")
    )
    private List<CalendarEntry> calendarEntries;
}