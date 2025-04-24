package com.senior.project.enums;

import static com.senior.project.constant.Authority.ADMIN_AUTHORITIES;
import static com.senior.project.constant.Authority.USER_AUTHORITIES;

public enum Role {
    USER(USER_AUTHORITIES),
    MANAGER(ADMIN_AUTHORITIES),
    ADMIN(ADMIN_AUTHORITIES);

    private String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
