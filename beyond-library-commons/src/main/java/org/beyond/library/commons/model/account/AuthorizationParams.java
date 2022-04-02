package org.beyond.library.commons.model.account;

import java.io.Serializable;

import org.beyond.library.commons.model.AuthenticatedUser;

/**
 * @author Beyond
 */
public class AuthorizationParams implements Serializable {

    private String method;

    private String url;

    private AuthenticatedUser user;

    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public AuthenticatedUser getUser() {
        return user;
    }

    public void setUser(final AuthenticatedUser user) {
        this.user = user;
    }

}
