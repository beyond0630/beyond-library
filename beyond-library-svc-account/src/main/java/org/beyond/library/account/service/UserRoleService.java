package org.beyond.library.account.service;

import java.util.NoSuchElementException;

import org.beyond.library.account.entity.UserRole;
import org.beyond.library.account.model.dto.UserRoleDTO;
import org.beyond.library.account.model.vo.UserRoleQueryVO;
import org.beyond.library.account.model.vo.UserRoleUpdateVO;
import org.beyond.library.account.model.vo.UserRoleVO;
import org.beyond.library.account.repository.UserRoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public Integer save(UserRoleVO vO) {
        UserRole bean = new UserRole();
        BeanUtils.copyProperties(vO, bean);
        bean = userRoleRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        userRoleRepository.deleteById(id);
    }

    public void update(Integer id, UserRoleUpdateVO vO) {
        UserRole bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userRoleRepository.save(bean);
    }

    public UserRoleDTO getById(Integer id) {
        UserRole original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserRoleDTO> query(UserRoleQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserRoleDTO toDTO(UserRole original) {
        UserRoleDTO bean = new UserRoleDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private UserRole requireOne(Integer id) {
        return userRoleRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

}
