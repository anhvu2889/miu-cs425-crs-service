package miu.cs425.dtos;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long userId;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String email;

    private String address;

    private String phone;

    private Date createdAt;

    private Date updatedAt;
}
