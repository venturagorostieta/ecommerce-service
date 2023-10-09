package com.tech.grove.ordermanagement.infrastructure.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tech.grove.ordermanagement.utils.TestUtilConstants
import com.tech.grove.ordermanagement.utils.TestUtils
import com.tech.trove.ordermanagement.application.service.OrderService
import com.tech.trove.ordermanagement.infrastructure.controller.OrderController
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = OrderController.class)
@WebAppConfiguration
class OrderControllerSpec extends Specification {

    @MockBean
    OrderService orderService

    MockMvc mockMvc

    @Shared
    def mapper = new ObjectMapper()

    def before() {
        MockitoAnnotations.openMocks(super)
    }

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new OrderController(orderService)).build()
    }

    def "test create order"() {
        given: "a request DTO for a new Order"
        def requestDto = TestUtils.buildOrderRequestDto(
                TestUtilConstants.DEFAULT_PRODUCT_ID,
                TestUtilConstants.DEFAULT_ORDER_COUNT,
                TestUtilConstants.DEFAULT_LAST_FOUR_DIGITS)
        def expectedJsonRequest = mapper.writeValueAsString(requestDto)

        and: "the service responds with a successful DTO"
        def responseChannel = TestUtils.buildOrderChannelDto(
                TestUtilConstants.DEFAULT_PRODUCT_ID,
                TestUtilConstants.DEFAULT_ORDER_COUNT,
                TestUtilConstants.DEFAULT_LAST_FOUR_DIGITS)
        orderService.saveOrder(requestDto) >> responseChannel

        when: "the create order endpoint is called"
        def result = mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedJsonRequest))
                .andExpect(status().isCreated())

        then: "expect a created status and the service's response"
        result.andExpect(status().isCreated())
    }

    def "should get order by id"() {
        given: "an order id and its expected return DTO"
        def orderId = "12345"
        def expectedOrderDto = TestUtils.buildOrderChannelDto(
                TestUtilConstants.DEFAULT_PRODUCT_ID,
                TestUtilConstants.DEFAULT_ORDER_COUNT,
                TestUtilConstants.DEFAULT_LAST_FOUR_DIGITS)
        orderService.findOrderById(orderId) >> expectedOrderDto

        when: "the getOrder endpoint is invoked"
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/order/${orderId}")
                .contentType(MediaType.APPLICATION_JSON))

        then: "the expected order is returned with a status of 200 OK"
        result.andExpect(MockMvcResultMatchers.status().isOk())

    }

    def "should update order by id"() {
        given: "an order id, update request DTO, and the expected return DTO after update"
        def orderId = "12345"
        def updateRequestDto = TestUtils.buildOrderUpdateRequestDto(
                TestUtilConstants.DEFAULT_PRODUCT_ID,
                TestUtilConstants.DEFAULT_ORDER_COUNT,
                TestUtilConstants.DEFAULT_LAST_FOUR_DIGITS
        )
        def updatedOrderDto = TestUtils.buildOrderChannelDto(
                TestUtilConstants.DEFAULT_PRODUCT_ID,
                TestUtilConstants.DEFAULT_ORDER_COUNT,
                TestUtilConstants.DEFAULT_LAST_FOUR_DIGITS)
        def expectedJsonRequest = mapper.writeValueAsString(updateRequestDto)

        orderService.updateOrder(updateRequestDto, orderId) >> updatedOrderDto

        when: "the updateOrder endpoint is invoked"
        def result = mockMvc.perform(MockMvcRequestBuilders.put("/order/${orderId}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedJsonRequest))

        then: "the order is updated and the updated order is returned with a status of 200 OK"
        result.andExpect(MockMvcResultMatchers.status().isOk())
    }

    def "should remove order by id"() {
        given: "an order id to be deleted"
        def orderId = "12345"

        orderService.removeOrder(orderId)

        when: "the removeOrder endpoint is invoked"
        def result = mockMvc.perform(MockMvcRequestBuilders.delete("/order/${orderId}")
                .contentType(MediaType.APPLICATION_JSON))

        then: "the order is removed with a status of 204 NO CONTENT"
        result.andExpect(MockMvcResultMatchers.status().isNoContent())
    }

}
