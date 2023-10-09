package com.tech.trove.ordermanagement.application.dto.in;

import com.tech.trove.ordermanagement.domain.model.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Payment requestl dto.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestlDto {


    private String paymentId;

    private int authCode;

    private double total;

    private CardType cardType;

    private String lastFourDigits;

}
