package com.tech.trove.usermanagement.application.dto.in;

import com.tech.trove.usermanagement.common.constant.DomainType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Role request dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDto {

    private String name;

    private DomainType domain;

    private List<String> domainOperations;
}
