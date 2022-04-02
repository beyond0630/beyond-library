package org.beyond.library.account.biz.impl;

import java.util.Set;

import org.beyond.library.account.biz.AccessManager;
import org.beyond.library.account.model.entity.Permission;
import org.beyond.library.account.service.PermissionService;
import org.beyond.library.account.service.RolePermissionService;
import org.beyond.library.account.service.UserRoleService;
import org.beyond.library.commons.model.AuthenticatedUser;
import org.beyond.library.commons.model.account.AuthorizationResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author Beyond
 */
@Component
public class DefaultAccessManager implements AccessManager {

    private final UserRoleService userRoleService;
    private final PermissionService permissionService;
    private final RolePermissionService rolePermissionService;

    public DefaultAccessManager(final UserRoleService userRoleService,
                                final PermissionService permissionService,
                                final RolePermissionService rolePermissionService) {
        this.userRoleService = userRoleService;
        this.permissionService = permissionService;
        this.rolePermissionService = rolePermissionService;
    }

    @Override
    public AuthorizationResult userHasPermission(final AuthenticatedUser user, final String method, final String url) {
        Permission permission = permissionService.getPermission(method, url);
        if (permission == null) {
            return AuthorizationResult.fail("Permission not exists!");
        }
        if (permission.isAllowAnonymous()) {
            return AuthorizationResult.success();
        }
        if (user == null) {
            return AuthorizationResult.of(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        Set<String> roles = userRoleService.listRoles(user.getUserId());
        if (CollectionUtils.isEmpty(roles)) {
            return AuthorizationResult.of(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
        }
        for (String role : roles) {
            if (this.roleHasPermission(role, permission)) {
                return AuthorizationResult.success();
            }
        }
        return AuthorizationResult.of(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
    }


    private boolean roleHasPermission(final String roleCode, final Permission permission) {
        Set<String> permissions = rolePermissionService.listPermissionsByRoleCode(roleCode);
        if (CollectionUtils.isEmpty(permissions)) {
            return false;
        }
        return permissions.contains(permission.getCode());
    }

}
