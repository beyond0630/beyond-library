package org.beyond.library.account.service;

import java.util.NoSuchElementException;

import org.beyond.library.account.entity.User;
import org.beyond.library.account.model.dto.UserDTO;
import org.beyond.library.account.model.vo.UserQueryVO;
import org.beyond.library.account.model.vo.UserUpdateVO;
import org.beyond.library.account.model.vo.UserVO;
import org.beyond.library.account.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Long save(UserVO vO) {
        User bean = new User();
        BeanUtils.copyProperties(vO, bean);
        bean = userRepository.save(bean);
        return bean.getId();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void update(Long id, UserUpdateVO vO) {
        User bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userRepository.save(bean);
    }

    public UserDTO getById(Long id) {
        User original = requireOne(id);
        return toDTO(original);
    }

    public Page<User> query(UserQueryVO vO) {
        return this.userRepository.findAll(PageRequest.of(1, 20));
    }

    private UserDTO toDTO(User original) {
        UserDTO bean = new UserDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private User requireOne(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

}
