package com.konate.classactivity.paymentContext.PresentationLayer;

import com.konate.classactivity.paymentContext.DataLayer.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseModel {
    String invoiceIdentifier;
    String jobIdentifier;
    LocalDateTime issueDate;

    LocalDateTime dueDate;

    InvoiceStatus invoiceStatus;


    String notes;

    TaxCalculation taxCalculation;
    InvoiceTotal invoiceTotal;
    PlatformFee platformFee;

    List<LineItem> lineItems;
}
