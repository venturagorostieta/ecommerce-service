package com.tech.trove.usermanagement.application.service.impl;

import com.tech.trove.infrastructure.web.exception.ResourceNotFoundException;
import com.tech.trove.usermanagement.application.dto.in.RoleRequestDto;
import com.tech.trove.usermanagement.application.dto.out.RoleChannelDto;
import com.tech.trove.usermanagement.application.mapper.RoleMapper;
import com.tech.trove.usermanagement.application.service.RoleService;
import com.tech.trove.usermanagement.infrastructure.database.mongodb.Role;
import com.tech.trove.usermanagement.infrastructure.database.mongodb.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Role service.
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    /**
     * Save role role channel dto.
     *
     * @param roleRequestDto the role request dto
     * @return the role channel dto
     */
    @Override
    public RoleChannelDto saveRole(RoleRequestDto roleRequestDto) {
        Role response = roleRepository.save(RoleMapper.INSTANCE.toRole(roleRequestDto));
        return RoleMapper.INSTANCE.toRoleChannelDto(response);
    }

    /**
     * Find role by id role channel dto.
     *
     * @param roleId the role id
     * @return the role channel dto
     */
    @Override
    public RoleChannelDto findRoleById(String roleId) {
        Optional<Role> response =  roleRepository.findById(roleId);
        return RoleMapper.INSTANCE.toRoleChannelDto(
                response.orElseThrow(() -> new ResourceNotFoundException("User not found.")));
    }

}
