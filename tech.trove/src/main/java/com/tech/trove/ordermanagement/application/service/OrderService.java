package com.tech.trove.ordermanagement.application.service;


import com.tech.trove.ordermanagement.application.dto.in.OrderRequestDto;
import com.tech.trove.ordermanagement.application.dto.in.OrderUpdateRequestDto;
import com.tech.trove.ordermanagement.application.dto.out.OrderChannelDto;


public interface OrderService {

    OrderChannelDto saveOrder(OrderRequestDto orderRequestDto);

    OrderChannelDto findOrderById(String orderId);

    OrderChannelDto updateOrder(OrderUpdateRequestDto orderUpdateRequestDto, String orderId);

    void removeOrder(String orderId);

}
