package com.tech.trove.productmanagement.application.service;


import com.tech.trove.productmanagement.application.dto.in.CategoryRequestDto;
import com.tech.trove.productmanagement.application.dto.out.CategoryChannelDto;

public interface CategoryService {

    CategoryChannelDto saveCategory(CategoryRequestDto categoryRequestDto);

    CategoryChannelDto findCategoryById(String productId);

}
