package miu.cs425.constants.enums;

import lombok.Getter;

@Getter
public enum TokenTypeEnum {

    BEARER("bearer");

    private final String value;

    TokenTypeEnum(String value) {
        this.value = value;
    }

}
