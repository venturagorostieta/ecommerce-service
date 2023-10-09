package com.tech.trove.ordermanagement.application.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Item channel dto.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemChannelDto {


    private String productId;

    private Integer count;

}
