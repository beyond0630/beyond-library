package org.beyond.library.account.service;


import org.beyond.library.account.model.param.SaveUserParams;
import org.beyond.library.commons.model.account.UserVO;

/**
 * @author Beyond
 */
public interface UserService {

    /**
     * 保存新用户
     *
     * @param params 参数
     */
    void saveUser(SaveUserParams params);

    /**
     * 查找用户
     *
     * @param id 用户id
     * @return user
     */
    UserVO findUser(long id);

}
