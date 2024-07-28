package miu.cs425.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class JwtTokenDto {

    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private Long id;

    private String username;

    private String email;

    private String firstname;

    private String lastname;

    private List<String> roles;
}
