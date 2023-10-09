package com.tech.trove.usermanagement.application.mapper;


import com.tech.trove.usermanagement.application.dto.in.UserRequestDto;
import com.tech.trove.usermanagement.application.dto.out.UserChannelDto;
import com.tech.trove.usermanagement.infrastructure.database.mongodb.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * The interface User mapper.
 */
@Mapper
public interface UserMapper {

    /**
     * The constant INSTANCE.
     */
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * To user user.
     *
     * @param userRequestDto the user request dto
     * @return the user
     */
    @Mapping(target = "userId", source = "username")
    @Mapping(target = "name", source = "firstname")
    @Mapping(target = "lastName", source = "lastname")
    User toUser(UserRequestDto userRequestDto);

    /**
     * To user channel dto user channel dto.
     *
     * @param user the user
     * @return the user channel dto
     */
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "firstname", source = "name")
    @Mapping(target = "lastname", source = "lastName")
    UserChannelDto toUserChannelDto(User user);


}
