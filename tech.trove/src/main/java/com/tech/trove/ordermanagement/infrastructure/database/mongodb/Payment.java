package com.tech.trove.ordermanagement.infrastructure.database.mongodb;

import com.tech.trove.ordermanagement.domain.model.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * The type Payment.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    private String id;

    @Indexed
    private String paymentId;

    private Integer authCode;

    private Double total;

    private CardType cardType;

    private String lastFourDigits;

}
