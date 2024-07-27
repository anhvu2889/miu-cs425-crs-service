package miu.cs425.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JwtTokenDto {

    private String accessToken;

    private String refreshToken;

}
