package com.tech.trove.usermanagement.application.dto.in;

import com.tech.trove.usermanagement.common.constant.DomainType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The type User request dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @Pattern(regexp = "^[0-9a-z]{12,55}$")
    @NotBlank
    private String username;

    @Pattern(regexp = "^[0-9a-z]{12,55}$")
    @NotBlank
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9]{1}[a-zA-Z0-9\\._\\-]+[a-zA-Z0-9]+@[a-zA-Z0-9]{1}[a-zA-Z0-9\\._\\-]+\\.[a-z]+$",
            message = "Email is invalid.")
    @NotBlank
    private String email;

    @Size(max = 60)
    @NotBlank
    private String firstname;

    @Size(max = 60)
    @NotBlank
    private String lastname;

    private String roleName;

}
