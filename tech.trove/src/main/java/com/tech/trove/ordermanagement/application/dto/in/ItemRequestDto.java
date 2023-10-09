package com.tech.trove.ordermanagement.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Item request dto.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDto {


    private String productId;

    private Integer count;

}
