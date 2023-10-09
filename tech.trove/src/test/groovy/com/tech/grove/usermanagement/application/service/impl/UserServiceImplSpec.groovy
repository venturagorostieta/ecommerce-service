package com.tech.grove.usermanagement.application.service.impl

import com.tech.grove.usermanagement.utils.TestUtilConstants
import com.tech.grove.usermanagement.utils.TestUtils
import com.tech.trove.infrastructure.configuration.CognitoCredentials
import com.tech.trove.usermanagement.application.service.impl.UserServiceImpl
import com.tech.trove.usermanagement.infrastructure.database.mongodb.repository.RoleRepository
import com.tech.trove.usermanagement.infrastructure.database.mongodb.repository.UserRepository
import com.tech.trove.usermanagement.infrastructure.database.mongodb.repository.UserRoleRepository
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient
import spock.lang.Specification
import spock.lang.Subject

class UserServiceImplSpec extends Specification {

    def userRepository = Mock(UserRepository)

    def userRoleRepository = Mock(UserRoleRepository)

    def roleRepository = Mock(RoleRepository)

    def cognitoClient = Mock(CognitoIdentityProviderClient)

    def cognitoCredentials = Mock(CognitoCredentials)

    @Subject
    def subject = new UserServiceImpl(userRepository, userRoleRepository,
            roleRepository, cognitoClient, cognitoCredentials)

    def "test saveUser"() {
        given:
        def requestDto = TestUtils.buildUserRequestDto()
        def userModel = TestUtils.buildUser()
        def roleModel = TestUtils.buildRole()
        when:
        def result = subject.saveUser(requestDto)

        then:
        result != null
        result.userId == TestUtilConstants.DEFAULT_USER_ID
        1 * userRepository.save(_) >> userModel
        1 * roleRepository.findByName(_) >> Optional.of(roleModel)
        1 * userRoleRepository.save(_)
        0 * _
    }

    def "test findUserById"() {
        given:
        def requestDto = TestUtilConstants.DEFAULT_USER_ID
        def userModel = TestUtils.buildUser()
        when:
        def result = subject.findUserById(requestDto)

        then:
        result != null
        result.userId == TestUtilConstants.DEFAULT_USER_ID
        1 * userRepository.findById(_) >> Optional.of(userModel)
        0 * _
    }


    def "test updateUser"() {
        given:
        def requestDto = TestUtils.buildUserUpdateRequestDto()
        def userModel = TestUtils.buildUser()
        def pathParam = TestUtilConstants.DEFAULT_USER_ID
        def userRole = TestUtils.buildUserRole()
        when:
        def result = subject.updateUser(requestDto, pathParam)

        then:
        result != null
        result.userId == TestUtilConstants.DEFAULT_USER_ID
        1 * userRepository.findById(_) >> Optional.of(userModel)
        1 * userRoleRepository.findByRole(_) >> Optional.of(userRole)
        1 *userRoleRepository.save(_)
        1 * userRepository.save(_) >> userModel

        0 * _
    }

    def "test removeUserById"() {
        given:
        def requestDto = TestUtilConstants.DEFAULT_USER_ID
        def userModel = TestUtils.buildUser()
        when:
        subject.removeUserById(requestDto)

        then:
        1 * userRepository.findById(_) >> Optional.of(userModel)
        1 * userRepository.deleteById(_)
        0 * _
    }

    def "test userLogin"() {
        given:
        def requestDto = TestUtils.buildUserLoginResponseDto()
        def userModel = TestUtils.buildUser()
        def login = TestUtils.buildAdminInitiateAuthResponse()
        when:
        def result = subject.userLogin(requestDto)

        then:

        1 * userRepository.findById(_) >> Optional.of(userModel)
        1 * cognitoCredentials.getClientId() >> TestUtilConstants.DEFAULT_COGNITO_CLIENT_ID
        1 * cognitoCredentials.getUserPoolId() >> TestUtilConstants.DEFAULT_COGNITO_POOL_ID
        1 * cognitoClient.adminInitiateAuth(_) >> login
        0 * _
    }
}
