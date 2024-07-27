package miu.cs425.constants.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    SUPER_ADMIN("SUPER_ADMIN"), ADMIN("ADMIN"), USER("USER");

    private final String code;

    RoleEnum(String code) {
        this.code = code;
    }

    public RoleEnum getRoleByCode(String code) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getCode().equals(code)) {
                return roleEnum;
            }
        }
        return null;
    }
}
