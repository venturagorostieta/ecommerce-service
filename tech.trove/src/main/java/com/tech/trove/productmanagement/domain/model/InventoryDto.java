package com.tech.trove.productmanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Inventory dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

    private String id;

    private String productId;

    private Integer stock;
}
