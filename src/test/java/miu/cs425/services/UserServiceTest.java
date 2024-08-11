package miu.cs425.services;

import jakarta.persistence.EntityNotFoundException;
import miu.cs425.dtos.UserDto;
import miu.cs425.mappers.UserMapper;
import miu.cs425.models.Role;
import miu.cs425.models.User;
import miu.cs425.models.UserRole;
import miu.cs425.models.UserRoleId;
import miu.cs425.repositories.IRoleRepository;
import miu.cs425.repositories.IUserRepository;
import miu.cs425.repositories.IUserRoleRepository;
import miu.cs425.services.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private IUserRoleRepository userRoleRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void should_returnUserDetails_when_loadUserByUsername() throws UsernameNotFoundException {
        // given
        String username = "test";

        User mockUser = new User();
        mockUser.setUserId(1L);
        mockUser.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("ADMIN");
        roles.add(role);
        when(roleRepository.findRolesByUserId(mockUser.getUserId())).thenReturn(roles);

        // when
        UserDetails user = userService.loadUserByUsername(username);

        // then
        assertNotNull(user);
        assertEquals(mockUser.getUsername(), user.getUsername());
    }

    @Test
    public void should_throwException_when_loadUserByUsername() throws UsernameNotFoundException {
        // given
        String username = "test";
        when(userRepository.findByUsername(username)).thenThrow(new UsernameNotFoundException("User not found"));

        // when
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));

        // then
        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_returnUserDtoList_when_getAllUsers() {
        // given
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserId(1L);
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> userDtos = new ArrayList<>();
        UserDto userDto = UserDto.builder().userId(1L).build();
        userDtos.add(userDto);
        when(userMapper.toUserDtos(users)).thenReturn(userDtos);

        // when
        List<UserDto> resultUserDtos = userService.getAllUsers();

        // then
        assertEquals(users.size(), resultUserDtos.size());
        assertEquals(users.get(0).getUserId(), resultUserDtos.get(0).getUserId());
    }

    @Test
    public void should_returnNull_when_getUserById() {
        // given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        when(userRoleRepository.findByUserUserId(userId)).thenReturn(new ArrayList<>());

        // when
        UserDto userDto = userService.getUserById(userId);

        // then
        assertNull(userDto);
    }

    @Test
    public void should_returnUserDto_when_getUserById() {
        // given
        Long userId = 1L;

        User user = new User();
        user.setUserId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Long roleId = 1L;
        Role role = new Role();
        role.setRoleId(roleId);

        UserRole userRole = new UserRole();
        UserRoleId userRoleId = new UserRoleId(user.getUserId(), roleId);
        userRole.setId(userRoleId);
        userRole.setUser(user);
        userRole.setRole(role);
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);
        when(userRoleRepository.findByUserUserId(userId)).thenReturn(userRoles);

        UserDto userDto = UserDto.builder().userId(1L).build();
        when(userMapper.toUserDto(user)).thenReturn(userDto);

        // when
        UserDto resultUserDto = userService.getUserById(userId);

        // then
        assertEquals(user.getUserId(), resultUserDto.getUserId());
    }

    @Test
    public void should_throwException_when_deleteUser() {
        // given
        Long userId = 1L;

        when(userRepository.findById(userId)).thenThrow(new EntityNotFoundException("User not found"));

        // when
        Exception exception = assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(userId));

        // then
        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
