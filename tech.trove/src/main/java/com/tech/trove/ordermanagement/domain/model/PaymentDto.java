package com.tech.trove.ordermanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Payment dto.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private String id;

    private String paymentId;

    private int authCode;

    private double total;

    private CardType cardType;

    private String lastFourDigits;

}
