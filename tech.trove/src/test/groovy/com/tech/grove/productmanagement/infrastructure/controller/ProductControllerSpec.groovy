package com.tech.grove.productmanagement.infrastructure.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tech.grove.productmanagement.utils.TestUtilConstants
import com.tech.grove.productmanagement.utils.TestUtils
import com.tech.trove.productmanagement.application.service.ProductService
import com.tech.trove.productmanagement.infrastructure.controller.ProductController
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

@SpringBootTest(classes = ProductController.class)
@WebAppConfiguration
class ProductControllerSpec extends Specification {

    @MockBean
    ProductService productService

    MockMvc mockMvc

    @Shared
    def mapper = new ObjectMapper()

    def before() {
        MockitoAnnotations.openMocks(super)
    }

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productService)).build()
    }

    def "test create product"() {
        given: "a request DTO for a new product"
        def requestDto = TestUtils.buildProductRequestDto()
        def expectedJsonRequest = mapper.writeValueAsString(requestDto)

        and: "the service responds with a successful DTO"
        def responseDto = TestUtils.buildProductChannelDto()
        productService.saveProduct(requestDto) >> responseDto

        when: "the create product endpoint is called"
        def result = mockMvc.perform(
                post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJsonRequest))

        then: "expect a created status and the service's response"
        result
                .andExpect(status().isCreated())
    }

    def "test get product by id"() {
        given: "an existing product ID"
        def productId = TestUtilConstants.DEFAULT_PRODUCT_ID

        and: "the service responds with the product details"
        def responseDto = TestUtils.buildProductChannelDto()
        productService.findProductById(productId) >> responseDto

        when: "the get product endpoint is called"
        def result = mockMvc.perform(get("/product/${productId}"))

        then: "expect an OK status and the service's response"
        result
                .andExpect(status().isOk())
    }

    def "test update product by product id"() {
        given: "an existing product ID and update request"
        String productId = "someId"
        def updateRequestDto = TestUtils.buildProductUpdateRequestDto()
        def expectedJsonRequest = mapper.writeValueAsString(updateRequestDto)

        and: "the service responds with the updated product details"
        def responseDto = TestUtils.buildProductChannelDto()
        productService.updateProduct(updateRequestDto, productId) >> responseDto

        when: "the update product endpoint is called"
        def result = mockMvc.perform(
                put("/product/${productId}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJsonRequest))

        then: "expect an OK status and the service's response"
        result
                .andExpect(status().isOk())
    }

    def "test remove product by product id"() {
        given: "an existing product ID"
        def productId = TestUtilConstants.DEFAULT_PRODUCT_ID

        and: "the service removes the product without error"
        productService.removeProductByProductId(productId) >> { /* No exception thrown */ }

        when: "the remove product endpoint is called"
        def result = mockMvc.perform(delete("/product/${productId}"))

        then: "expect a no content status"
        result
                .andExpect(status().isNoContent())
    }

}
