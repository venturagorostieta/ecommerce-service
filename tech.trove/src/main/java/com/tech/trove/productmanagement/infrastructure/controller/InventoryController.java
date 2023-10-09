package com.tech.trove.productmanagement.infrastructure.controller;


import com.tech.trove.productmanagement.application.dto.in.InventoryRequestDto;
import com.tech.trove.productmanagement.application.dto.in.InventoryUpdateRequestDto;
import com.tech.trove.productmanagement.application.dto.out.InventoryChannelDto;
import com.tech.trove.productmanagement.application.service.InventoryService;
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
 * The type Inventory controller.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryController {


    private final InventoryService inventoryService;

    /**
     * Create inventory inventory channel dto.
     *
     * @param inventoryRequestDto the inventory request dto
     * @return the inventory channel dto
     */
    @Operation(summary = "Create new Inventory", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventory create successfully."),

    })
    @PostMapping(value = "product/inventory", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryChannelDto createInventory(@RequestBody @Valid final InventoryRequestDto inventoryRequestDto) {
        return inventoryService.saveInventory(inventoryRequestDto);
    }


    /**
     * Gets inventory by id.
     *
     * @param productId the product id
     * @return the inventory by id
     */
    @Operation(summary = "Find inventory by id", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventory found successfully."),

    })
    @GetMapping(value = "product/inventory/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryChannelDto getInventoryById(@PathVariable  final String productId) {

        return inventoryService.findInventoryById(productId);
    }

    /**
     * Update inventory by product id inventory channel dto.
     *
     * @param InventoryUpdateRequestDto the inventory update request dto
     * @param productId                 the product id
     * @return the inventory channel dto
     */
    @Operation(summary = "Update Inventory", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory Update successfully."),

    })
    @PutMapping(value = "product/inventory/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public InventoryChannelDto updateInventoryByProductId(
            @RequestBody @Valid final InventoryUpdateRequestDto InventoryUpdateRequestDto,
            @PathVariable final String productId) {
        return inventoryService.updateInventoryByProductId(InventoryUpdateRequestDto, productId);
    }

    /**
     * Remove inventory by product id.
     *
     * @param productId the product id
     */
    @Operation(summary = "Remove inventory by productId", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inventory remove successfully."),

    })
    @DeleteMapping(value = "product/inventory/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeInventoryByProductId(@PathVariable final String productId) {

        inventoryService.removeInventoryById(productId);
    }

}
