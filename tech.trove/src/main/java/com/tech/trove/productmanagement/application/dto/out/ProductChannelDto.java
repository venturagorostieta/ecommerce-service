package com.tech.trove.productmanagement.application.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Product channel dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductChannelDto implements Serializable {

    private String id;

    private String productId;

    private String name;

    private double price;

    private String categoryId;

}
