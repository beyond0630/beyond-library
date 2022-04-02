package org.beyond.library.account.model.param;

import java.io.Serializable;

/**
 * @author Beyond
 */
public class AddOrUpdatePermission implements Serializable {

    private String code;

    private String name;

    private String method;

    private String pattern;

    private boolean allowAnonymous;

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    public boolean isAllowAnonymous() {
        return allowAnonymous;
    }

    public void setAllowAnonymous(final boolean allowAnonymous) {
        this.allowAnonymous = allowAnonymous;
    }

}
