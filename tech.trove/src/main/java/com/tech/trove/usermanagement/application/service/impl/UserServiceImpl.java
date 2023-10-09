package com.tech.trove.usermanagement.application.service.impl;


import com.tech.trove.infrastructure.configuration.CognitoCredentials;
import com.tech.trove.infrastructure.web.exception.ResourceNotFoundException;
import com.tech.trove.usermanagement.application.dto.RoleDto;
import com.tech.trove.usermanagement.application.dto.in.UserLoginRequestDTO;
import com.tech.trove.usermanagement.application.dto.in.UserRequestDto;
import com.tech.trove.usermanagement.application.dto.in.UserUpdateRequestDto;
import com.tech.trove.usermanagement.application.dto.out.UserChannelDto;
import com.tech.trove.usermanagement.application.dto.out.UserLoginResponseDto;
import com.tech.trove.usermanagement.application.mapper.UserMapper;
import com.tech.trove.usermanagement.application.service.UserService;
import com.tech.trove.usermanagement.common.constant.UserStatus;
import com.tech.trove.usermanagement.infrastructure.database.mongodb.Role;
import com.tech.trove.usermanagement.infrastructure.database.mongodb.User;
import com.tech.trove.usermanagement.infrastructure.database.mongodb.UserRole;
import com.tech.trove.usermanagement.infrastructure.database.mongodb.repository.RoleRepository;
import com.tech.trove.usermanagement.infrastructure.database.mongodb.repository.UserRepository;
import com.tech.trove.usermanagement.infrastructure.database.mongodb.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthFlowType;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The type User service.
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final RoleRepository roleRepository;

    private final CognitoIdentityProviderClient cognitoClient;

    @Qualifier("cognitoMainCredentials")
    private final CognitoCredentials mainCredentials;

    /**
     * Save user user channel dto.
     *
     * @param userRequestDto the user request dto
     * @return the user channel dto
     */
    @Override
    public UserChannelDto saveUser(UserRequestDto userRequestDto) {

        log.info("Init create user by productId.");

        User request = UserMapper.INSTANCE.toUser(userRequestDto);
        request.setStatus(UserStatus.ACTIVE);
        request.setCreatedAt(LocalDateTime.now());
        User response = userRepository.save(request);
        Optional<Role> roleResponse = roleRepository.findByName(userRequestDto.getRoleName());
        roleResponse.ifPresent(role -> userRoleRepository.save(
                UserRole.builder()
                        .userId(response.getUserId())
                        .role(role.getName())
                        .createdAt(LocalDateTime.now())
                        .active(Boolean.TRUE)
                        .build())
        );
        return UserMapper.INSTANCE.toUserChannelDto(response);
    }

    /**
     * Find user by id user channel dto.
     *
     * @param userId the user id
     * @return the user channel dto
     */
    @Cacheable(value = "users", key = "#userId")
    @Override
    public UserChannelDto findUserById(String userId) {
        log.info("Init find user by userId.{}", userId);

        Optional<User> response = userRepository.findById(userId);
        return UserMapper.INSTANCE.toUserChannelDto(
                response.orElseThrow( () -> new ResourceNotFoundException("User not found.")));
    }

    /**
     * Update user user channel dto.
     *
     * @param userUpdateRequestDto the user update request dto
     * @param userId               the user id
     * @return the user channel dto
     */
    @CachePut(value = "users", key = "#userId")
    @Override
    public UserChannelDto updateUser(UserUpdateRequestDto userUpdateRequestDto, String userId) {
        log.info("Init update user by userId.{}", userId);

        User userModel = lookUpUser(userId);
        userModel.setEmail(userUpdateRequestDto.getEmail());
        Optional<UserRole> userRoleModel = userRoleRepository.findByRole(userUpdateRequestDto.getRoleName());
        userRoleModel.ifPresent(userRole -> {
            userRole.setRole(userUpdateRequestDto.getRoleName());
            userRoleRepository.save(userRole);
        });

        return UserMapper.INSTANCE.toUserChannelDto(userRepository.save(userModel));

    }

    /**
     * Remove user by id.
     *
     * @param userId the user id
     */
    @CacheEvict(value = "users", key = "#userId")
    @Override
    public void removeUserById(String userId) {
        log.info("Init remove user by userId.{}", userId);

        lookUpUser(userId);
        userRepository.deleteById(userId);
    }

    /**
     * Retrieve user authorities by user id role dto.
     *
     * @param userId the user id
     * @return the role dto
     */
    @Override
    public RoleDto retrieveUserAuthoritiesByUserId(String userId) {
        Optional<Role>[] resultRoleArray = new Optional[1];

        Optional<UserRole> userRoleActive = userRoleRepository.findByUserIdAndActive(userId,true);

        userRoleActive.ifPresent(userRole ->
            resultRoleArray[0]= roleRepository.findByName(userRole.getRole()));
        Optional<Role> resultRole = resultRoleArray[0];
        final RoleDto[] response = {null};
        resultRole.ifPresent(role ->
                    response[0] = buildRoleDto(role));

        return response[0];
    }

    /**
     * User login user login response dto.
     *
     * @param userLoginRequestDTO the user login request dto
     * @return the user login response dto
     */
    @Override
    public UserLoginResponseDto userLogin(UserLoginRequestDTO userLoginRequestDTO) {

        findUserById(userLoginRequestDTO.getUsername());

        final Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", userLoginRequestDTO.getUsername());
        authParams.put("PASSWORD", userLoginRequestDTO.getPassword());

        AdminInitiateAuthRequest authRequest = AdminInitiateAuthRequest.builder()
                .authFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .clientId(mainCredentials.getClientId())
                .userPoolId(mainCredentials.getUserPoolId())
                .authParameters(authParams)
                .build();

        AdminInitiateAuthResponse authResponse = cognitoClient.adminInitiateAuth(authRequest);
        return UserLoginResponseDto.builder()
                .accessToken(authResponse.authenticationResult().accessToken())
                .refreshToken(authResponse.authenticationResult().refreshToken())
                .expiresIn(authResponse.authenticationResult().expiresIn())
                .build();

    }

    /**
     * Look up user user.
     *
     * @param id the id
     * @return the user
     */
    User lookUpUser (String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found."));
    }

    private RoleDto buildRoleDto(Role role) {
        return RoleDto.builder()
                .name(role.getName())
                .domainOperations(role.getDomainOperations())
                .build();
    }
}
