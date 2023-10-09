package com.tech.trove.productmanagement.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * The type Product request dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    @NotNull
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]{1,19}(([ ][A-ZÁÉÍÓÚÑ][a-záéíóúñ]{1,19}){0,5})$", message = "The 'name' field is not valid")
    private String name;

    @NotNull
    private Double price;

    @NotNull
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]{1,19}(([ ][A-ZÁÉÍÓÚÑ][a-záéíóúñ]{1,19}){0,5})$", message = "The 'categoryId' field is not valid")
    private String categoryId;

    private String productId;

}
