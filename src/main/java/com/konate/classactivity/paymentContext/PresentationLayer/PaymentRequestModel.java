package com.konate.classactivity.paymentContext.PresentationLayer;

import com.konate.classactivity.paymentContext.DataLayer.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestModel {
//    String jobIdentifier;
//
//    LocalDateTime dueDate;
//
//    String notes;
//
//
//    List<LineItem> lineItems;
//
//
//    //tax calculation value object
//    Double taxRate;
//    String taxType;
//    String jurisdiction;

    LocalDateTime issueDate;

    LocalDateTime dueDate;

    InvoiceStatus invoiceStatus;


    String notes;

    TaxCalculation taxCalculation;
    InvoiceTotal invoiceTotal;
    PlatformFee platformFee;

    List<LineItem> lineItems;
    String jobIdentifier;


}
