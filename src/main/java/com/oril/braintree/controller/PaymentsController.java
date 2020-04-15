package com.oril.braintree.controller;

import com.braintreegateway.Transaction;
import com.oril.braintree.model.BraintreeRequestDTO;
import com.oril.braintree.model.BraintreeResponseDTO;
import com.oril.braintree.service.BraintreeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentsController {

    private final BraintreeService braintreeService;

    @GetMapping(value = "/braintree")
    public BraintreeResponseDTO getBraintreeClientToken() {
        return braintreeService.generateClientToken();
    }

    @PostMapping(value = "/braintree")
    public Transaction processBraintreeTransaction(@RequestBody BraintreeRequestDTO braintreeRequestDTO) {
        return braintreeService.processTransaction(braintreeRequestDTO);
    }

}
