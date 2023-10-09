package com.tech.trove.usermanagement.application.service;

import com.tech.trove.usermanagement.application.dto.RoleDto;
import com.tech.trove.usermanagement.application.dto.in.UserLoginRequestDTO;
import com.tech.trove.usermanagement.application.dto.in.UserRequestDto;
import com.tech.trove.usermanagement.application.dto.in.UserUpdateRequestDto;
import com.tech.trove.usermanagement.application.dto.out.UserChannelDto;
import com.tech.trove.usermanagement.application.dto.out.UserLoginResponseDto;

public interface UserService {

    UserChannelDto saveUser(UserRequestDto userRequestDto);

    UserChannelDto findUserById(String userId);

    UserChannelDto updateUser(UserUpdateRequestDto userUpdateRequestDto, String userId);

    void removeUserById(String id);

    RoleDto retrieveUserAuthoritiesByUserId(String userId);

    UserLoginResponseDto userLogin(UserLoginRequestDTO userLoginRequestDTO);

}
