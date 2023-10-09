package com.tech.trove.ordermanagement.application.mapper;

import com.tech.trove.ordermanagement.application.dto.in.OrderRequestDto;
import com.tech.trove.ordermanagement.application.dto.out.ItemChannelDto;
import com.tech.trove.ordermanagement.application.dto.out.OrderChannelDto;
import com.tech.trove.ordermanagement.infrastructure.database.mongodb.Item;
import com.tech.trove.ordermanagement.infrastructure.database.mongodb.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * The interface Order mapper.
 */
@Mapper(uses = {ItemMapper.class, PaymentMapper.class})
public interface OrderMapper {

    /**
     * The constant INSTANCE.
     */
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    /**
     * To channel dto order channel dto.
     *
     * @param order the order
     * @return the order channel dto
     */
    @Mapping(target = "items", source = "items", qualifiedByName = "toChannelDtoList")
    @Mapping(target = "paymentChannelDto", source = "payment")
    OrderChannelDto toChannelDto(Order order);

    /**
     * To order order.
     *
     * @param orderRequestDto the order request dto
     * @return the order
     */
    @Mapping(target = "items", source = "items", qualifiedByName = "toItemList")
    @Mapping(target = "payment", source = "paymentRequestlDto")
    Order toOrder(OrderRequestDto orderRequestDto);

    /**
     * From order channel dto to order order.
     *
     * @param orderChannelDto the order channel dto
     * @return the order
     */
    @Mapping(source = "items", target = "items", qualifiedByName = "toItemListChannel")
    Order fromOrderChannelDtoToOrder(OrderChannelDto orderChannelDto);

    /**
     * To item channel list list.
     *
     * @param itemChannelDtos the item channel dtos
     * @return the list
     */
    @Named("toItemListChannel")
    default List<Item> toItemChannelList(List<ItemChannelDto> itemChannelDtos) {
        return Mappers.getMapper(ItemMapper.class).fromItemChannelDtoToItemList(itemChannelDtos);
    }

}
