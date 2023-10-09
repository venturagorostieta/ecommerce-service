package com.tech.trove.ordermanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Item dto.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private String id;

    private String productId;

    private int count;

}
