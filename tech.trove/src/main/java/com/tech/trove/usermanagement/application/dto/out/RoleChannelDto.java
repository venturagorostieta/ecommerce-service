package com.tech.trove.usermanagement.application.dto.out;

import com.tech.trove.usermanagement.common.constant.DomainType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Role channel dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleChannelDto {

    private String name;

    private DomainType domain;

    private List<String> domainOperations;

    private LocalDateTime createdAt;

}
