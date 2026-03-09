package com.konate.classactivity.paymentContext.BusinessLayer;

import com.konate.classactivity.Exceptions.InvalidInputException;
import com.konate.classactivity.Exceptions.NotFoundException;
import com.konate.classactivity.bookingContext.DataLayer.JobIdentifier;
import com.konate.classactivity.paymentContext.DataLayer.Invoice;
import com.konate.classactivity.paymentContext.DataLayer.InvoiceIdentifier;
import com.konate.classactivity.paymentContext.DataLayer.PaymentRepository;
import com.konate.classactivity.paymentContext.MappingLayer.PaymentRequestMapper;
import com.konate.classactivity.paymentContext.MappingLayer.PaymentResponseMapper;
import com.konate.classactivity.paymentContext.PresentationLayer.PaymentRequestModel;
import com.konate.classactivity.paymentContext.PresentationLayer.PaymentResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository repository;
    private final PaymentRequestMapper requestMapper;
    private final PaymentResponseMapper responseMapper;

    public PaymentServiceImpl(PaymentRepository repository, PaymentRequestMapper requestMapper, PaymentResponseMapper responseMapper) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<PaymentResponseModel> getAll() {
        List<Invoice> payments = repository.findAll();
        return responseMapper.toResponseModelList(payments);
    }

    @Override
    public PaymentResponseModel getOne(String invoiceID) {
        Invoice payment = repository.findAllByInvoiceIdentifier_InvoiceNumber(invoiceID);
        if (payment == null) {
            throw new NotFoundException("Sorry, the invoice have not been found at the given id: " + invoiceID);
        }
        return responseMapper.toResponseModel(payment);
    }

    @Override
    public PaymentResponseModel create(PaymentRequestModel requestModel) {
        if (requestModel == null) {
            throw new InvalidInputException("the request cannot be null: " + requestModel);
        }
        Invoice found = repository.findAllByJobIdentifier_JobId(requestModel.getJobIdentifier());
        if (found == null) {
            throw new NotFoundException("the job id you have enter is invalid: " + requestModel.getJobIdentifier());
        }
        Invoice newInvoice = requestMapper.toInvoice(
                requestModel,
                new InvoiceIdentifier(),
                new JobIdentifier(requestModel.getJobIdentifier())
        );
        Invoice saved = repository.save(newInvoice);
        return responseMapper.toResponseModel(saved);
    }

    @Override
    public PaymentResponseModel update(String invoiceID, PaymentRequestModel requestModel) {
        Invoice payment = repository.findAllByInvoiceIdentifier_InvoiceNumber(invoiceID);
        if (payment == null) {
            throw new NotFoundException("Sorry, the invoice have not been found at the given id: " + invoiceID);
        }
        payment.setDueDate(requestModel.getDueDate());
        payment.setIssueDate(requestModel.getIssueDate());
        payment.setInvoiceStatus(requestModel.getInvoiceStatus());
        payment.setNotes(requestModel.getNotes());
        payment.setTaxCalculation(requestModel.getTaxCalculation());
        payment.setInvoiceTotal(requestModel.getInvoiceTotal());
        payment.setPlatformFee(requestModel.getPlatformFee());
        payment.setLineItems(requestModel.getLineItems());

        Invoice saved = repository.save(payment);
        return responseMapper.toResponseModel(saved);
    }

    @Override
    public void delete(String InvoiceID) {
        Invoice payment = repository.findAllByInvoiceIdentifier_InvoiceNumber(InvoiceID);
        if (payment == null) {
            throw new NotFoundException("Sorry, the invoice have not been found at the given id: " + InvoiceID);
        }
        repository.delete(payment);
    }
}
