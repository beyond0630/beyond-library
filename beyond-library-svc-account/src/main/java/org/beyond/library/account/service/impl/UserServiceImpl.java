package org.beyond.library.account.service.impl;

import java.time.LocalDateTime;

import org.apache.commons.codec.digest.DigestUtils;
import org.beyond.library.account.model.entity.User;
import org.beyond.library.account.model.param.SaveUserParams;
import org.beyond.library.account.repository.UserRepository;
import org.beyond.library.account.service.UserService;
import org.beyond.library.commons.model.account.UserVO;
import org.beyond.library.framework.common.IdFactory;
import org.beyond.library.framework.exception.ApiException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author Beyond
 */
@Service
public class UserServiceImpl implements UserService {

    private final IdFactory idFactory;
    private final UserRepository userRepository;

    public UserServiceImpl(final IdFactory idFactory, final UserRepository userRepository) {
        this.idFactory = idFactory;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(final SaveUserParams params) {
        boolean existsUsername = userRepository.existsByUsername(params.getUsername());
        if (existsUsername) {
            throw new ApiException("用户名已存在");
        }
        boolean existsEmail = userRepository.existsByEmail(params.getEmail());
        if (existsEmail) {
            throw new ApiException("邮箱已被注册");
        }

        User user = new User();
        user.setId(idFactory.generate());
        user.setUsername(params.getUsername());
        user.setPassword(DigestUtils.sha1Hex(params.getPassword()));
        user.setEmail(params.getEmail());
        user.setRegisterTime(LocalDateTime.now());
        user.setCreatedBy(user.getId());
        user.setModifiedBy(user.getId());
        userRepository.save(user);
    }

    @Override
    public UserVO findUser(final long id) {
        User user = userRepository.getById(id);
        UserVO result = new UserVO();
        BeanUtils.copyProperties(user, result);
        return result;
    }

}
