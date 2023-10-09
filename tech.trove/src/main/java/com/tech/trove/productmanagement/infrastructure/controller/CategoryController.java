package com.tech.trove.productmanagement.infrastructure.controller;


import com.tech.trove.productmanagement.application.dto.in.CategoryRequestDto;
import com.tech.trove.productmanagement.application.dto.out.CategoryChannelDto;
import com.tech.trove.productmanagement.application.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The type Category controller.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {


    private final CategoryService categoryService;

    /**
     * Create category category channel dto.
     *
     * @param categoryRequestDto the category request dto
     * @return the category channel dto
     */
    @Operation(summary = "Create new category", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category create successfully."),

    })
    @PostMapping(value = "product/category", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryChannelDto createCategory(@RequestBody @Valid final CategoryRequestDto categoryRequestDto) {

        return categoryService.saveCategory(categoryRequestDto);
    }

    /**
     * Gets category by id.
     *
     * @param categoryId the category id
     * @return the category by id
     */
    @Operation(summary = "Find category by id", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found successfully."),

    })
    @GetMapping(value = "product/category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CategoryChannelDto getCategoryById(@PathVariable  final String categoryId) {

        return categoryService.findCategoryById(categoryId);
    }
}
