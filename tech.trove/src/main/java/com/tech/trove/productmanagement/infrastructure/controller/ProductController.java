package com.tech.trove.productmanagement.infrastructure.controller;


import com.tech.trove.productmanagement.application.dto.in.ProductRequestDto;
import com.tech.trove.productmanagement.application.dto.in.ProductUpdateRequestDto;
import com.tech.trove.productmanagement.application.dto.out.ProductChannelDto;
import com.tech.trove.productmanagement.application.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The type Product controller.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    /**
     * Create product product channel dto.
     *
     * @param productRequestDto the product request dto
     * @return the product channel dto
     */
    @Operation(summary = "Create new product", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product create successfully."),

    })
    @PostMapping(value = "product", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductChannelDto createProduct(@RequestBody @Valid final ProductRequestDto productRequestDto) {
        return productService.saveProduct(productRequestDto);
    }

    /**
     * Gets product by id.
     *
     * @param productId the product id
     * @return the product by id
     */
    @Operation(summary = "Find product by productId", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found successfully."),

    })
    @GetMapping(value = "product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductChannelDto getProductById(@PathVariable  final String productId) {
        return productService.findProductById(productId);
    }

    /**
     * Update product product channel dto.
     *
     * @param productUpdateRequestDto the product update request dto
     * @param productId               the product id
     * @return the product channel dto
     */
    @Operation(summary = "Update product", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product update successfully."),

    })
    @PutMapping(value = "product/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductChannelDto updateProduct(@RequestBody @Valid final ProductUpdateRequestDto productUpdateRequestDto,
                                           @PathVariable final String productId) {
        return productService.updateProduct(productUpdateRequestDto, productId);
    }

    /**
     * Remove product by product id.
     *
     * @param productId the product id
     */
    @Operation(summary = "Remove product by productId", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product remove successfully."),

    })
    @DeleteMapping(value = "product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProductByProductId(@PathVariable final String productId) {
         productService.removeProductByProductId(productId);
    }
}
