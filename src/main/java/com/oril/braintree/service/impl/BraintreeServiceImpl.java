package com.oril.braintree.service.impl;

import com.braintreegateway.*;
import com.oril.braintree.model.BraintreeRequestDTO;
import com.oril.braintree.model.BraintreeResponseDTO;
import com.oril.braintree.service.BraintreeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BraintreeServiceImpl implements BraintreeService {

    private final Logger logger = LoggerFactory.getLogger(BraintreeServiceImpl.class);

    private BraintreeGateway braintreeGateway;

    @Value("${braintree.merchantId}")
    private String merchantId;

    @Value("${braintree.publicKey}")
    private String publicKey;

    @Value("${braintree.privateKey}")
    private String privateKey;

    @PostConstruct
    public void init() {
        braintreeGateway = new BraintreeGateway(Environment.SANDBOX, merchantId, publicKey, privateKey);
    }

    @Override
    public BraintreeResponseDTO generateClientToken() {
        String clientToken = braintreeGateway.clientToken().generate();
        return new BraintreeResponseDTO(clientToken);
    }

    @Override
    public Transaction processTransaction(BraintreeRequestDTO braintreeRequestDTO) {
        TransactionRequest transactionRequest = buildTransactionRequest(braintreeRequestDTO);
        Result<Transaction> result = braintreeGateway.transaction().sale(transactionRequest);

        if (result.isSuccess()) {
            Transaction transaction = result.getTarget();
            logger.info("Successfully created transaction with Transaction ID: {}", transaction.getId());
            return transaction;
        } else {
            ValidationErrors errors = result.getErrors();
            validationError(errors);
            return null;
        }
    }

    private TransactionRequest buildTransactionRequest(BraintreeRequestDTO braintreeRequestDTO) {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.amount(new BigDecimal(braintreeRequestDTO.getPrice()));
        transactionRequest.paymentMethodNonce(braintreeRequestDTO.getPaymentToken());

        CustomerRequest customerRequest = transactionRequest.customer();
        customerRequest.email(braintreeRequestDTO.getEmail());
        customerRequest.firstName(braintreeRequestDTO.getFirstName());
        customerRequest.lastName(braintreeRequestDTO.getLastName());

        TransactionOptionsRequest options = transactionRequest.options();
        options.submitForSettlement(true);
        options.done();
        return transactionRequest;
    }

    private void validationError(ValidationErrors errors) {
        List<ValidationError> error = errors.getAllDeepValidationErrors();
        for (ValidationError er : error) {
            logger.error("error code : " + er.getCode() + " message  : " + er.getMessage());
        }
    }

}
