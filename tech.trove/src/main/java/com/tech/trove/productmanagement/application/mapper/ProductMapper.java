package com.tech.trove.productmanagement.application.mapper;

import com.tech.trove.productmanagement.application.dto.in.ProductRequestDto;
import com.tech.trove.productmanagement.application.dto.out.ProductChannelDto;
import com.tech.trove.productmanagement.domain.model.ProductDto;
import com.tech.trove.productmanagement.infrastructure.database.mongodb.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The interface Product mapper.
 */
@Mapper
public interface ProductMapper {

    /**
     * The constant INSTANCE.
     */
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    /**
     * To product dto product dto.
     *
     * @param productRequestDto the product request dto
     * @return the product dto
     */
    ProductDto toProductDto (ProductRequestDto productRequestDto);

    /**
     * To product document product.
     *
     * @param productDto the product dto
     * @return the product
     */
    Product toProductDocument(ProductDto productDto);

    /**
     * To product channel dto product channel dto.
     *
     * @param product the product
     * @return the product channel dto
     */
    ProductChannelDto toProductChannelDto(Product product);

}
