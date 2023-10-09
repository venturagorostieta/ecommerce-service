package com.tech.trove.productmanagement.infrastructure.database.mongodb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Product.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    private String id;

    @Indexed(unique = true)
    private String productId;

    private String name;

    private double price;

    @Indexed
    private String categoryId;

}
