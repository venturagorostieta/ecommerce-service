package com.tech.trove.ordermanagement.application.mapper;

import com.tech.trove.ordermanagement.application.dto.in.PaymentRequestlDto;
import com.tech.trove.ordermanagement.application.dto.out.PaymentChannelDto;
import com.tech.trove.ordermanagement.domain.model.PaymentDto;
import com.tech.trove.ordermanagement.infrastructure.database.mongodb.Payment;
import org.mapstruct.Mapper;

/**
 * The interface Payment mapper.
 */
@Mapper
public interface PaymentMapper {

    /**
     * To dto payment dto.
     *
     * @param payment the payment
     * @return the payment dto
     */
    PaymentDto toDto(Payment payment);

    /**
     * From dto payment.
     *
     * @param paymentDto the payment dto
     * @return the payment
     */
    Payment fromDto(PaymentDto paymentDto);

    /**
     * To dto from request payment dto.
     *
     * @param paymentRequestDto the payment request dto
     * @return the payment dto
     */
    PaymentDto toDtoFromRequest(PaymentRequestlDto paymentRequestDto);

    /**
     * To request from dto payment requestl dto.
     *
     * @param paymentDto the payment dto
     * @return the payment requestl dto
     */
    PaymentRequestlDto toRequestFromDto(PaymentDto paymentDto);

    /**
     * To payment payment.
     *
     * @param paymentChannelDto the payment channel dto
     * @return the payment
     */
    Payment toPayment(PaymentChannelDto paymentChannelDto);

}
