package org.beyond.library.account.biz.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.beyond.library.account.biz.TokenManager;
import org.beyond.library.commons.model.AuthenticatedUser;
import org.beyond.library.commons.model.account.VerifyTokenResult;
import org.beyond.library.framework.auth.HttpAuthenticator;


/**
 * @author Beyond
 */
//@Component
public class DefaultHttpAuthenticator implements HttpAuthenticator {

    private final TokenManager tokenManager;

    public DefaultHttpAuthenticator(final TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public AuthenticatedUser authenticate(final HttpServletRequest request) {
        String token = this.getToken(request);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        VerifyTokenResult result = tokenManager.verifyToken(token);
        if (!result.isSuccess()) {
            return null;
        }
        VerifyTokenResult.UserTokenClaims claims = result.getClaims();
        return new AuthenticatedUser(claims.getUserId(), claims.getUsername());
    }

}
