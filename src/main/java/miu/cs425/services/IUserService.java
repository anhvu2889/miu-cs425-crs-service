package miu.cs425.services;

import miu.cs425.dtos.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserDto> getAllUsers();

    UserDto getUserById(int id);

    UserDto updateUser(UserDto userDto);

    UserDto saveUser(UserDto userDto);
}
