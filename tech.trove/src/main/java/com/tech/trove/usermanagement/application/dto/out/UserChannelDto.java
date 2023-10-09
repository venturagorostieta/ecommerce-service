package com.tech.trove.usermanagement.application.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The type User channel dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserChannelDto implements Serializable {

    private String userId;

    private String username;

    private String password;

    private String email;

    private String firstname;

    private String lastname;

    private LocalDateTime createdAt;

}
