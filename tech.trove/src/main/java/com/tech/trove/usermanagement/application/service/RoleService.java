package com.tech.trove.usermanagement.application.service;

import com.tech.trove.usermanagement.application.dto.in.RoleRequestDto;
import com.tech.trove.usermanagement.application.dto.out.RoleChannelDto;

public interface RoleService {

    RoleChannelDto saveRole(RoleRequestDto roleRequestDto);

    RoleChannelDto findRoleById(String roleId);

}
