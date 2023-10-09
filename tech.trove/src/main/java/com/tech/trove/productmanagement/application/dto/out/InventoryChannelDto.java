package com.tech.trove.productmanagement.application.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Inventory channel dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryChannelDto implements Serializable {

    private String id;

    private String productId;

    private Integer stock;

}
