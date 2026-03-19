package com.konate.classactivity.serviceRequest.domain;


import com.konate.classactivity.serviceRequester.DataAccessLayer.DateRange;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class DateRangeUnitTest {

    @Test
    void shouldCreateDateRange_withValidDates() {
        // Arrange
        Date start = new Date();
        Date end = new Date(start.getTime() + 3600000); // 1 hour later

        // Act
        DateRange dateRange = new DateRange(start, end);

        // Assert
        assertNotNull(dateRange);
        assertEquals(start, dateRange.getStartDate(), "Start date should match the constructor input");
        assertEquals(end, dateRange.getEndDate(), "End date should match the constructor input");
    }

    @Test
    void noArgsConstructor_shouldInitializeToNull() {
        // Act
        DateRange dateRange = new DateRange();

        // Assert
        assertNull(dateRange.getStartDate(), "Default constructor should leave startDate null");
        assertNull(dateRange.getEndDate(), "Default constructor should leave endDate null");
    }

    @Test
    void shouldHandleSameStartAndEndDate() {
        // Arrange
        Date now = new Date();

        // Act
        DateRange dateRange = new DateRange(now, now);

        // Assert
        assertEquals(dateRange.getStartDate(), dateRange.getEndDate(), "Start and end dates can be the same");
    }

    @Test
    void shouldAcceptNullDates() {
        // Act
        DateRange dateRange = new DateRange(null, null);

        // Assert
        assertNull(dateRange.getStartDate());
        assertNull(dateRange.getEndDate());
    }
}