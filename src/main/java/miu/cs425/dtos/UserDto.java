package miu.cs425.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    private List<Long> roles;
}
