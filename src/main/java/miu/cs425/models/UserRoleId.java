package miu.cs425.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class UserRoleId implements Serializable {

    private Long userId;

    private Long roleId;

    public UserRoleId() {
    }

    public UserRoleId(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
