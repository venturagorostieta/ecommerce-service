package com.tech.grove.productmanagement.utils

import com.tech.trove.productmanagement.application.dto.in.CategoryRequestDto
import com.tech.trove.productmanagement.application.dto.in.InventoryRequestDto
import com.tech.trove.productmanagement.application.dto.in.InventoryUpdateRequestDto
import com.tech.trove.productmanagement.application.dto.in.ProductRequestDto
import com.tech.trove.productmanagement.application.dto.in.ProductUpdateRequestDto
import com.tech.trove.productmanagement.application.dto.out.CategoryChannelDto
import com.tech.trove.productmanagement.application.dto.out.InventoryChannelDto
import com.tech.trove.productmanagement.application.dto.out.ProductChannelDto
import com.tech.trove.productmanagement.infrastructure.database.mongodb.Category
import com.tech.trove.productmanagement.infrastructure.database.mongodb.Inventory
import com.tech.trove.productmanagement.infrastructure.database.mongodb.Product

class TestUtils {

    static CategoryRequestDto buildCategoryRequestDto() {

        return CategoryRequestDto.builder()
                .name(TestUtilConstants.DEFAULT_CATEGORY_ID)
                .categoryId(TestUtilConstants.DEFAULT_CATEGORY_NAME)
        .build()
    }

    static CategoryChannelDto buildCategoryChannelDto() {

        return CategoryChannelDto.builder()
                .id(TestUtilConstants.DEFAULT_CATEGORY_DOCUMENT_ID)
                .categoryId(TestUtilConstants.DEFAULT_CATEGORY_ID)
                .name(TestUtilConstants.DEFAULT_CATEGORY_NAME)
                .build()
    }

    static Category buildCategory() {
        return Category.builder()
                .id(TestUtilConstants.DEFAULT_CATEGORY_DOCUMENT_ID)
                .categoryId(TestUtilConstants.DEFAULT_CATEGORY_ID)
                .name(TestUtilConstants.DEFAULT_CATEGORY_NAME)
        .build()
    }

    static InventoryRequestDto buildInventoryRequestDto() {
        return InventoryRequestDto.builder()
                .productId(TestUtilConstants.DEFAULT_PRODUCT_ID)
                .stock(TestUtilConstants.DEFAULT_STOCK)
        .build()
    }

    static Inventory buildInventory() {
        return Inventory.builder()
                .id(TestUtilConstants.DEFAULT_INVENTORY_DOCUMENT_ID)
                .productId(TestUtilConstants.DEFAULT_PRODUCT_ID)
                .stock(TestUtilConstants.DEFAULT_STOCK)
        .build()
    }

    static InventoryChannelDto buildInventoryChannelDto() {
        return InventoryChannelDto.builder()
                .id(TestUtilConstants.DEFAULT_INVENTORY_DOCUMENT_ID)
                .productId(TestUtilConstants.DEFAULT_PRODUCT_ID)
                .stock(TestUtilConstants.DEFAULT_STOCK)
        .build()
    }

    static InventoryUpdateRequestDto buildInventoryUpdateRequestDto() {
        return InventoryUpdateRequestDto.builder()
                .stock(TestUtilConstants.DEFAULT_STOCK)
        .build()
    }

    static ProductRequestDto buildProductRequestDto() {
        return ProductRequestDto.builder()
                .productId(TestUtilConstants.DEFAULT_PRODUCT_ID)
                .categoryId(TestUtilConstants.DEFAULT_CATEGORY_ID)
                .name(TestUtilConstants.DEFAULT_PRODUCT_NAME)
        .build()
    }

    static Product buildProduct() {
        return Product.builder()
                .id(TestUtilConstants.DEFAULT_PRODUCT_DOCUMENT_ID)
                .productId(TestUtilConstants.DEFAULT_PRODUCT_ID)
                .categoryId(TestUtilConstants.DEFAULT_CATEGORY_ID)
                .name(TestUtilConstants.DEFAULT_PRODUCT_NAME)
        .build()
    }

    static ProductChannelDto buildProductChannelDto() {
        return ProductChannelDto.builder()
                .id(TestUtilConstants.DEFAULT_PRODUCT_DOCUMENT_ID)
                .productId(TestUtilConstants.DEFAULT_PRODUCT_ID)
                .categoryId(TestUtilConstants.DEFAULT_CATEGORY_ID)
                .name(TestUtilConstants.DEFAULT_PRODUCT_NAME)
        .build()
    }

    static ProductUpdateRequestDto buildProductUpdateRequestDto() {
        return ProductUpdateRequestDto.builder()
        .categoryId(TestUtilConstants.DEFAULT_CATEGORY_ID)
        .price(TestUtilConstants.DEFAULT_PRICE)
        .build()
    }
}
