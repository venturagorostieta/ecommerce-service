package com.tech.trove.productmanagement.application.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Category channel dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryChannelDto {

    private String id;

    private String categoryId;

    private String name;

}
