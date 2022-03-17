package org.beyond.library.account.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.beyond.library.account.config.CacheConfig;
import org.beyond.library.account.model.entity.Permission;
import org.beyond.library.account.model.entity.Role;
import org.beyond.library.account.model.entity.RolePermission;
import org.beyond.library.account.repository.PermissionRepository;
import org.beyond.library.account.repository.RolePermissionRepository;
import org.beyond.library.account.repository.RoleRepository;
import org.beyond.library.account.service.RolePermissionService;
import org.beyond.library.framework.exception.ApiException;
import org.ehcache.Cache;
import org.springframework.stereotype.Service;

/**
 * @author Beyond
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final Cache<String, CacheConfig.RolePermissionWrapper> cache;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public RolePermissionServiceImpl(final Cache<String, CacheConfig.RolePermissionWrapper> cache,
                                     final RoleRepository roleRepository,
                                     final PermissionRepository permissionRepository,
                                     final RolePermissionRepository rolePermissionRepository) {
        this.cache = cache;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    public Set<String> listPermissionsByRoleCode(final String roleCode) {
        CacheConfig.RolePermissionWrapper rolePermissionWrapper = cache.get(roleCode);
        if (rolePermissionWrapper != null) {
            return rolePermissionWrapper.getPermissions();
        }
        Set<String> permissions = rolePermissionRepository.getAllByRoleCode(roleCode)
            .stream()
            .filter(x -> !x.isDisabled())
            .map(RolePermission::getPermissionCode)
            .collect(Collectors.toSet());
        cache.put(roleCode, new CacheConfig.RolePermissionWrapper(permissions));
        return permissions;
    }

    @Override
    public void grant(final String roleCode, final String permissionCode) {
        Role role = roleRepository.getByCode(roleCode);
        if (role == null) {
            throw new ApiException("角色不存在");
        }

        Permission permission = permissionRepository.getByCode(permissionCode);
        if (permission == null) {
            throw new ApiException("权限不存在");
        }

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleCode(roleCode);
        rolePermission.setPermissionCode(permissionCode);
        rolePermissionRepository.save(rolePermission);
        cache.remove(roleCode);
    }

    @Override
    public void revoke(final int rolePermissionId) {
        rolePermissionRepository.findById(rolePermissionId)
            .ifPresent(x -> {
                rolePermissionRepository.deleteById(rolePermissionId);
                cache.remove(x.getRoleCode());
            });
    }

}
