package com.tech.trove.usermanagement.infrastructure.database.mongodb.repository;


import com.tech.trove.usermanagement.infrastructure.database.mongodb.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;


/**
 * The interface Role repository.
 */
public interface RoleRepository extends MongoRepository<Role, String> {

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    @Query("{name : ?0}")
    Optional<Role> findByName(String name);

    /**
     * Find by domain optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<List<Role>> findByDomain(String name);

    /**
     * Delete by name int.
     *
     * @param role the role
     * @return the int
     */
    int deleteByName(String role);

}