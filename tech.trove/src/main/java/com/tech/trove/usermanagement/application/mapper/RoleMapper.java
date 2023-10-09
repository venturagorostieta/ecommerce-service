package com.tech.trove.usermanagement.application.mapper;


import com.tech.trove.usermanagement.application.dto.in.RoleRequestDto;
import com.tech.trove.usermanagement.application.dto.out.RoleChannelDto;
import com.tech.trove.usermanagement.infrastructure.database.mongodb.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The interface Role mapper.
 */
@Mapper
public interface RoleMapper {

    /**
     * The constant INSTANCE.
     */
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    /**
     * To role role.
     *
     * @param roleRequestDto the role request dto
     * @return the role
     */
    Role toRole(RoleRequestDto roleRequestDto);

    /**
     * To role channel dto role channel dto.
     *
     * @param role the role
     * @return the role channel dto
     */
    RoleChannelDto toRoleChannelDto(Role role);


}
