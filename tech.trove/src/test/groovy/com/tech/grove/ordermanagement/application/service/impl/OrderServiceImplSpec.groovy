package com.tech.grove.ordermanagement.application.service.impl

import com.tech.grove.ordermanagement.utils.TestUtilConstants
import com.tech.grove.ordermanagement.utils.TestUtils
import com.tech.trove.ordermanagement.application.service.impl.OrderServiceImpl
import com.tech.trove.ordermanagement.infrastructure.database.mongodb.repository.OrderRepository
import spock.lang.Specification
import spock.lang.Subject

class OrderServiceImplSpec extends Specification {

    def orderRepository = Mock(OrderRepository)

    @Subject
    def subject = new OrderServiceImpl(orderRepository)

    def "test saveOrder"() {
        given:
        def requestDto = TestUtils.buildOrderRequestDto(
                TestUtilConstants.DEFAULT_PRODUCT_ID,
                TestUtilConstants.DEFAULT_ORDER_COUNT,
                TestUtilConstants.DEFAULT_LAST_FOUR_DIGITS)
        def orderModel = TestUtils.buildOrder(TestUtilConstants.DEFAULT_PRODUCT_ID,
                TestUtilConstants.DEFAULT_ORDER_COUNT)
        when:
        def result = subject.saveOrder(requestDto)

        then:
        result != null
        result.items.size() == 1
        1 * orderRepository.save(_) >> orderModel
        0 * _
    }


    def "test findOrderById"() {
        given:
        def idParam = TestUtilConstants.DEFAULT_ORDER_ID
        def orderModel = TestUtils.buildOrder(TestUtilConstants.DEFAULT_PRODUCT_ID,
                TestUtilConstants.DEFAULT_ORDER_COUNT)
        when:
        def result = subject.findOrderById(idParam)

        then:
        result != null
        result.items.size() == 1
        1 * orderRepository.findById(_) >> Optional.of(orderModel)
        0 * _
    }

    def "test updateOrder"() {
        given:
        def idParam = TestUtilConstants.DEFAULT_ORDER_ID

        def requestDto = TestUtils.buildOrderUpdateRequestDto(
                TestUtilConstants.DEFAULT_PRODUCT_ID,
                TestUtilConstants.DEFAULT_ORDER_COUNT,
                TestUtilConstants.DEFAULT_LAST_FOUR_DIGITS)
        def orderModel = TestUtils.buildOrder(TestUtilConstants.DEFAULT_PRODUCT_ID,
                TestUtilConstants.DEFAULT_ORDER_COUNT)
        when:
        def result = subject.updateOrder(requestDto, idParam)

        then:
        result != null
        result.items.size() == 1
        1 * orderRepository.findById(_) >> Optional.of(orderModel)
        1 * orderRepository.save(_) >> orderModel
        0 * _
    }

    def "test findOrderById"() {
        given:
        def idParam = TestUtilConstants.DEFAULT_ORDER_ID
        def orderModel = TestUtils.buildOrder(TestUtilConstants.DEFAULT_PRODUCT_ID,
                TestUtilConstants.DEFAULT_ORDER_COUNT)

        when:
        subject.removeOrder(idParam)

        then:
        1 * orderRepository.findById(_) >> Optional.of(orderModel)
        1 * orderRepository.deleteById(_)
        0 * _
    }

}
