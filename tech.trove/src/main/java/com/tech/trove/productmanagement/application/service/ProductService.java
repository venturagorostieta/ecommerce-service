package com.tech.trove.productmanagement.application.service;


import com.tech.trove.productmanagement.application.dto.in.ProductRequestDto;
import com.tech.trove.productmanagement.application.dto.in.ProductUpdateRequestDto;
import com.tech.trove.productmanagement.application.dto.out.ProductChannelDto;

public interface ProductService {

    ProductChannelDto saveProduct(ProductRequestDto productRequestDto);

    ProductChannelDto findProductById(String productId);

    ProductChannelDto updateProduct(ProductUpdateRequestDto productUpdateRequestDto, String productId);

    void removeProductByProductId(String productId);

}
