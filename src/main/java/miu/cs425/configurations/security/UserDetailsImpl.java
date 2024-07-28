package miu.cs425.configurations.security;

import lombok.Getter;
import miu.cs425.models.Role;
import miu.cs425.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDetailsImpl implements UserDetails {

    private final Long userId;

    private final String username;

    private final String password;

    private final String firstname;

    private final String lastname;

    private final String email;

    private final List<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(User user, List<Role> roles) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.authorities = roles.stream().map(e -> new SimpleGrantedAuthority(e.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
