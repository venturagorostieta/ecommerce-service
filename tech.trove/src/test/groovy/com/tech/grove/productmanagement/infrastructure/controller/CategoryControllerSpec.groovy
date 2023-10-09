package com.tech.grove.productmanagement.infrastructure.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tech.grove.productmanagement.utils.TestUtils
import com.tech.trove.productmanagement.application.service.CategoryService
import com.tech.trove.productmanagement.infrastructure.controller.CategoryController
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

@SpringBootTest(classes = CategoryController.class)
@WebAppConfiguration
class CategoryControllerSpec extends Specification {

    @MockBean
    CategoryService categoryService

    MockMvc mockMvc

    @Shared
    def mapper = new ObjectMapper()

    def before() {
        MockitoAnnotations.openMocks(super)
    }

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoryController(categoryService)).build()
    }

    def "test create category"() {
        given: "a request DTO for a new category"
        def requestDto = TestUtils.buildCategoryRequestDto()
        def expectedJsonRequest = mapper.writeValueAsString(requestDto)

        and: "the service responds with a successful DTO"
        def responseDto = TestUtils.buildCategoryChannelDto()
        categoryService.saveCategory(requestDto) >> responseDto

        when: "the create category endpoint is called"
        def result = mockMvc.perform(
                post("/product/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJsonRequest))

        then: "expect a created status and the service's response"
        result
                .andExpect(status().isCreated())
    }

    def "test get category by id"() {
        given: "an existing category ID"
        String categoryId = "someId"

        and: "the service responds with the category details"
        def responseDto = TestUtils.buildCategoryChannelDto()
        categoryService.findCategoryById(categoryId) >> responseDto

        when: "the get category endpoint is called"
        def result = mockMvc.perform(
                get("/product/category/${categoryId}")
                        .accept(MediaType.APPLICATION_JSON))

        then: "expect an OK status and the service's response"
        result
                .andExpect(status().isOk())

    }

}
