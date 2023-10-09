package com.tech.trove.productmanagement.application.service.impl;

import com.tech.trove.infrastructure.web.exception.ResourceNotFoundException;
import com.tech.trove.productmanagement.application.dto.in.CategoryRequestDto;
import com.tech.trove.productmanagement.application.dto.out.CategoryChannelDto;
import com.tech.trove.productmanagement.application.mapper.CategoryMapper;
import com.tech.trove.productmanagement.application.service.CategoryService;
import com.tech.trove.productmanagement.domain.model.CategoryDto;
import com.tech.trove.productmanagement.infrastructure.database.mongodb.Category;
import com.tech.trove.productmanagement.infrastructure.database.mongodb.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Category service.
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Save category category channel dto.
     *
     * @param categoryRequestDto the category request dto
     * @return the category channel dto
     */
    @Override
    public CategoryChannelDto saveCategory(CategoryRequestDto categoryRequestDto) {
        CategoryDto request = CategoryMapper.INSTANCE.toCategoryDto(categoryRequestDto);
        Category response = categoryRepository.save(CategoryMapper.INSTANCE.toCategoryDocument(request));
        return CategoryMapper.INSTANCE.toCategoryChannelDto(response);
    }

    /**
     * Find category by id category channel dto.
     *
     * @param productId the product id
     * @return the category channel dto
     */
    @Override
    public CategoryChannelDto findCategoryById(String productId) {
        Optional<Category> response = categoryRepository.findById(productId);

        return CategoryMapper.INSTANCE.toCategoryChannelDto(
                response.orElseThrow( () -> new ResourceNotFoundException("Category not found.")));
    }

}
