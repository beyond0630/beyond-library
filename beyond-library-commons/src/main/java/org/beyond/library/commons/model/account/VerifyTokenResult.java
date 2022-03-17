package org.beyond.library.commons.model.account;

import java.io.Serializable;
import java.util.Date;

public class VerifyTokenResult implements Serializable {

    private boolean success;

    private UserTokenClaims claims;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public UserTokenClaims getClaims() {
        return claims;
    }

    public void setClaims(final UserTokenClaims claims) {
        this.claims = claims;
    }

    public static VerifyTokenResult of(final boolean success, final UserTokenClaims claims) {
        VerifyTokenResult result = new VerifyTokenResult();
        result.setSuccess(success);
        result.setClaims(claims);
        return result;
    }

    public static class UserTokenClaims implements Serializable {

        private long userId;

        private String username;

        private String env;

        private String ipAddress;

        private Date expiration;

        public long getUserId() {
            return userId;
        }

        public void setUserId(final long userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(final String username) {
            this.username = username;
        }

        public String getEnv() {
            return env;
        }

        public void setEnv(final String env) {
            this.env = env;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(final String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public Date getExpiration() {
            return expiration;
        }

        public void setExpiration(final Date expiration) {
            this.expiration = expiration;
        }

    }


}
