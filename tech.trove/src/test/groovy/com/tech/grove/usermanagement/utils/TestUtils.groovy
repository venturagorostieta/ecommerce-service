package com.tech.grove.usermanagement.utils

import com.tech.trove.usermanagement.application.dto.in.RoleRequestDto
import com.tech.trove.usermanagement.application.dto.in.UserLoginRequestDTO
import com.tech.trove.usermanagement.application.dto.in.UserRequestDto
import com.tech.trove.usermanagement.application.dto.in.UserUpdateRequestDto
import com.tech.trove.usermanagement.application.dto.out.RoleChannelDto
import com.tech.trove.usermanagement.application.dto.out.UserChannelDto
import com.tech.trove.usermanagement.application.dto.out.UserLoginResponseDto
import com.tech.trove.usermanagement.common.constant.DomainType
import com.tech.trove.usermanagement.common.constant.UserStatus
import com.tech.trove.usermanagement.infrastructure.database.mongodb.Role
import com.tech.trove.usermanagement.infrastructure.database.mongodb.User
import com.tech.trove.usermanagement.infrastructure.database.mongodb.UserRole
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthResponse
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthenticationResultType

class TestUtils {

    static RoleRequestDto buildRoleRequestDto() {
        return RoleRequestDto.builder()
        .name(TestUtilConstants.DEFAULT_ROLE_NAME)
        .domain(DomainType.ADMIN)
        .domainOperations(Collections.singleton(TestUtilConstants.DEFAULT_ROLE_OPERATION).toList())
        .build()
    }

    static Role buildRole() {
        return Role.builder()
        .id(TestUtilConstants.DEFAULT_ROLE_DOCUMENT_ID)
        .name(TestUtilConstants.DEFAULT_ROLE_NAME)
        .domain(DomainType.ADMIN)
        .domainOperations(Collections.singleton(TestUtilConstants.DEFAULT_ROLE_OPERATION).toList())
        .build()
    }

    static RoleChannelDto buildRoleChannelDto() {
        return RoleChannelDto.builder()
                .name(TestUtilConstants.DEFAULT_ROLE_NAME)
                .domain(DomainType.ADMIN)
                .domainOperations(Collections.singleton(TestUtilConstants.DEFAULT_ROLE_OPERATION).toList())
        .build()
    }


    static UserRequestDto buildUserRequestDto() {
        return UserRequestDto.builder()
                .email(TestUtilConstants.DEFAULT_USER_EMAIL)
                .firstname(TestUtilConstants.DEFAULT_FIRST_NAME)
                .password(TestUtilConstants.DEFAULT_USERNAME_PASSWORD)
                .roleName(TestUtilConstants.DEFAULT_ROLE_NAME)
                .username(TestUtilConstants.DEFAULT_USER_ID)
        .build()
    }

    static User buildUser() {
        User.builder()
                .email(TestUtilConstants.DEFAULT_USER_EMAIL)
                .name(TestUtilConstants.DEFAULT_FIRST_NAME)
                .userId(TestUtilConstants.DEFAULT_USER_ID)
                .status(UserStatus.ACTIVE)
        .build()
    }

    static UserChannelDto buildUserChannelDto() {
        return UserChannelDto.builder()
                .email(TestUtilConstants.DEFAULT_USER_EMAIL)
                .username(TestUtilConstants.DEFAULT_FIRST_NAME)
                .userId(TestUtilConstants.DEFAULT_USER_ID)
        .build()
    }

    static UserUpdateRequestDto buildUserUpdateRequestDto() {
        return UserUpdateRequestDto.builder()
        .email(TestUtilConstants.DEFAULT_USER_EMAIL)
        .roleName(TestUtilConstants.DEFAULT_ROLE_NAME)
        .build()
    }

    static UserRole buildUserRole() {
        return UserRole.builder()
                .userId(TestUtilConstants.DEFAULT_USER_ID)
                .role(TestUtilConstants.DEFAULT_ROLE_NAME)
                .build()
    }

    static UserLoginRequestDTO buildUserLoginResponseDto() {
        return UserLoginRequestDTO.builder()
                .username(TestUtilConstants.DEFAULT_USER_ID)
                .password(TestUtilConstants.DEFAULT_USERNAME_PASSWORD)
        .build()
    }

    static AdminInitiateAuthResponse buildAdminInitiateAuthResponse () {
        return AdminInitiateAuthResponse.builder()
                .authenticationResult(
                        AuthenticationResultType.builder()
                                .accessToken(TestUtilConstants.DEFAULT_LOGIN_ACCESS_TOKEN)
                                .refreshToken(TestUtilConstants.DEFAULT_LOGIN_REFRESG_TOKEN)
                                .expiresIn(TestUtilConstants.DEFAULT_COGNITO_TOKEN_EXPIRED)
                                .build()
                ).build()
    }

    static UserLoginResponseDto buildUserLoginCognitoResponseDto() {
        return UserLoginResponseDto.builder()
                .accessToken(TestUtilConstants.DEFAULT_LOGIN_ACCESS_TOKEN)
                .refreshToken(TestUtilConstants.DEFAULT_LOGIN_REFRESG_TOKEN)
                .expiresIn(TestUtilConstants.DEFAULT_COGNITO_TOKEN_EXPIRED)
        .build()
    }
}
