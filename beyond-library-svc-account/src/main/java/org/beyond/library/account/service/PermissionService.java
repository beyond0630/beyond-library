package org.beyond.library.account.service;

import java.util.NoSuchElementException;

import org.beyond.library.account.entity.Permission;
import org.beyond.library.account.model.dto.PermissionDTO;
import org.beyond.library.account.model.vo.PermissionQueryVO;
import org.beyond.library.account.model.vo.PermissionUpdateVO;
import org.beyond.library.account.model.vo.PermissionVO;
import org.beyond.library.account.repository.PermissionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Integer save(PermissionVO vO) {
        Permission bean = new Permission();
        BeanUtils.copyProperties(vO, bean);
        bean = permissionRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        permissionRepository.deleteById(id);
    }

    public void update(Integer id, PermissionUpdateVO vO) {
        Permission bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        permissionRepository.save(bean);
    }

    public PermissionDTO getById(Integer id) {
        Permission original = requireOne(id);
        return toDTO(original);
    }

    public Page<PermissionDTO> query(PermissionQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private PermissionDTO toDTO(Permission original) {
        PermissionDTO bean = new PermissionDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Permission requireOne(Integer id) {
        return permissionRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

}
