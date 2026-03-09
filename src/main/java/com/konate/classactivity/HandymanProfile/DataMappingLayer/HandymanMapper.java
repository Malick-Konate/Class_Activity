//package com.konate.classactivity.HandymanProfile.DataMappingLayer;
//
//
//import com.konate.classactivity.HandymanProfile.DataLayer.*;
//import com.konate.classactivity.HandymanProfile.PresentationLayer.HandymanProfileRequestModel;
//import com.konate.classactivity.HandymanProfile.PresentationLayer.HandymanProfileResponseModel;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.stream.Collectors;
//
//@Component
//public class HandymanMapper {
//
//    public HandymanProfile toEntity(HandymanProfileRequestModel dto) {
//        WorkZone workZone = new WorkZone(
//                dto.getWorkZone().getCity(),
//                dto.getWorkZone().getProvince(),
//                dto.getWorkZone().getMaxTravelDistance()
//        );
//
//        var skillSets = dto.getSkillSets() != null
//                ? dto.getSkillSets().stream()
//                .map(s -> new SkillSet(s.getSkillName(), s.getProficiencyLevel()))
//                .collect(Collectors.toList())
//                : null;
//
//        AvailabilityCalendar calendar = null;
//        if (dto.getAvailabilityCalendar() != null) {
//            var entries = dto.getAvailabilityCalendar().getCalendarEntries().stream()
//                    .map(e -> new CalendarEntry(e.getDayOfWeek(), e.getStartTime(),
//                            e.getEndTime(), e.getIsAvailable()))
//                    .collect(Collectors.toList());
//            calendar = new AvailabilityCalendar(entries);
//            if (calendar != null) {
//                calendar.setCalendarId(null);
//            }
//        }
//        return HandymanProfile.builder()
//                .handymanIdentifier(new HandymanIdentifier())
//                .firstName(dto.getFirstName())
//                .lastName(dto.getLastName())
//                .email(dto.getEmail())
//                .registrationDate( LocalDateTime.now() )
//                .workZone(workZone)
//                .skillSets(skillSets)
//                .availabilityCalendar(calendar)
//                .isActive(true)
//                .isVerified(true)
//                .backgroundChecks(null)
//                .build() ;
//    }
//
//    public HandymanProfileResponseModel toResponseDTO(HandymanProfile entity) {
//        HandymanProfileResponseModel dto = new HandymanProfileResponseModel();
//        dto.setHandymanId(entity.getHandymanIdentifier().getHandymanId());
//        dto.setFirstName(entity.getFirstName());
//        dto.setLastName(entity.getLastName());
//        dto.setEmail(entity.getEmail());
//        dto.setIsActive(entity.getIsActive());
//        dto.setIsVerified(entity.getIsVerified());
//        dto.setRegistrationDate(entity.getRegistrationDate());
//
//        if (entity.getWorkZone() != null) {
//            WorkZone workZoneDTO = new WorkZone();
//            workZoneDTO.setCity(entity.getWorkZone().getCity());
//            workZoneDTO.setProvince(entity.getWorkZone().getProvince());
//            workZoneDTO.setMaxTravelDistance(entity.getWorkZone().getMaxTravelDistance());
//            dto.setWorkZone(workZoneDTO);
//        }
//
//        if (entity.getSkillSets() != null) {
//            var skillDTOs = entity.getSkillSets().stream()
//                    .map(s -> {
//                        SkillSet skillDTO = new SkillSet();
//                        skillDTO.setSkillName(s.getSkillName());
//                        skillDTO.setProficiencyLevel(s.getProficiencyLevel());
//                        return skillDTO;
//                    })
//                    .collect(Collectors.toList());
//            dto.setSkillSets(skillDTOs);
//        }
//
//        if (entity.getAvailabilityCalendar() != null) {
//            AvailabilityCalendar calendarDTO = new AvailabilityCalendar();
//            calendarDTO.setCalendarId(entity.getAvailabilityCalendar().getCalendarId());
//            calendarDTO.setLastUpdated(entity.getAvailabilityCalendar().getLastUpdated());
//
//            var entryDTOs = entity.getAvailabilityCalendar().getCalendarEntries().stream()
//                    .map(e -> {
//                        CalendarEntry entryDTO = new CalendarEntry();
//                        entryDTO.setDayOfWeek(e.getDayOfWeek());
//                        entryDTO.setStartTime(e.getStartTime());
//                        entryDTO.setEndTime(e.getEndTime());
//                        entryDTO.setIsAvailable(e.getIsAvailable());
//                        return entryDTO;
//                    })
//                    .collect(Collectors.toList());
//            calendarDTO.setCalendarEntries(entryDTOs);
//            dto.setAvailabilityCalendar(calendarDTO);
//        }
//
//        if (entity.getBackgroundChecks() != null) {
//            var checkDTOs = entity.getBackgroundChecks().stream()
//                    .map(bc -> {
//                        BackgroundCheck checkDTO = new BackgroundCheck();
//                        checkDTO.setCheckId(bc.getCheckId());
//                        checkDTO.setStatus(bc.getStatus());
//                        checkDTO.setPerformedDate(bc.getPerformedDate());
//                        checkDTO.setExpiryDate(bc.getExpiryDate());
//                        checkDTO.setNotes(bc.getNotes());
//                        checkDTO.setPerformedBy(bc.getPerformedBy());
//                        return checkDTO;
//                    })
//                    .collect(Collectors.toList());
//            dto.setBackgroundChecks(checkDTOs);
//        }
//
//        return dto;
//    }
//}