package com.tech.grove.ordermanagement.utils

import com.tech.trove.ordermanagement.application.dto.in.ItemRequestDto
import com.tech.trove.ordermanagement.application.dto.in.OrderRequestDto
import com.tech.trove.ordermanagement.application.dto.in.OrderUpdateRequestDto
import com.tech.trove.ordermanagement.application.dto.in.PaymentRequestlDto
import com.tech.trove.ordermanagement.application.dto.out.ItemChannelDto
import com.tech.trove.ordermanagement.application.dto.out.OrderChannelDto
import com.tech.trove.ordermanagement.application.dto.out.PaymentChannelDto
import com.tech.trove.ordermanagement.domain.model.CardType
import com.tech.trove.ordermanagement.infrastructure.database.mongodb.Item
import com.tech.trove.ordermanagement.infrastructure.database.mongodb.Order
import com.tech.trove.ordermanagement.infrastructure.database.mongodb.Payment

import java.util.stream.Collectors
import java.util.stream.IntStream

class TestUtils {

    static OrderChannelDto buildOrderChannelDto(String productId, int  count, String lastFourDigits) {
        return OrderChannelDto.builder().userId(TestUtilConstants.DEFAULT_USER_ID)
        .items(generateItems(1, productId, count))
        .paymentChannelDto(buildPaymentChannelDto(lastFourDigits))
        .build()
    }


    static List<ItemChannelDto> generateItems(int n, String productId, int  count) {
        return IntStream.range(0, n)
                .mapToObj(i -> buildItemChannelDto(productId, count))
                .collect(Collectors.toList());
    }

    static ItemChannelDto buildItemChannelDto(String productId, int  count) {

        return ItemChannelDto.builder()
        .productId(productId)
        .count(count)
        .build()
    }

    static PaymentChannelDto buildPaymentChannelDto(String lastFourDigits) {
        return PaymentChannelDto.builder()
        .authCode(TestUtilConstants.DEFAULT_AUTH_CODE)
        .cardType(CardType.DEBIT_CARD)
        .lastFourDigits(lastFourDigits)
        .total(TestUtilConstants.DEFAULT_ORDER_TOTAL)
        .build()

    }

    static OrderRequestDto buildOrderRequestDto(String productId, int  count, String lastFourDigits) {
        return OrderRequestDto.builder()
                .userId(TestUtilConstants.DEFAULT_USER_ID)
                .items(generateItemRequestDto(1, productId,count))
                .paymentRequestlDto(buildPaymentRequestlDto(lastFourDigits))
                .build()
    }

    static List<ItemRequestDto> generateItemRequestDto(int n, String productId, int  count) {
        return IntStream.range(0, n)
                .mapToObj(i -> buildItemRequestDto(productId, count))
                .collect(Collectors.toList());
    }

    static ItemRequestDto buildItemRequestDto(String productId, int  count) {

        return ItemRequestDto.builder()
                .productId(productId)
                .count(count)
                .build()
    }

    static PaymentRequestlDto buildPaymentRequestlDto(String lastFourDigits) {
        return PaymentRequestlDto.builder()
                .authCode(TestUtilConstants.DEFAULT_AUTH_CODE)
                .cardType(CardType.DEBIT_CARD)
                .lastFourDigits(lastFourDigits)
                .total(TestUtilConstants.DEFAULT_ORDER_TOTAL)
                .build()
    }

    static OrderUpdateRequestDto buildOrderUpdateRequestDto(String productId, int  count, String lastFourDigits) {
        return OrderUpdateRequestDto.builder()
                .items(generateItemRequestDto(1, productId,count))
                .paymentRequestlDto(buildPaymentRequestlDto(lastFourDigits))
                .build()
    }

    static Order buildOrder(String productId, int  count) {
        return Order.builder()
                .id(TestUtilConstants.DEFAULT_ORDER_ID)
        .userId(TestUtilConstants.DEFAULT_USER_ID)
        .items(generateItemModelList(1, productId, count))
        .payment(buildPayment())
                .build()
    }

    static List<Item> generateItemModelList(int n, String productId, int  count) {
        return IntStream.range(0, n)
                .mapToObj(i -> buildItemModel(productId, count))
                .collect(Collectors.toList());
    }

    static Item buildItemModel(String productId, int  count) {

        return Item.builder()
                .productId(productId)
                .count(count)
                .build()
    }

    static Payment buildPayment(){
        return Payment.builder()
                .id(TestUtilConstants.DEFAULT_PAYMENT_ID)
                .total(TestUtilConstants.DEFAULT_ORDER_TOTAL)
                .authCode(TestUtilConstants.DEFAULT_AUTH_CODE)
                .cardType(CardType.DEBIT_CARD)
                .lastFourDigits(TestUtilConstants.DEFAULT_LAST_FOUR_DIGITS)
        .build()
    }
}
