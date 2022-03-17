package org.beyond.library.account.biz.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.beyond.library.commons.constant.HttpAuthConstants;
import org.beyond.library.commons.model.AuthenticatedUser;
import org.beyond.library.framework.auth.HttpAuthenticator;
import org.springframework.stereotype.Component;


/**
 * @author Beyond
 */
@Component
public class MsaHttpAuthenticator implements HttpAuthenticator {

    @Override
    public AuthenticatedUser authenticate(final HttpServletRequest request) {

        String userId = request.getHeader(HttpAuthConstants.HEADER_X_USER_ID);
        String username = request.getHeader(HttpAuthConstants.HEADER_X_USERNAME);

        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(username)) {
            return null;
        }

        return new AuthenticatedUser(Long.parseLong(userId), username);
    }

}
