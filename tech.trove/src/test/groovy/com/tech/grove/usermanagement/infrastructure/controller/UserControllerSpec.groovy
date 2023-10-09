package com.tech.grove.usermanagement.infrastructure.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tech.grove.usermanagement.utils.TestUtilConstants
import com.tech.grove.usermanagement.utils.TestUtils
import com.tech.trove.usermanagement.application.service.UserService
import com.tech.trove.usermanagement.infrastructure.controller.UserController
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

@SpringBootTest(classes = UserController.class)
@WebAppConfiguration
class UserControllerSpec extends Specification {

    @MockBean
    UserService userService

    MockMvc mockMvc

    @Shared
    def mapper = new ObjectMapper()

    def before() {
        MockitoAnnotations.openMocks(super)
    }

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build()
    }

    def "should create user successfully"() {
        given:
        def userRequestDto = TestUtils.buildUserRequestDto()
        def expectedUser = TestUtils.buildUserChannelDto()
        def expectedJsonRequest = mapper.writeValueAsString(userRequestDto)

        when:
        userService.saveUser(userRequestDto) >> expectedUser

        def result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedJsonRequest))

        then:
        result.andExpect {status().isCreated()}
    }

    def "should get user by id"() {
        given:
        def userId = TestUtilConstants.DEFAULT_USER_ID
        def expectedUser = TestUtils.buildUserChannelDto()
        when:
        userService.findUserById(userId) >> expectedUser

        def result = mockMvc.perform(get("/user/${userId}")
                .contentType(MediaType.APPLICATION_JSON))

        then:
        result.andExpect {status().isOk()}
    }

    def "should update user"() {
        given:
        def userId = TestUtilConstants.DEFAULT_USER_ID
        def userUpdateRequestDto = TestUtils.buildUserUpdateRequestDto()
        def expectedUser = TestUtils.buildUserChannelDto()
        def expectedJsonRequest = mapper.writeValueAsString(userUpdateRequestDto)

        when:
        userService.updateUser(userUpdateRequestDto, userId) >> expectedUser

        def result = mockMvc.perform(put("/user/${userId}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedJsonRequest))

        then:
        result.andExpect {status().isOk()}
    }

    def "should delete user"() {
        given:
        def userId = TestUtilConstants.DEFAULT_USER_ID
        when:
        userService.removeUserById(userId) >> { }

        def result = mockMvc.perform(delete("/user/${userId}")
                .contentType(MediaType.APPLICATION_JSON))

        then:
        result.andExpect {status().isNoContent()}
    }

    def "should login user"() {
        given:
        def userLoginRequestDTO = TestUtils.buildUserLoginResponseDto()
        def expectedResponse = TestUtils.buildUserLoginCognitoResponseDto()
        def expectedJsonRequest = mapper.writeValueAsString(userLoginRequestDTO)

        when:
        userService.userLogin(userLoginRequestDTO) >> expectedResponse

        def result = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedJsonRequest))

        then:
        result.andExpect {status().isOk()}
    }

}
