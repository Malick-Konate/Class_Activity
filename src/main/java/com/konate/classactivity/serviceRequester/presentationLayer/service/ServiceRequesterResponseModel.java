package com.konate.classactivity.serviceRequester.presentationLayer.service;

import com.konate.classactivity.serviceRequester.DataAccessLayer.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequesterResponseModel {
    String serviceRequestId;
//    String customerName;
    UrgencyOfRequest urgency;
    RequestStatus status;
    Date preferredDate;
    Date createdAt;
    String customerEmail;
//    CustomerPhoneNumber customerPhone;
//    CustomerAddress customerAddress;
    ProblemDescription problemDescription;
    SearchCriteria searchCriteria;
    Preferences preferences;
}
