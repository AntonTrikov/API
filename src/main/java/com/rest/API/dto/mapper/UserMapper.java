package com.rest.API.dto.mapper;

import com.rest.API.dto.model.UserDto;
import com.rest.API.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto toUserDto(AppUser user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
        /*return new UserDto()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setLastName(user.getLastName())
                .setMobileNumber(user.getMobileNumber())
                .setRoles(new HashSet<RoleDto>(user
                        .getRoles()
                        .stream()
                        .map(role -> new ModelMapper().map(role, RoleDto.class))
                        .collect(Collectors.toSet())))*/
    }

}