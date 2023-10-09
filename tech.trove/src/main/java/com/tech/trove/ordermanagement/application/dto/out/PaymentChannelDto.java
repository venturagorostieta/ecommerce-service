package com.tech.trove.ordermanagement.application.dto.out;

import com.tech.trove.ordermanagement.domain.model.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Payment channel dto.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentChannelDto {


    private String paymentId;

    private Integer authCode;

    private Double total;

    private CardType cardType;

    private String lastFourDigits;

}
