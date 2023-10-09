package com.tech.grove.usermanagement.infrastructure.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tech.grove.usermanagement.utils.TestUtilConstants
import com.tech.grove.usermanagement.utils.TestUtils
import com.tech.trove.usermanagement.application.service.RoleService
import com.tech.trove.usermanagement.infrastructure.controller.RoleController
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

@SpringBootTest(classes = RoleController.class)
@WebAppConfiguration
class RoleControllerSpec extends Specification {

    @MockBean
    RoleService roleService

    MockMvc mockMvc

    @Shared
    def mapper = new ObjectMapper()

    def before() {
        MockitoAnnotations.openMocks(super)
    }

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RoleController(roleService)).build()
    }

    def "should create role successfully"() {
        given:
        def roleRequestDto = TestUtils.buildRoleRequestDto()
        def expectedRole = TestUtils.buildRoleChannelDto()
        def expectedJsonRequest = mapper.writeValueAsString(roleRequestDto)

        when:
        roleService.saveRole(roleRequestDto) >> expectedRole

        def result = mockMvc.perform(post("/user/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedJsonRequest))

        then:
        result.andExpect {status().isCreated()}
    }

    def "should get role by id"() {
        given:
        def roleId = TestUtilConstants.DEFAULT_ROLE_DOCUMENT_ID
        def expectedRole = TestUtils.buildRoleChannelDto()
        when:
        roleService.findRoleById(roleId) >> expectedRole

        def result = mockMvc.perform(get("/user/role/${roleId}")
                .contentType(MediaType.APPLICATION_JSON))

        then:
        result.andExpect {status().isOk()}
    }

}
