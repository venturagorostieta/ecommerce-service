package com.tech.trove.usermanagement.infrastructure.database.mongodb.repository;

import com.tech.trove.usermanagement.infrastructure.database.mongodb.UserRole;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;


/**
 * The interface User role repository.
 */
@Qualifier("userRoleRepository")
public interface UserRoleRepository extends MongoRepository<UserRole, String> {

    /**
     * Find all by user id optional.
     *
     * @param userId the user id
     * @return the optional
     */
    Optional<List<UserRole>> findAllByUserId(String userId);

    /**
     * Find by user id and active optional.
     *
     * @param userId the user id
     * @param active the active
     * @return the optional
     */
    @Query("{userId : ?0, active : ?1}")
    Optional<UserRole> findByUserIdAndActive(String userId, boolean active);

    /**
     * Find by user id and role optional.
     *
     * @param userId the user id
     * @param role   the role
     * @return the optional
     */
    @Query("{userId : ?0, role : ?1}")
    Optional<UserRole> findByUserIdAndRole(String userId, String role);

    /**
     * Delete all by user id.
     *
     * @param userId the user id
     */
    void deleteAllByUserId(String userId);

    /**
     * Find all by role optional.
     *
     * @param role the role
     * @return the optional
     */
    Optional<List<UserRole>> findAllByRole(String role);

    /**
     * Delete all by role.
     *
     * @param role the role
     */
    void deleteAllByRole(String role);

    /**
     * Delete all by user id and role in.
     *
     * @param userId   the user id
     * @param oldRoles the old roles
     */
    void deleteAllByUserIdAndRoleIn(String userId, List<String> oldRoles);

    /**
     * Find by role optional.
     *
     * @param role the role
     * @return the optional
     */
    Optional<UserRole> findByRole(String role);

}