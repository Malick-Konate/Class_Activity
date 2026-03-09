package com.konate.classactivity.HandymanProfile.PresentationLayer;


import com.konate.classactivity.HandymanProfile.Business.HandymanProfileServiceImpl;
import com.konate.classactivity.HandymanProfile.Business.HandymanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/handymen")
public class HandymanController {

    private final HandymanService handymanProfileService;

    @Autowired
    public HandymanController(HandymanService handymanProfileService) {
        this.handymanProfileService = handymanProfileService;
    }


    @GetMapping
    public ResponseEntity<List<HandymanProfileResponseModel>> getAllHandymanProfiles() {
        List<HandymanProfileResponseModel> response = handymanProfileService.getAllHandymanProfiles();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{handymanId}")
    public ResponseEntity<HandymanProfileResponseModel> getHandymanProfile(
            @PathVariable String handymanId) {
        HandymanProfileResponseModel response = handymanProfileService.getHandymanProfile(handymanId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<HandymanProfileResponseModel> createHandymanProfile(
            @Valid @RequestBody HandymanProfileRequestModel requestDTO) {
        HandymanProfileResponseModel response = handymanProfileService.createHandymanProfile(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{handymanId}")
    public ResponseEntity<HandymanProfileResponseModel> updateHandymanProfile(
            @PathVariable String handymanId,
            @Valid @RequestBody HandymanProfileRequestModel requestDTO) {
        HandymanProfileResponseModel response =
                handymanProfileService.updateHandymanProfile(handymanId, requestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/active")
    public ResponseEntity<List<HandymanProfileResponseModel>> getActiveHandymen() {
        List<HandymanProfileResponseModel> response = handymanProfileService.getAllHandymanProfiles();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    @PostMapping("/{handymanId}/background-checks")
//    public ResponseEntity<HandymanProfileResponseModel> addBackgroundCheck(
//            @PathVariable String handymanId,
//            @Valid @RequestBody BackgroundCheckRequestModel requestDTO) {
//        HandymanProfileResponseModel response = handymanProfileService.addBackgroundCheck(handymanId, requestDTO);
//        return ResponseEntity.ok(response);
//    }

    @DeleteMapping("/{handymanId}")
    public ResponseEntity<Void> deleteHandymanProfile(@PathVariable String handymanId) {
        handymanProfileService.deleteHandymanProfile(handymanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{handymanId}/availability-calendar")
    public ResponseEntity<List<CalendarResponseModel>> getAvailabilityCalendar(@PathVariable String handymanId) {
        List<CalendarResponseModel> response = handymanProfileService.getAvailabilityCalendar(handymanId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{handymanId}/availability-calendar/{calendarId}")
    public ResponseEntity<CalendarResponseModel> getAvailabilityCalendar(@PathVariable String handymanId, @PathVariable String calendarId) {
        CalendarResponseModel response = handymanProfileService.getAvailabilityCalendarById(handymanId, calendarId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{handymanId}/availability-calendar")
    public ResponseEntity<CalendarResponseModel> createAvailabilityCalendar(@PathVariable String handymanId, @RequestBody CalendarRequestModel requestDTO) {
        CalendarResponseModel response = handymanProfileService.createAvailabilityCalendar(handymanId, requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{handymanId}/availability-calendar/{calendarId}")
    public ResponseEntity<CalendarResponseModel> updateAvailabilityCalendar(@PathVariable String handymanId, @PathVariable String calendarId, @RequestBody CalendarRequestModel requestDTO) {
        CalendarResponseModel response = handymanProfileService.updateAvailabilityCalendar(handymanId, calendarId, requestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{handymanId}/availability-calendar/{calendarId}")
    public ResponseEntity<Void> deleteAvailabilityCalendar(@PathVariable String handymanId, @PathVariable String calendarId) {
        handymanProfileService.deleteAvailabilityCalendar(handymanId, calendarId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}