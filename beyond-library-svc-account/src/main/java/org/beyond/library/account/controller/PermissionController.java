package org.beyond.library.account.controller;

import org.beyond.library.account.model.param.AddOrUpdatePermission;
import org.beyond.library.account.service.PermissionService;
import org.beyond.library.commons.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Beyond
 */
@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(final PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public Result<?> addPermission(@Validated @RequestBody AddOrUpdatePermission params) {
        permissionService.savePermission(params);
        return Result.ok();
    }


    @PutMapping("/{permissionId}")
    public Result<?> editPermission(@PathVariable int permissionId,
                                    @Validated @RequestBody AddOrUpdatePermission params) {
        permissionService.editPermission(permissionId, params);
        return Result.ok();
    }

    @DeleteMapping("/{permissionId}")
    public Result<?> deletePermission(@PathVariable int permissionId) {
        permissionService.deletePermission(permissionId);
        return Result.ok();
    }

}
