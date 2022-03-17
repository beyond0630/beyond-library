package org.beyond.library.framework.auth;

import javax.servlet.http.HttpServletRequest;

import org.beyond.library.commons.model.AuthenticatedUser;
import org.springframework.http.HttpHeaders;

/**
 * @author Beyond
 */
public interface HttpAuthenticator {

    /**
     * 执行身份认证
     *
     * @param request@return 如果已经完成身份认证，返回 true，否则返回 false 让下一个认证器处理
     */
    AuthenticatedUser authenticate(HttpServletRequest request);

    /**
     * 获取 token
     *
     * @param request request
     * @return token
     */
    default String getToken(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

}
