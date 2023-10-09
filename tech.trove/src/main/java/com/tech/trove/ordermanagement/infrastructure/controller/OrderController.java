package com.tech.trove.ordermanagement.infrastructure.controller;

import com.tech.trove.ordermanagement.application.dto.in.OrderRequestDto;
import com.tech.trove.ordermanagement.application.dto.in.OrderUpdateRequestDto;
import com.tech.trove.ordermanagement.application.dto.out.OrderChannelDto;
import com.tech.trove.ordermanagement.application.service.OrderService;
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
 * The type Order controller.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    /**
     * Create order order channel dto.
     *
     * @param orderRequestDto the order request dto
     * @return the order channel dto
     */
    @Operation(summary = "Create new order.", tags = "order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created."),

    })
    @PostMapping(value = "order", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderChannelDto createOrder(@RequestBody @Valid final OrderRequestDto orderRequestDto) {

        return orderService.saveOrder(orderRequestDto);
    }

    /**
     * Gets order.
     *
     * @param orderId the order id
     * @return the order
     */
    @Operation(summary = "Find order by id.", tags = "order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found."),

    })
    @GetMapping(value = "order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderChannelDto getOrder(@PathVariable @Valid final String orderId) {

        return orderService.findOrderById(orderId);
    }

    /**
     * Gets order.
     *
     * @param orderId               the order id
     * @param orderUpdateRequestDto the order update request dto
     * @return the order
     */
    @Operation(summary = "Update order by id.", tags = "order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated."),

    })
    @PutMapping(value = "order/{orderId}",consumes =  MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderChannelDto getOrder(@PathVariable @Valid final String orderId,
                                    @RequestBody @Valid final OrderUpdateRequestDto orderUpdateRequestDto) {

        return orderService.updateOrder(orderUpdateRequestDto,orderId);
    }

    /**
     * Remove order.
     *
     * @param orderId the order id
     */
    @Operation(summary = "Remove order by id.", tags = "order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order delete."),

    })
    @DeleteMapping(value = "order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrder(@PathVariable @Valid final String orderId) {

        orderService.removeOrder(orderId);
    }
}
