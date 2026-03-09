package com.konate.classactivity.paymentContext.DataLayer;


import com.konate.classactivity.Exceptions.InvoiceDomainException;
import com.konate.classactivity.bookingContext.DataLayer.JobIdentifier;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Invoice {

    @Id // makes it a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generation strategy
    private Integer id;

    @Embedded
    private InvoiceIdentifier invoiceIdentifier;

    @Column(name = "issue_date")
    private LocalDateTime issueDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_status")
    private InvoiceStatus invoiceStatus;


    @Column(name = "notes")
    private String notes;

    @Embedded
    private TaxCalculation taxCalculation;
    @Embedded
    private InvoiceTotal invoiceTotal;
    @Embedded
    private PlatformFee platformFee;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "invoice_line_items",
            joinColumns = @JoinColumn(name = "invoice_number", referencedColumnName = "invoice_number")
    )
    private List<LineItem> lineItems;

    @Embedded
    private JobIdentifier jobIdentifier;


    // ── Business Rules ──────────────────────────────────────────────

    public void markAsPaid() {
        if (this.invoiceStatus == InvoiceStatus.CANCELLED)
            throw new InvoiceDomainException("Cannot mark a cancelled invoice as paid.");
        if (this.invoiceStatus == InvoiceStatus.PAID)
            throw new InvoiceDomainException("Invoice is already paid.");
        this.invoiceStatus = InvoiceStatus.PAID;
    }


}
