package org.beyond.library.account.biz;

import org.beyond.library.commons.model.AuthenticatedUser;
import org.beyond.library.commons.model.account.AuthorizationResult;

/**
 * @author Beyond
 */
public interface AccessManager {

    /**
     * 检查用户是有权限
     * @param user 用户
     * @param method 请求方法
     * @param url 请求url
     * @return
     */
    AuthorizationResult userHasPermission(AuthenticatedUser user, String method, final String url);

}
