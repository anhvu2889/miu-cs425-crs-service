package miu.cs425.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import miu.cs425.configurations.security.UserDetailsImpl;
import miu.cs425.constants.enums.TokenTypeEnum;
import miu.cs425.dtos.JwtTokenDto;
import miu.cs425.dtos.UserDto;
import miu.cs425.dtos.requests.AuthRequest;
import miu.cs425.services.IUserService;
import miu.cs425.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pub")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private IUserService userService;

    @Operation(summary = "Basic authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful authentication"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtTokenDto> authenticate(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            JwtTokenDto tokenDto = JwtTokenDto.builder()
                    .accessToken(jwtTokenUtils.generateAccessToken(userDetails))
                    .refreshToken(jwtTokenUtils.generateRefreshToken(userDetails))
                    .tokenType(TokenTypeEnum.BEARER.getValue())
                    .id(userDetails.getUserId())
                    .username(userDetails.getUsername())
                    .email(userDetails.getEmail())
                    .firstname(userDetails.getFirstname())
                    .lastname(userDetails.getLastname())
                    .roles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .build();

            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }

    @Operation(summary = "Sign up user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sign up user successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.signupUser(userDto), HttpStatus.CREATED);
    }
}
