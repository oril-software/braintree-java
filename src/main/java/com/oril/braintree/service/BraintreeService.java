package com.oril.braintree.service;

import com.braintreegateway.Transaction;
import com.oril.braintree.model.BraintreeRequestDTO;
import com.oril.braintree.model.BraintreeResponseDTO;

public interface BraintreeService {

    /**
     * Generates Braintree client token for UI
     *
     * @return dto with clientToken
     */
    BraintreeResponseDTO generateClientToken();

    /**
     * Processes braintree transaction
     *
     * @param braintreeRequestDTO dto with needed data
     * @return transaction result
     */
    Transaction processTransaction(BraintreeRequestDTO braintreeRequestDTO);
}
