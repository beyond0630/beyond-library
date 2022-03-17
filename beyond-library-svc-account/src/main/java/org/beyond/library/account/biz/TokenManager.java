package org.beyond.library.account.biz;

import org.beyond.library.commons.model.account.VerifyTokenResult;

/**
 * @author Beyond
 */
public interface TokenManager {

    /**
     * 验证 token
     *
     * @param token token
     * @return 如果验证成功返回用户 id, 不成功返回 -1;
     */
    VerifyTokenResult verifyToken(String token);

    /**
     * 创建 token
     *
     * @param userId   用户 Id
     * @param username 用戶名
     * @return token
     */
    String createToken(long userId, final String username);


}
