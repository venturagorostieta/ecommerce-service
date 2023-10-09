package com.tech.trove.usermanagement.domain.model;

import lombok.Builder;
import lombok.Data;

/**
 * The type User dto.
 */
@Builder
@Data
public class UserDto {

    private String id;
    private String name;
    private String email;

}
