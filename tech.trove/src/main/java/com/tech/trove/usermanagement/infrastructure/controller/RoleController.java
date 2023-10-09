package com.tech.trove.usermanagement.infrastructure.controller;

import com.tech.trove.usermanagement.application.dto.in.RoleRequestDto;
import com.tech.trove.usermanagement.application.dto.out.RoleChannelDto;
import com.tech.trove.usermanagement.application.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The type Role controller.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleController {

    private final RoleService roleService;

    /**
     * Create role role channel dto.
     *
     * @param roleRequestDto the role request dto
     * @return the role channel dto
     */
    @Operation(summary = "Create new Role", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role create successfully."),

    })
    @PostMapping(value = "user/role", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RoleChannelDto createRole(@RequestBody @Valid final RoleRequestDto roleRequestDto) {

        return roleService.saveRole(roleRequestDto);
    }

    /**
     * Create user role channel dto.
     *
     * @param roleId the role id
     * @return the role channel dto
     */
    @Operation(summary = "Get Role", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role found successfully."),

    })
    @GetMapping(value = "user/role/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RoleChannelDto createUser(@PathVariable  final String roleId) {

        return roleService.findRoleById(roleId);
    }

}
