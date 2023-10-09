package com.tech.trove.productmanagement.infrastructure.database.mongodb.repository;

import com.tech.trove.productmanagement.infrastructure.database.mongodb.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * The interface Product repository.
 */
public interface ProductRepository extends PagingAndSortingRepository<Product,String> {

    /**
     * Find by product id optional.
     *
     * @param productId the product id
     * @return the optional
     */
    Optional<Product> findByProductId(String productId);

    /**
     * Delete by product id.
     *
     * @param productId the product id
     */
    void deleteByProductId(String productId);
}
