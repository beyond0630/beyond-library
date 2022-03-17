package org.beyond.library.account.service;


import org.beyond.library.account.model.param.LoginParams;

/**
 * @author Beyond
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param params 登录参数
     * @return token
     */
    String login(LoginParams params);

}
