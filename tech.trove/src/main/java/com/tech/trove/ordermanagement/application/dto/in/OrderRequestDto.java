package com.tech.trove.ordermanagement.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Order request dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private String userId;

    private List<ItemRequestDto> items;

    private PaymentRequestlDto paymentRequestlDto;

}
