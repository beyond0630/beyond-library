package org.beyond.library.account.service;

import java.util.NoSuchElementException;

import org.beyond.library.account.entity.RolePermission;
import org.beyond.library.account.model.dto.RolePermissionDTO;
import org.beyond.library.account.model.vo.RolePermissionQueryVO;
import org.beyond.library.account.model.vo.RolePermissionUpdateVO;
import org.beyond.library.account.model.vo.RolePermissionVO;
import org.beyond.library.account.repository.RolePermissionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    public Integer save(RolePermissionVO vO) {
        RolePermission bean = new RolePermission();
        BeanUtils.copyProperties(vO, bean);
        bean = rolePermissionRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        rolePermissionRepository.deleteById(id);
    }

    public void update(Integer id, RolePermissionUpdateVO vO) {
        RolePermission bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        rolePermissionRepository.save(bean);
    }

    public RolePermissionDTO getById(Integer id) {
        RolePermission original = requireOne(id);
        return toDTO(original);
    }

    public Page<RolePermissionDTO> query(RolePermissionQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private RolePermissionDTO toDTO(RolePermission original) {
        RolePermissionDTO bean = new RolePermissionDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private RolePermission requireOne(Integer id) {
        return rolePermissionRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

}
