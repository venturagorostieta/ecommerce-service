package com.tech.trove.usermanagement.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * The type User login request dto.
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
