package com.konate.classactivity.HandymanProfile.Business;


//import com.konate.classactivity.HandymanProfile.DataMappingLayer.BackgroundCheckMapper;
//import com.konate.classactivity.HandymanProfile.DataMappingLayer.HandymanMapper;

import com.konate.classactivity.Exceptions.NotFoundException;
import com.konate.classactivity.HandymanProfile.DataLayer.*;
import com.konate.classactivity.HandymanProfile.DataMappingLayer.CalendarRequestMapper;
import com.konate.classactivity.HandymanProfile.DataMappingLayer.CalendarResponseMapper;
import com.konate.classactivity.HandymanProfile.DataMappingLayer.HandymanRequestMapper;
import com.konate.classactivity.HandymanProfile.DataMappingLayer.HandymanResponseMapper;
import com.konate.classactivity.HandymanProfile.PresentationLayer.CalendarRequestModel;
import com.konate.classactivity.HandymanProfile.PresentationLayer.CalendarResponseModel;
import com.konate.classactivity.HandymanProfile.PresentationLayer.HandymanProfileRequestModel;
import com.konate.classactivity.HandymanProfile.PresentationLayer.HandymanProfileResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class HandymanProfileServiceImpl implements HandymanService {

    private final HandymanRepository repository;
    private final HandymanResponseMapper responseMapper;
    private final HandymanRequestMapper requestMapper;

    private final CalendarRequestMapper calendarRequestMapper;
    private final CalendarResponseMapper calendarResponseMapper;

    private final AvailabilityCalendarRepository calendarRepository;
    private final AvailabilityCalendarRepository availabilityCalendarRepository;

    public HandymanProfileServiceImpl(HandymanRepository repository, HandymanResponseMapper responseMapper, HandymanRequestMapper requestMapper, CalendarRequestMapper calendarRequestMapper, CalendarResponseMapper calendarResponseMapper, AvailabilityCalendarRepository calendarRepository, AvailabilityCalendarRepository availabilityCalendarRepository) {
        this.repository = repository;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
        this.calendarRequestMapper = calendarRequestMapper;
        this.calendarResponseMapper = calendarResponseMapper;
        this.calendarRepository = calendarRepository;
        this.availabilityCalendarRepository = availabilityCalendarRepository;
    }


    @Override
    public List<HandymanProfileResponseModel> getAllHandymanProfiles() {
        List<HandymanProfile> allHandymanProfiles = repository.findAll();
        return responseMapper.entityListToResponseModelList(allHandymanProfiles);
    }

    @Override
    public HandymanProfileResponseModel getHandymanProfile(String handymanId) {
        HandymanProfile profile = repository.findByHandymanIdentifier_HandymanId(handymanId);
        if (profile == null) {
            throw new NotFoundException("profile not found: " + handymanId);
        }
        return responseMapper.toResponseModel(profile);
    }

    @Override
    public HandymanProfileResponseModel createHandymanProfile(HandymanProfileRequestModel requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("RequestDTO is null");
        }
        if (repository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + requestDTO.getEmail());
        }


        HandymanProfile profile = requestMapper.requestModelToEntity(requestDTO,
                new HandymanIdentifier(), new AvailableIdentifier(requestDTO.getCalendarId())
        );
        profile.setBackgroundCheck(new BackgroundCheck());

        HandymanProfile saved = repository.save(profile);
        return responseMapper.toResponseModel(saved);
    }

    @Override
    public HandymanProfileResponseModel updateHandymanProfile(String handymanId, HandymanProfileRequestModel requestDTO) {
        HandymanProfile profile = repository.findByHandymanIdentifier_HandymanId(handymanId);
        if (profile == null) {
            throw new NotFoundException("profile not found: " + handymanId);
        }
        if (requestDTO == null) {
            throw new IllegalArgumentException("RequestDTO is null");
        }
        profile.setEmail(requestDTO.getEmail());
        profile.setFirstName(requestDTO.getFirstName());
        profile.setLastName(requestDTO.getLastName());
        List<WorkZone> workZones = new ArrayList<>();
        workZones.addAll(requestDTO.getWorkZone());




        profile.setWorkZone(workZones);




        profile.setSkillSets(requestDTO.getSkillSets());
//        profile.setCalendarId(requestDTO.getCalendarId());
        profile.setRegistrationDate(LocalDateTime.now());
        profile.setIsActive(requestDTO.getIsActive());
        profile.setIsVerified(requestDTO.getIsVerified());
        profile.setBackgroundCheck(requestDTO.getBackgroundCheck());


        HandymanProfile saved = repository.save(profile);
        return responseMapper.toResponseModel(saved);
    }

    @Override
    public void deleteHandymanProfile(String handymanId) {

        HandymanProfile profile = repository.findByHandymanIdentifier_HandymanId(handymanId);
        if (profile == null) {
            throw new NotFoundException("profile not found: " + handymanId);
        }
        repository.delete(profile);
    }

    @Override
    public HandymanProfileResponseModel getEmail(String email) {

        HandymanProfile profile = repository.findAllByEmail(email);
        if (profile == null) {
            throw new NotFoundException("profile not found: " + email);
        }
        return responseMapper.toResponseModel(profile);
    }

    @Override
    public List<CalendarResponseModel> getAvailabilityCalendar(String handymanId) {
        HandymanProfile profile = repository.findByHandymanIdentifier_HandymanId(handymanId);
        if (profile == null) {
            throw new NotFoundException("availability calendar not found: " + handymanId);
        }
        List<AvailabilityCalendar> availabilityCalendar = availabilityCalendarRepository.findByHandymanIdentifier_HandymanId(handymanId);
        return calendarResponseMapper.toCalendarResponseModels(availabilityCalendar);
    }

    @Override
    public CalendarResponseModel getAvailabilityCalendarById(String handymanId, String calendarId) {
        HandymanProfile profile = repository.findByHandymanIdentifier_HandymanId(handymanId);
        if (profile == null) {
            throw new NotFoundException("profile not found: " + handymanId);
        }

        AvailabilityCalendar availabilityCalendarById = availabilityCalendarRepository.findByAvailabilityCalendarIdentifier_CalendarId(calendarId);
        if (availabilityCalendarById == null)
            throw new  NotFoundException("availability calendar not found: " + calendarId);

        if(!Objects.equals(profile.getHandymanIdentifier().getHandymanId(), availabilityCalendarById.getHandymanIdentifier().getHandymanId()))
            throw new NotFoundException("Calendar: " + calendarId + " not found in the handyman: " + handymanId);

        return calendarResponseMapper.toCalendarResponseModel(availabilityCalendarById);
    }

    @Override
    public CalendarResponseModel createAvailabilityCalendar(String handymanId, CalendarRequestModel requestDTO) {
        HandymanProfile profile = repository.findByHandymanIdentifier_HandymanId(handymanId);
        if (profile == null) {
            throw new NotFoundException("profile not found: " + handymanId);
        }
        AvailabilityCalendar availabilityCalendar = calendarRequestMapper.toAvailabilityCalendar(requestDTO,
                new AvailableIdentifier(), profile.getHandymanIdentifier());

        AvailabilityCalendar saved = calendarRepository.save(availabilityCalendar);
        return calendarResponseMapper.toCalendarResponseModel(saved);
    }

    @Override
    public CalendarResponseModel updateAvailabilityCalendar(String handymanId, String calendarId, CalendarRequestModel requestDTO) {
        HandymanProfile profile = repository.findByHandymanIdentifier_HandymanId(handymanId);
        if (profile == null) {
            throw new NotFoundException("profile not found: " + handymanId);
        }
        AvailabilityCalendar availabilityCalendarById = availabilityCalendarRepository.findByAvailabilityCalendarIdentifier_CalendarId(calendarId);
        if (availabilityCalendarById == null)
            throw new  NotFoundException("availability calendar not found: " + calendarId);

        if(!Objects.equals(profile.getHandymanIdentifier().getHandymanId(), availabilityCalendarById.getHandymanIdentifier().getHandymanId()))
            throw new NotFoundException("Calendar: " + calendarId + " not found in the handyman: " + handymanId);

        availabilityCalendarById.setCalendarEntries(requestDTO.getCalendarEntries());
        availabilityCalendarById.setLastUpdated(LocalDateTime.now());
        AvailabilityCalendar saved = calendarRepository.save(availabilityCalendarById);
        return calendarResponseMapper.toCalendarResponseModel(saved);
    }

    @Override
    public void deleteAvailabilityCalendar(String handymanId, String calendarId) {
        HandymanProfile profile = repository.findByHandymanIdentifier_HandymanId(handymanId);
        if (profile == null) {
            throw new NotFoundException("profile not found: " + handymanId);
        }

        AvailabilityCalendar availabilityCalendarById = availabilityCalendarRepository.findByAvailabilityCalendarIdentifier_CalendarId(calendarId);
        if (availabilityCalendarById == null)
            throw new  NotFoundException("availability calendar not found: " + calendarId);

        if(!Objects.equals(profile.getHandymanIdentifier().getHandymanId(), availabilityCalendarById.getHandymanIdentifier().getHandymanId()))
            throw new NotFoundException("Calendar: " + calendarId + " not found in the handyman: " + handymanId);

        availabilityCalendarRepository.delete(availabilityCalendarById);
    }
}