package miu.cs425.services;

import miu.cs425.dtos.UserDto;
import miu.cs425.dtos.requests.DynamicFilterSortRequest;
import miu.cs425.models.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService, IGenericService<User> {

    List<UserDto> getAllUsers();

    Page<UserDto> getUsersByFilter(DynamicFilterSortRequest dynamicFilterSortRequest);

    UserDto getUserById(Long id);

    UserDto updateUser(UserDto userDto);

    UserDto saveUser(UserDto userDto);

    void deleteUser(Long id);
}
