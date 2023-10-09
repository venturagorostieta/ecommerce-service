package com.tech.trove.usermanagement.infrastructure.database.mongodb;

import com.tech.trove.usermanagement.common.constant.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


/**
 * The type User.
 */
@Getter
@Setter
@Builder
@Document("user")
@CompoundIndexes({
        @CompoundIndex(name = "userId_1_email_1", def = "{'userId': 1, 'email': 1}, {'unique': true}")
})
public class User {

    @Id
    private String userId;

    private String name;

    private String lastName;

    @Indexed(unique = true)
    private String email;

    private UserStatus status;

    @Indexed
    private LocalDateTime createdAt;

}
