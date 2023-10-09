package com.tech.trove.ordermanagement.infrastructure.database.mongodb.repository;

import com.tech.trove.ordermanagement.infrastructure.database.mongodb.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order,String> {

}
