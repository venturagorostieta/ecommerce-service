package com.tech.trove.productmanagement.application.service;

import com.tech.trove.productmanagement.application.dto.in.InventoryRequestDto;
import com.tech.trove.productmanagement.application.dto.in.InventoryUpdateRequestDto;
import com.tech.trove.productmanagement.application.dto.out.InventoryChannelDto;


public interface InventoryService {

    InventoryChannelDto saveInventory(InventoryRequestDto inventoryRequestDto);

    InventoryChannelDto findInventoryById(String productId);

    InventoryChannelDto updateInventoryByProductId(InventoryUpdateRequestDto inventoryUpdateRequestDto,
                                                   String productId);

    void removeInventoryById(String productId);

}
