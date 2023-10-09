package com.tech.trove.usermanagement.infrastructure.database.mongodb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


/**
 * The type User role.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("userRole")
@CompoundIndexes({
        @CompoundIndex(name = "userId_1_role_1", def = "{'userId': 1, 'role': 1}, {'unique': true}")
})
public class UserRole {

    @Id
    private String id;

    @Indexed
    private String userId;

    private String role;

    private boolean active;

    @Indexed
    private LocalDateTime createdAt;

}
