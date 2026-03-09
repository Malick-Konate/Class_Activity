package com.konate.classactivity.paymentContext.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Invoice, Integer> {
    Invoice findAllByJobIdentifier_JobId(String jobIdentifierJobId);

    Invoice findAllByInvoiceIdentifier_InvoiceNumber(String invoiceIdentifierInvoiceNumber);
}
