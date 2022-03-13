package org.beyond.library.account.service;

import java.util.NoSuchElementException;

import org.beyond.library.account.entity.Role;
import org.beyond.library.account.model.dto.RoleDTO;
import org.beyond.library.account.model.vo.RoleQueryVO;
import org.beyond.library.account.model.vo.RoleUpdateVO;
import org.beyond.library.account.model.vo.RoleVO;
import org.beyond.library.account.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Integer save(RoleVO vO) {
        Role bean = new Role();
        BeanUtils.copyProperties(vO, bean);
        bean = roleRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    public void update(Integer id, RoleUpdateVO vO) {
        Role bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        roleRepository.save(bean);
    }

    public RoleDTO getById(Integer id) {
        Role original = requireOne(id);
        return toDTO(original);
    }

    public Page<RoleDTO> query(RoleQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private RoleDTO toDTO(Role original) {
        RoleDTO bean = new RoleDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Role requireOne(Integer id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

}
