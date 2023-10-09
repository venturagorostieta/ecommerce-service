package com.tech.trove.productmanagement.infrastructure.database.mongodb.repository;

import com.tech.trove.productmanagement.infrastructure.database.mongodb.Inventory;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * The interface Inventory repository.
 */
public interface InventoryRepository extends PagingAndSortingRepository<Inventory,String> {

    /**
     * Find by product id optional.
     *
     * @param productId the product id
     * @return the optional
     */
    Optional<Inventory> findByProductId(String productId);

    /**
     * Delete by product id.
     *
     * @param productId the product id
     */
    void deleteByProductId(String productId);
}
