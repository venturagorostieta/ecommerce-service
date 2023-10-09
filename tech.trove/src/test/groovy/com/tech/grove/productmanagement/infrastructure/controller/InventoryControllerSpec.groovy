package com.tech.grove.productmanagement.infrastructure.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tech.grove.productmanagement.utils.TestUtilConstants
import com.tech.grove.productmanagement.utils.TestUtils
import com.tech.trove.productmanagement.application.service.InventoryService
import com.tech.trove.productmanagement.infrastructure.controller.InventoryController
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(classes = InventoryController.class)
@WebAppConfiguration
class InventoryControllerSpec extends Specification {

    @MockBean
    InventoryService inventoryService

    MockMvc mockMvc

    @Shared
    def mapper = new ObjectMapper()

    def before() {
        MockitoAnnotations.openMocks(super)
    }

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new InventoryController(inventoryService)).build()
    }

    def "test create inventory"() {
        given: "a request DTO for a new inventory"
        def requestDto = TestUtils.buildInventoryRequestDto()
        def expectedJsonRequest = mapper.writeValueAsString(requestDto)

        and: "the service responds with a successful DTO"
        def responseDto = TestUtils.buildInventoryChannelDto()
        inventoryService.saveInventory(requestDto) >> responseDto

        when: "the create inventory endpoint is called"
        def result = mockMvc.perform(
                post("/product/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJsonRequest))

        then: "expect a created status and the service's response"
        result
                .andExpect(status().isCreated())

    }

    def "test get inventory by id"() {
        given: "an existing product ID"
        def productId = TestUtilConstants.DEFAULT_PRODUCT_ID

        and: "the service responds with the inventory details"
        def responseDto = TestUtils.buildInventoryChannelDto()
        inventoryService.findInventoryById(productId) >> responseDto

        when: "the get inventory endpoint is called"
        def result = mockMvc.perform(get("/product/inventory/${productId}"))

        then: "expect a created status (though typically it would be OK) and the service's response"
        result
                .andExpect(status().isCreated())

    }

    def "test update inventory by product id"() {
        given: "an existing product ID and update request"
        def productId = TestUtilConstants.DEFAULT_PRODUCT_ID
        def updateRequestDto = TestUtils.buildInventoryUpdateRequestDto()
        def expectedJsonRequest = mapper.writeValueAsString(updateRequestDto)

        and: "the service responds with the updated inventory details"
        def responseDto = TestUtils.buildInventoryChannelDto()
        inventoryService.updateInventoryByProductId(updateRequestDto, productId) >> responseDto

        when: "the update inventory endpoint is called"
        def result = mockMvc.perform(
                put("/product/inventory/${productId}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJsonRequest))

        then: "expect an OK status and the service's response"
        result
                .andExpect(status().isOk())

    }

    def "test remove inventory by product id"() {
        given: "an existing product ID"
        def productId = TestUtilConstants.DEFAULT_PRODUCT_ID

        and: "the service removes the inventory without error"
        inventoryService.removeInventoryById(productId) >> { }

        when: "the remove inventory endpoint is called"
        def result = mockMvc.perform(delete("/product/inventory/${productId}"))

        then: "expect a no content status"
        result
                .andExpect(status().isNoContent())
    }

}
