package miu.cs425.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {

    private String username;

    private String password;
}
