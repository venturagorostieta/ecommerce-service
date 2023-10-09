package com.tech.trove.usermanagement.application.dto;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * The type Role dto.
 */
@Data
@Builder
public class RoleDto implements Serializable {

    private String name;

    private List<String> domainOperations;

}
