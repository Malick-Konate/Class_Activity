package com.konate.classactivity.HandymanProfile.DataLayer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvailabilityCalendarRepository extends JpaRepository<AvailabilityCalendar, Long> {
    AvailabilityCalendar findByAvailabilityCalendarIdentifier_CalendarId(String calendarId);

    List<AvailabilityCalendar> findByHandymanIdentifier_HandymanId(String handymanId);
}
