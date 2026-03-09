package com.konate.classactivity.HandymanProfile.DataLayer;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HandymanRepository extends JpaRepository<HandymanProfile, Integer> {


    boolean existsByEmail(String email);

//    List<HandymanProfile> findByHandymanIdentifier_HandymanId(String handymanId);

    HandymanProfile findByHandymanIdentifier_HandymanId(String handymanId);

    HandymanProfile findAllByEmail(String email);

    List<HandymanProfile> findByIsActiveTrue();

    List<HandymanProfile> findAllByWorkZoneCityAndIsActive(String workZone_city, Boolean isActive);

    List<HandymanProfile> findHandymanProfileBySkillSets(List<SkillSet> skillSets);
}
