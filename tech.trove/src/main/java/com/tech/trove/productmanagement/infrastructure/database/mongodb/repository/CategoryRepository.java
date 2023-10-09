package com.tech.trove.productmanagement.infrastructure.database.mongodb.repository;

import com.tech.trove.productmanagement.infrastructure.database.mongodb.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category,String> {

}
