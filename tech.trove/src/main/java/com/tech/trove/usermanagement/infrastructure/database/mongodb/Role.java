package com.tech.trove.usermanagement.infrastructure.database.mongodb;

import com.tech.trove.usermanagement.common.constant.DomainType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


/**
 * The type Role.
 */
@Getter
@Setter
@Builder
@Document("role")
public class Role {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private DomainType domain;

    private List<String> domainOperations;

    @Indexed
    private LocalDateTime createdAt;

}
