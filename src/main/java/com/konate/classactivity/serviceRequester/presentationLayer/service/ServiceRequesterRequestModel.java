package com.konate.classactivity.serviceRequester.presentationLayer.service;

import com.konate.classactivity.serviceRequester.DataAccessLayer.Preferences;
import com.konate.classactivity.serviceRequester.DataAccessLayer.ProblemDescription;
import com.konate.classactivity.serviceRequester.DataAccessLayer.SearchCriteria;
import com.konate.classactivity.serviceRequester.DataAccessLayer.UrgencyOfRequest;
import com.konate.classactivity.serviceRequester.DataAccessLayer.customer.CustomerAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequesterRequestModel {
    String customerEmail;
    Date preferredDate;
//    List<CustomerPhoneNumber> customerPhone;
//    CustomerAddress customerAddress;
    UrgencyOfRequest urgency;
    ProblemDescription problemDescription;
    SearchCriteria searchCriteria;
    Preferences preferences;

}
