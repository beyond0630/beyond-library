package org.beyond.library.account.biz.impl;

import java.util.Set;

import org.beyond.library.account.biz.AccessManager;
import org.beyond.library.account.model.entity.Permission;
import org.beyond.library.account.repository.PermissionRepository;
import org.beyond.library.account.repository.RolePermissionRepository;
import org.beyond.library.account.service.RolePermissionService;
import org.beyond.library.account.service.UserRoleService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author Beyond
 */
@Component
public class AccessManagerImpl implements AccessManager {

    private final UserRoleService userRoleService;
    private final RolePermissionService rolePermissionService;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public AccessManagerImpl(final UserRoleService userRoleService,
                             final RolePermissionService rolePermissionService,
                             final PermissionRepository permissionRepository,
                             final RolePermissionRepository rolePermissionRepository) {
        this.userRoleService = userRoleService;
        this.rolePermissionService = rolePermissionService;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    public boolean userHasPermission(final long userId, final String permissionUrl) {
        Permission permission = permissionRepository.getByUrl(permissionUrl);
        if (permission == null) {
            return false;
        }
        Set<String> roles = userRoleService.listRoles(userId);
        if (CollectionUtils.isEmpty(roles)) {
            return false;
        }
        for (String role : roles) {
            if (this.roleHasPermission(role, permission)) {
                return true;
            }
        }
        return false;
    }


    private boolean roleHasPermission(final String roleCode, final Permission permission) {
        Set<String> permissions = rolePermissionService.listPermissionsByRoleCode(roleCode);
        if (CollectionUtils.isEmpty(permissions)) {
            return false;
        }
        return permissions.contains(permission.getCode());
    }

}
