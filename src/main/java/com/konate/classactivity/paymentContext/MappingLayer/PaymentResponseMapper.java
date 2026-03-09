package com.konate.classactivity.paymentContext.MappingLayer;

import com.konate.classactivity.paymentContext.DataLayer.Invoice;
import com.konate.classactivity.paymentContext.PresentationLayer.PaymentResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentResponseMapper {
    @Mappings({
            @Mapping(source = "invoiceIdentifier.invoiceNumber", target = "invoiceIdentifier"),
            @Mapping(source = "jobIdentifier.jobId", target = "jobIdentifier"),
            @Mapping(source = "issueDate", target = "issueDate"),
            @Mapping(source = "dueDate", target = "dueDate"),
            @Mapping(source = "invoiceStatus", target = "invoiceStatus"),
            @Mapping(source = "notes", target = "notes"),
            @Mapping(source = "taxCalculation", target = "taxCalculation"),
            @Mapping(source = "invoiceTotal", target = "invoiceTotal"),
            @Mapping(source = "platformFee", target = "platformFee"),
            @Mapping(source = "lineItems", target = "lineItems")
    })
    PaymentResponseModel toResponseModel(Invoice invoice);

    List<PaymentResponseModel> toResponseModelList(List<Invoice> invoices);
}
