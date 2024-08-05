package miu.cs425.mappers;

import miu.cs425.dtos.UserDto;
import miu.cs425.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserDto toUserDto(User user) {
        modelMapper.typeMap(User.class, UserDto.class).addMappings(mapper -> {
            mapper.skip(UserDto::setPassword);
            mapper.skip(UserDto::setRoles);
        });

        UserDto userDto = modelMapper.map(user, UserDto.class);
        List<Long> roles = user.getRoles().stream().map(e -> e.getRole().getRoleId()).toList();
        userDto.setRoles(roles);
        return userDto;
    }

    public List<UserDto> toUserDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(toUserDto(user));
        }
        return userDtos;
    }

    public User toUser(UserDto userDto) {
        modelMapper.typeMap(UserDto.class, User.class).addMappings(mapper -> {
            mapper.skip(User::setRoles);
        });
        return modelMapper.map(userDto, User.class);
    }
}
