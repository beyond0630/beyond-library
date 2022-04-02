package org.beyond.library.commons.model.account;

import java.io.Serializable;

/**
 * @author Beyond
 */
public class AuthorizationResult implements Serializable {

    private static final int SUCCESS = 1;

    private static final int FAIL = 0;

    private boolean success;

    private int code;

    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public static AuthorizationResult success() {
        return of(SUCCESS, null);
    }

    public static AuthorizationResult fail(final String message) {
        return of(FAIL, message);
    }

    public static AuthorizationResult of(final int code,
                                         final String message) {
        AuthorizationResult result = new AuthorizationResult();
        result.setSuccess(SUCCESS == code);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }


}
