package miu.cs425.services.impl;

import jakarta.persistence.EntityNotFoundException;
import miu.cs425.configurations.security.UserDetailsImpl;
import miu.cs425.dtos.UserDto;
import miu.cs425.dtos.requests.DynamicFilterSortRequest;
import miu.cs425.mappers.UserMapper;
import miu.cs425.models.Role;
import miu.cs425.models.User;
import miu.cs425.models.UserRole;
import miu.cs425.repositories.IRoleRepository;
import miu.cs425.repositories.IUserRepository;
import miu.cs425.repositories.IUserRoleRepository;
import miu.cs425.services.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        List<Role> roles = roleRepository.findRolesByUserId(user.getUserId());
        return new UserDetailsImpl(user, roles);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDtos(users);
    }

    @Override
    public Page<UserDto> getUsersByFilter(DynamicFilterSortRequest dynamicFilterSortRequest) {
        Result<User> result = filterAndSort(dynamicFilterSortRequest);
        Page<User> userPage = userRepository.findAll(result.filter(), result.pageable());
        return new PageImpl<>(userMapper.toUserDtos(userPage.getContent()), userPage.getPageable(), userPage.getTotalElements());
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        List<UserRole> roles = userRoleRepository.findByUserUserId(id);
        if (userOpt.isEmpty()) {
            return null;
        }

        User user = userOpt.get();
        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserDto(user);
    }

    @Transactional
    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        User savedUser = userRepository.save(user);

        List<Role> roles = roleRepository.findAllById(userDto.getRoles());
        Set<UserRole> userRoles = roles.stream().map(r -> new UserRole(savedUser, r)).collect(Collectors.toSet());
        userRoleRepository.saveAll(userRoles);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userRoleRepository.deleteByUserUserId(id);
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userDto.getUserId()));
        if (StringUtils.isNotEmpty(userDto.getFirstname())) {
            user.setFirstname(userDto.getFirstname());
        }

        if (StringUtils.isNotEmpty(userDto.getLastname())) {
            user.setLastname(userDto.getLastname());
        }

        if (StringUtils.isNotEmpty(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }

        if (StringUtils.isNotEmpty(userDto.getPhone())) {
            user.setPhone(userDto.getPhone());
        }

        if (StringUtils.isNotEmpty(userDto.getAddress())) {
            user.setAddress(userDto.getAddress());
        }

        User savedUser = userRepository.save(user);

        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            List<Role> roles = roleRepository.findAllById(userDto.getRoles());
            Set<UserRole> userRoles = roles.stream()
                    .map(r -> new UserRole(savedUser, r))
                    .collect(Collectors.toSet());
            userRoleRepository.deleteByUserUserId(savedUser.getUserId());
            userRoleRepository.saveAll(userRoles);
        }
        return modelMapper.map(savedUser, UserDto.class);
    }
}
