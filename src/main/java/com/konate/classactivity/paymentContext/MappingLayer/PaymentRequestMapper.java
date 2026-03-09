package com.konate.classactivity.paymentContext.MappingLayer;

import com.konate.classactivity.bookingContext.DataLayer.JobIdentifier;
import com.konate.classactivity.paymentContext.DataLayer.Invoice;
import com.konate.classactivity.paymentContext.DataLayer.InvoiceIdentifier;
import com.konate.classactivity.paymentContext.PresentationLayer.PaymentRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PaymentRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(invoiceIdentifier)", target = "invoiceIdentifier"),
            @Mapping(expression = "java(jobIdentifier)", target = "jobIdentifier"),
            @Mapping(source = "requestModel.issueDate", target = "issueDate"),
            @Mapping(source = "requestModel.dueDate", target = "dueDate"),
            @Mapping(source = "requestModel.invoiceStatus", target = "invoiceStatus"),
            @Mapping(source = "requestModel.notes", target = "notes"),
            @Mapping(source = "requestModel.taxCalculation", target = "taxCalculation"),
            @Mapping(source = "requestModel.invoiceTotal", target = "invoiceTotal"),
            @Mapping(source = "requestModel.platformFee", target = "platformFee"),
            @Mapping(source = "requestModel.lineItems", target = "lineItems")



    })
    Invoice toInvoice(PaymentRequestModel requestModel,
                      InvoiceIdentifier invoiceIdentifier, JobIdentifier jobIdentifier);
}
