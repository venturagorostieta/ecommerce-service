package com.tech.trove.ordermanagement.infrastructure.database.mongodb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * The type Item.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    private String id;

    @Indexed
    private String productId;

    private int count;

}
