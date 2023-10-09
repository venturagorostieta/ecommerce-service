package com.tech.trove.ordermanagement.domain.model;

import com.tech.trove.ordermanagement.infrastructure.database.mongodb.Item;
import com.tech.trove.ordermanagement.infrastructure.database.mongodb.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * The type Order dto.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
public class OrderDto {

    private String id;

    private String userId;

    private List<ItemDto> items;

    private PaymentDto paymentDto;
}
