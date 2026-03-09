//package com.konate.classactivity.HandymanProfile.DataMappingLayer;
//
//
//import com.konate.classactivity.HandymanProfile.DataLayer.BackgroundCheck;
//import com.konate.classactivity.HandymanProfile.PresentationLayer.BackgroundCheckResponseModel;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface BackgroundCheckMapper {
//
//    @Mapping(source = "handymanIdentifier.handymanId", target = "handymanIdentifier") // If needed in the DTO
//    BackgroundCheckResponseModel entityToResponseModel(BackgroundCheck entity);
//
//    List<BackgroundCheckResponseModel> entityListToResponseModelList(List<BackgroundCheck> entities);
//}