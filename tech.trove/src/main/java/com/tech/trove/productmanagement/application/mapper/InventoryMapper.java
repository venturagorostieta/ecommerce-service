package com.tech.trove.productmanagement.application.mapper;

import com.tech.trove.productmanagement.application.dto.in.InventoryRequestDto;
import com.tech.trove.productmanagement.application.dto.out.InventoryChannelDto;
import com.tech.trove.productmanagement.domain.model.InventoryDto;
import com.tech.trove.productmanagement.infrastructure.database.mongodb.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The interface Inventory mapper.
 */
@Mapper
public interface InventoryMapper {

    /**
     * The constant INSTANCE.
     */
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    /**
     * To inventory dto inventory dto.
     *
     * @param inventoryRequestDto the inventory request dto
     * @return the inventory dto
     */
    InventoryDto toInventoryDto (InventoryRequestDto inventoryRequestDto);

    /**
     * To inventory document inventory.
     *
     * @param inventoryDto the inventory dto
     * @return the inventory
     */
    Inventory toInventoryDocument(InventoryDto inventoryDto);

    /**
     * To inventory channel dto inventory channel dto.
     *
     * @param inventory the inventory
     * @return the inventory channel dto
     */
    InventoryChannelDto toInventoryChannelDto(Inventory inventory);

}
