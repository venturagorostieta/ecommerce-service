package com.tech.trove.ordermanagement.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Order update request dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequestDto {

    private List<ItemRequestDto> items;

    private PaymentRequestlDto paymentRequestlDto;

}
