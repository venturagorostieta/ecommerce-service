package com.tech.trove.usermanagement.infrastructure.controller;

import com.tech.trove.usermanagement.application.dto.in.UserLoginRequestDTO;
import com.tech.trove.usermanagement.application.dto.in.UserRequestDto;
import com.tech.trove.usermanagement.application.dto.in.UserUpdateRequestDto;
import com.tech.trove.usermanagement.application.dto.out.UserChannelDto;
import com.tech.trove.usermanagement.application.dto.out.UserLoginResponseDto;
import com.tech.trove.usermanagement.application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The type User controller.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    /**
     * Create user user channel dto.
     *
     * @param userRequestDto the user request dto
     * @return the user channel dto
     */
    @Operation(summary = "Create new user", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User create successfully."),

    })
    @PostMapping(value = "user", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserChannelDto createUser(@RequestBody @Valid final UserRequestDto userRequestDto) {

        return userService.saveUser(userRequestDto);
    }

    /**
     * Find user by id user channel dto.
     *
     * @param userId the user id
     * @return the user channel dto
     */
    @Operation(summary = "Get user", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found successfully."),

    })
    @GetMapping(value = "user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserChannelDto findUserById(@PathVariable  final String userId) {

        return userService.findUserById(userId);
    }

    /**
     * Update user user channel dto.
     *
     * @param userUpdateRequestDto the user update request dto
     * @param userId               the user id
     * @return the user channel dto
     */
    @Operation(summary = "Update  user", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User update successfully."),

    })
    @PutMapping(value = "user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserChannelDto updateUser(@RequestBody @Valid final UserUpdateRequestDto userUpdateRequestDto,
                                     @PathVariable final String userId) {

        return userService.updateUser(userUpdateRequestDto, userId);
    }

    /**
     * Remove user by id.
     *
     * @param userId the user id
     */
    @Operation(summary = "Remove user", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User delete successfully."),

    })
    @DeleteMapping(value = "user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUserById(@PathVariable  final String userId) {
        userService.removeUserById(userId);
    }

    /**
     * User login user login response dto.
     *
     * @param userLoginRequestDTO the user login request dto
     * @return the user login response dto
     */
    @Operation(summary = "Login user", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User login successfully."),

    })
    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserLoginResponseDto userLogin(@RequestBody @Valid final UserLoginRequestDTO userLoginRequestDTO) {

        return userService.userLogin(userLoginRequestDTO);
    }

}
