package com.konate.classactivity.paymentContext.PresentationLayer;

import com.konate.classactivity.paymentContext.BusinessLayer.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseModel>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<PaymentResponseModel> getOne(@PathVariable String invoiceId){
        return  new ResponseEntity<>(service.getOne(invoiceId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentResponseModel> createPayment(@RequestBody PaymentRequestModel requestModel){
        return new  ResponseEntity<>(service.create(requestModel), HttpStatus.CREATED);
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<PaymentResponseModel> updatePayment(@PathVariable String invoiceId, @RequestBody PaymentRequestModel requestModel){
        return new  ResponseEntity<>(service.update(invoiceId, requestModel), HttpStatus.OK);
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<Void> deletePayment(@PathVariable String invoiceId){
        service.delete(invoiceId);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
