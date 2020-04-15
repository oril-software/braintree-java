package com.oril.braintree.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BraintreeRequestDTO {

    private Double price;
    private String paymentToken;
    private String email;
    private String firstName;
    private String lastName;

}
