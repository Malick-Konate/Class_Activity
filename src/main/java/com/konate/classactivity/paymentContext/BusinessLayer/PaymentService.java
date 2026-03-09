package com.konate.classactivity.paymentContext.BusinessLayer;

import com.konate.classactivity.paymentContext.PresentationLayer.PaymentRequestModel;
import com.konate.classactivity.paymentContext.PresentationLayer.PaymentResponseModel;

import java.util.List;

public interface PaymentService {
    List<PaymentResponseModel> getAll();

    PaymentResponseModel getOne(String invoiceID);

    PaymentResponseModel create(PaymentRequestModel requestModel);

    PaymentResponseModel update(String invoiceID, PaymentRequestModel requestModel);

    void delete(String InvoiceID);
}
