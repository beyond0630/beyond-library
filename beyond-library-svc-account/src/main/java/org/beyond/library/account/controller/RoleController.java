package org.beyond.library.account.controller;

import org.beyond.library.account.model.param.AddOrUpdateRole;
import org.beyond.library.account.service.RoleService;
import org.beyond.library.commons.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Beyond
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public Result<?> addRole(@Validated @RequestBody AddOrUpdateRole params) {
        roleService.saveRole(params);
        return Result.ok();
    }

    @PutMapping("/{roleId}")
    public Result<?> editRole(@PathVariable int roleId,
                              @Validated @RequestBody AddOrUpdateRole params) {
        roleService.editRole(roleId, params);
        return Result.ok();
    }

    @DeleteMapping("/{roleId}")
    public Result<?> deleteRole(@PathVariable int roleId) {
        roleService.deleteRole(roleId);
        return Result.ok();
    }

}
