package com.tech.trove.productmanagement.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * The type Inventory request dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestDto {

    @NotNull
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]{1,19}(([ ][A-ZÁÉÍÓÚÑ][a-záéíóúñ]{1,19}){0,5})$", message = "The 'name category' field is not valid")
    private String productId;

    private Integer stock;

}
