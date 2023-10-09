package com.tech.trove.productmanagement.application.service.impl;


import com.tech.trove.infrastructure.web.exception.ResourceNotFoundException;
import com.tech.trove.productmanagement.application.dto.in.ProductRequestDto;
import com.tech.trove.productmanagement.application.dto.in.ProductUpdateRequestDto;
import com.tech.trove.productmanagement.application.dto.out.ProductChannelDto;
import com.tech.trove.productmanagement.application.mapper.ProductMapper;
import com.tech.trove.productmanagement.application.service.ProductService;
import com.tech.trove.productmanagement.domain.model.ProductDto;
import com.tech.trove.productmanagement.infrastructure.database.mongodb.Product;
import com.tech.trove.productmanagement.infrastructure.database.mongodb.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Product service.
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Save product product channel dto.
     *
     * @param productRequestDto the product request dto
     * @return the product channel dto
     */
    @Override
    public ProductChannelDto saveProduct(ProductRequestDto productRequestDto) {

        ProductDto dto = ProductMapper.INSTANCE.toProductDto(productRequestDto);
        return ProductMapper.INSTANCE.toProductChannelDto(
                productRepository.save(ProductMapper.INSTANCE.toProductDocument(dto))
        );
    }

    /**
     * Find product by id product channel dto.
     *
     * @param productId the product id
     * @return the product channel dto
     */
    @Cacheable(value = "products", key = "#productId")
    @Override
    public ProductChannelDto findProductById(String productId) {

        Optional<Product> product = productRepository.findByProductId(productId);
        return ProductMapper.INSTANCE.toProductChannelDto(
                product.orElseThrow( () -> new ResourceNotFoundException("Product not found.")));
    }

    /**
     * Update product product channel dto.
     *
     * @param productUpdateRequestDto the product update request dto
     * @param productId               the product id
     * @return the product channel dto
     */
    @CachePut(value = "products", key = "#productId")
    @Override
    public ProductChannelDto updateProduct(ProductUpdateRequestDto productUpdateRequestDto,
                                           String productId) {
        Product productModel = lookUpProductByProductId(productId);
        productModel.setCategoryId(productUpdateRequestDto.getCategoryId());
        productModel.setPrice(productUpdateRequestDto.getPrice());
        return ProductMapper.INSTANCE.toProductChannelDto(productRepository.save(productModel));
    }

    /**
     * Remove product by product id.
     *
     * @param productId the product id
     */
    @Override
    @CacheEvict(value = "products", key = "#productId")
    public void removeProductByProductId(String productId) {
        lookUpProductByProductId(productId);
        productRepository.deleteByProductId(productId);
    }

    private Product lookUpProductByProductId(String productId) {
        return productRepository.findByProductId(productId).
                orElseThrow(() -> new ResourceNotFoundException("Product not found."));
    }

}
