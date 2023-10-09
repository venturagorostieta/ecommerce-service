package com.tech.trove.productmanagement.application.mapper;

import com.tech.trove.productmanagement.application.dto.in.CategoryRequestDto;
import com.tech.trove.productmanagement.application.dto.out.CategoryChannelDto;
import com.tech.trove.productmanagement.domain.model.CategoryDto;
import com.tech.trove.productmanagement.infrastructure.database.mongodb.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The interface Category mapper.
 */
@Mapper
public interface CategoryMapper {

    /**
     * The constant INSTANCE.
     */
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    /**
     * To category dto category dto.
     *
     * @param categoryRequestDto the category request dto
     * @return the category dto
     */
    CategoryDto toCategoryDto (CategoryRequestDto categoryRequestDto);

    /**
     * To category document category.
     *
     * @param categoryDto the category dto
     * @return the category
     */
    Category toCategoryDocument(CategoryDto categoryDto);

    /**
     * To category channel dto category channel dto.
     *
     * @param category the category
     * @return the category channel dto
     */
    CategoryChannelDto toCategoryChannelDto(Category category);

}
