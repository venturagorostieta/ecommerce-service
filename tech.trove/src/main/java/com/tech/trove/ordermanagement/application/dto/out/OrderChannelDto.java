package com.tech.trove.ordermanagement.application.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Order channel dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderChannelDto {

    private String id;

    private String userId;

    private List<ItemChannelDto> items;

    private PaymentChannelDto paymentChannelDto;
}
