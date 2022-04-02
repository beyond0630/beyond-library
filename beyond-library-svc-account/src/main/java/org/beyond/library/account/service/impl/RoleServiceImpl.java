package org.beyond.library.account.service.impl;

import org.beyond.library.account.model.entity.Role;
import org.beyond.library.account.model.param.AddOrUpdateRole;
import org.beyond.library.account.repository.RolePermissionRepository;
import org.beyond.library.account.repository.RoleRepository;
import org.beyond.library.account.repository.UserRoleRepository;
import org.beyond.library.account.service.RoleService;
import org.beyond.library.framework.exception.ApiException;
import org.beyond.library.framework.exception.DataNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Beyond
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public RoleServiceImpl(final RoleRepository roleRepository,
                           final UserRoleRepository userRoleRepository,
                           final RolePermissionRepository rolePermissionRepository) {
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    public void saveRole(final AddOrUpdateRole params) {
        boolean exists = roleRepository.existsByCode(params.getCode());
        if (exists) {
            throw new ApiException("已存在code=" + params.getCode() + "的角色");
        }

        Role role = new Role();
        role.setCode(params.getCode());
        role.setName(params.getName());
        role.setDisabled(false);
        roleRepository.save(role);
    }

    @Override
    public void editRole(final int id, final AddOrUpdateRole params) {
        Role role = roleRepository.findById(id)
            .orElseThrow(DataNotFoundException::new);
        role.setName(params.getName());
        roleRepository.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(final int id) {
        Role role = roleRepository.findById(id)
            .orElseThrow(DataNotFoundException::new);
        userRoleRepository.deleteByRoleCode(role.getCode());
        rolePermissionRepository.deleteByRoleCode(role.getCode());
        roleRepository.deleteById(id);
    }

}
