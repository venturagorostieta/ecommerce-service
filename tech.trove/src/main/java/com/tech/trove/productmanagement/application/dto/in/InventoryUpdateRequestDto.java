package com.tech.trove.productmanagement.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * The type Inventory update request dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryUpdateRequestDto {

    private Integer stock;

}
