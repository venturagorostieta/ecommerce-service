package com.tech.trove.usermanagement.infrastructure.database.mongodb.repository;


import com.tech.trove.usermanagement.common.constant.UserStatus;
import com.tech.trove.usermanagement.infrastructure.database.mongodb.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Exists by user id and status boolean.
     *
     * @param userId the user id
     * @param status the status
     * @return the boolean
     */
    boolean existsByUserIdAndStatus(String userId, String status);

    /**
     * Find all by status optional.
     *
     * @param status the status
     * @return the optional
     */
    Optional<List<User>> findAllByStatus(String status);

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findByEmail(String email);

    /**
     * Find by user id and not equal status optional.
     *
     * @param userId the user id
     * @param status the status
     * @return the optional
     */
    @Query("{userId : ?0 , status : { $ne : ?1 } }")
    Optional<User> findByUserIdAndNotEqualStatus(String userId, UserStatus status);


}