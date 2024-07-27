package miu.cs425.services.impl;

import jakarta.persistence.EntityNotFoundException;
import miu.cs425.configurations.security.UserDetailsImpl;
import miu.cs425.constants.enums.RoleEnum;
import miu.cs425.dtos.UserDto;
import miu.cs425.mappers.UserMapper;
import miu.cs425.models.Role;
import miu.cs425.models.User;
import miu.cs425.repositories.IRoleRepository;
import miu.cs425.repositories.IUserRepository;
import miu.cs425.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    @Override
    public List<UserDto> getAllUsers() {
        Page<User> users = userRepository.findAll(PageRequest.of(0, 20));
        return userMapper.toUserDtos(users.getContent());
    }

    @Override
    public UserDto getUserById(int id) {
        return null;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        Role role = roleRepository.findByRoleName(RoleEnum.USER.getCode()).orElseThrow(() -> new EntityNotFoundException("Role not found"));
        user.getRoles().add(role);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto user) {
        return null;
    }
}
