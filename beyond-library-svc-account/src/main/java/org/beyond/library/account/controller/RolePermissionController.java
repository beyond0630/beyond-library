package org.beyond.library.account.controller;

import org.beyond.library.account.model.param.PermissionCode;
import org.beyond.library.account.service.RolePermissionService;
import org.beyond.library.commons.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Beyond
 */
@RestController
@RequestMapping("/api/roles/{roleCode}/permissions")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    public RolePermissionController(final RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @PostMapping
    public Result<?> grant(@PathVariable String roleCode,
                           @Validated @RequestBody PermissionCode params) {
        rolePermissionService.grant(roleCode, params.getCode());
        return Result.ok();
    }

    @DeleteMapping("/{rolePermissionId}")
    public Result<?> revoke(@PathVariable String roleCode,
                            @PathVariable int rolePermissionId) {
        rolePermissionService.revoke(rolePermissionId);
        return Result.ok();
    }

}
