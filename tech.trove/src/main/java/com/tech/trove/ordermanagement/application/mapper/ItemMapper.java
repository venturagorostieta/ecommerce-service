package com.tech.trove.ordermanagement.application.mapper;

import com.tech.trove.ordermanagement.application.dto.in.ItemRequestDto;
import com.tech.trove.ordermanagement.application.dto.out.ItemChannelDto;
import com.tech.trove.ordermanagement.infrastructure.database.mongodb.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

/**
 * The interface Item mapper.
 */
@Mapper
public interface ItemMapper {

    /**
     * To channel dto item channel dto.
     *
     * @param item the item
     * @return the item channel dto
     */
    @Named("toChannelDto")
    ItemChannelDto toChannelDto(Item item);

    /**
     * To channel dto list list.
     *
     * @param items the items
     * @return the list
     */
    @Named("toChannelDtoList")
    List<ItemChannelDto> toChannelDtoList(List<Item> items);


    /**
     * To item item.
     *
     * @param itemRequestDto the item request dto
     * @return the item
     */
    @Named("toItem")
    Item toItem(ItemRequestDto itemRequestDto);

    /**
     * To item list list.
     *
     * @param items the items
     * @return the list
     */
    @Named("toItemList")
    List<Item> toItemList(List<ItemRequestDto> items);

    /**
     * To item item.
     *
     * @param itemChannelDto the item channel dto
     * @return the item
     */
    Item toItem(ItemChannelDto itemChannelDto);

    /**
     * From item channel dto to item list list.
     *
     * @param itemChannelDtos the item channel dtos
     * @return the list
     */
    List<Item> fromItemChannelDtoToItemList(List<ItemChannelDto> itemChannelDtos);

}
