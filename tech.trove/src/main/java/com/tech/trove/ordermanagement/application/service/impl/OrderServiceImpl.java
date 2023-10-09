package com.tech.trove.ordermanagement.application.service.impl;

import com.tech.trove.infrastructure.web.exception.ResourceNotFoundException;
import com.tech.trove.ordermanagement.application.dto.in.OrderRequestDto;
import com.tech.trove.ordermanagement.application.dto.in.OrderUpdateRequestDto;
import com.tech.trove.ordermanagement.application.dto.out.OrderChannelDto;
import com.tech.trove.ordermanagement.application.mapper.ItemMapper;
import com.tech.trove.ordermanagement.application.mapper.OrderMapper;
import com.tech.trove.ordermanagement.application.mapper.PaymentMapper;
import com.tech.trove.ordermanagement.application.service.OrderService;
import com.tech.trove.ordermanagement.domain.model.PaymentDto;
import com.tech.trove.ordermanagement.infrastructure.database.mongodb.Order;
import com.tech.trove.ordermanagement.infrastructure.database.mongodb.Payment;
import com.tech.trove.ordermanagement.infrastructure.database.mongodb.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Order service.
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    /**
     * Save order order channel dto.
     *
     * @param orderRequestDto the order request dto
     * @return the order channel dto
     */
    @Override
    public OrderChannelDto saveOrder(OrderRequestDto orderRequestDto) {

        log.info("Init Create order");
        Order request = OrderMapper.INSTANCE.toOrder(orderRequestDto);
        return OrderMapper.INSTANCE.toChannelDto(saveOrder(request));
    }

    /**
     * Find order by id order channel dto.
     *
     * @param id the id
     * @return the order channel dto
     */
    @Override
    public OrderChannelDto findOrderById(String id) {
        log.info("Init find order byId {}", id);
        Optional<Order> response = orderRepository.findById(id);
        return OrderMapper.INSTANCE.toChannelDto(
                response.orElseThrow( () -> new ResourceNotFoundException("Product not found.")));
    }

    /**
     * Update order order channel dto.
     *
     * @param orderUpdateRequestDto the order update request dto
     * @param orderId               the order id
     * @return the order channel dto
     */
    @Override
    public OrderChannelDto updateOrder(OrderUpdateRequestDto orderUpdateRequestDto,
                                       String orderId) {
        log.info("Init update order by orderId {}", orderId);

        OrderChannelDto orderFound = findOrderById(orderId);
        Order oderModel = OrderMapper.INSTANCE.fromOrderChannelDtoToOrder(orderFound);
        oderModel.setItems(Mappers.getMapper(ItemMapper.class).toItemList(orderUpdateRequestDto.getItems()));
        PaymentDto paymentDto = Mappers.getMapper(PaymentMapper.class).toDtoFromRequest(orderUpdateRequestDto.getPaymentRequestlDto());
        Payment payment = Mappers.getMapper(PaymentMapper.class).fromDto(paymentDto);
        oderModel.setPayment(payment);
        return OrderMapper.INSTANCE.toChannelDto(saveOrder(oderModel));
    }

    /**
     * Remove order.
     *
     * @param orderId the order id
     */
    @Override
    public void removeOrder(String orderId) {
        log.info("Init remove order by orderId {}", orderId);

        findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    private Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

}
