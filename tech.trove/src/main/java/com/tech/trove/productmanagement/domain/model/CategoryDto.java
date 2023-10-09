package com.tech.trove.productmanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Category dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String id;

    private String categoryId;

    private String name;
}
