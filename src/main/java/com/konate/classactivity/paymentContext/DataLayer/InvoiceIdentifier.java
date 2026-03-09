package com.konate.classactivity.paymentContext.DataLayer;


import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;

@Embeddable
@Getter
public class InvoiceIdentifier {
    @Column(name = "invoice_number")
    private String invoiceNumber;
    public InvoiceIdentifier() {
        this.invoiceNumber = UUID.randomUUID().toString();
    }
    public InvoiceIdentifier(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}


