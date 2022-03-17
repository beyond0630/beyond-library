package org.beyond.library.commons.model;

import java.io.Serializable;

public class AuthenticatedUser implements Serializable {


    private Long userId;

    private String name;

    public AuthenticatedUser() {
    }

    public AuthenticatedUser(final Long userId, final String name) {
        this.userId = userId;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
