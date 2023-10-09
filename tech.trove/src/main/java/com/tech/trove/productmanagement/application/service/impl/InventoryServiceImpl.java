package com.tech.trove.productmanagement.application.service.impl;

import com.tech.trove.infrastructure.web.exception.ResourceNotFoundException;
import com.tech.trove.productmanagement.application.dto.in.InventoryRequestDto;
import com.tech.trove.productmanagement.application.dto.in.InventoryUpdateRequestDto;
import com.tech.trove.productmanagement.application.dto.out.InventoryChannelDto;
import com.tech.trove.productmanagement.application.mapper.InventoryMapper;
import com.tech.trove.productmanagement.application.service.InventoryService;
import com.tech.trove.productmanagement.domain.model.InventoryDto;
import com.tech.trove.productmanagement.infrastructure.database.mongodb.Inventory;
import com.tech.trove.productmanagement.infrastructure.database.mongodb.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Inventory service.
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    /**
     * Save inventory inventory channel dto.
     *
     * @param inventoryRequestDto the inventory request dto
     * @return the inventory channel dto
     */
    @Override
    public InventoryChannelDto saveInventory(InventoryRequestDto inventoryRequestDto) {

        InventoryDto request = InventoryMapper.INSTANCE.toInventoryDto(inventoryRequestDto);
        Inventory response = inventoryRepository.save(InventoryMapper.INSTANCE.toInventoryDocument(request));
        return InventoryMapper.INSTANCE.toInventoryChannelDto(response);
    }

    /**
     * Find inventory by id inventory channel dto.
     *
     * @param productId the product id
     * @return the inventory channel dto
     */
    @Cacheable(value = "inventory", key = "#productId")
    @Override
    public InventoryChannelDto findInventoryById(String productId) {

        Optional<Inventory> response = inventoryRepository.findByProductId(productId);
        return InventoryMapper.INSTANCE.toInventoryChannelDto(
                response.orElseThrow( () -> new ResourceNotFoundException("Inventory not found.")));
    }

    /**
     * Update inventory by product id inventory channel dto.
     *
     * @param inventoryUpdateRequestDto the inventory update request dto
     * @param productId                 the product id
     * @return the inventory channel dto
     */
    @CachePut(value = "inventory", key = "#productId")
    @Override
    public InventoryChannelDto updateInventoryByProductId(InventoryUpdateRequestDto inventoryUpdateRequestDto,
                                                          String productId) {
        Inventory model = lookUpInventoryByProductId(productId);
        model.setStock(inventoryUpdateRequestDto.getStock());

        return InventoryMapper.INSTANCE.toInventoryChannelDto(inventoryRepository.save(model));
    }

    /**
     * Remove inventory by id.
     *
     * @param productId the product id
     */
    @CacheEvict(value = "inventory", key = "#productId")
    @Override
    public void removeInventoryById(String productId) {
        lookUpInventoryByProductId(productId);
        inventoryRepository.deleteByProductId(productId);
    }

    private Inventory lookUpInventoryByProductId(String productId) {
        return inventoryRepository.findByProductId(productId).
                orElseThrow(() -> new ResourceNotFoundException("Inventory not found."));
    }

}
