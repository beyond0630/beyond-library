package org.beyond.library.account.controller;

import org.beyond.library.account.model.param.RoleCode;
import org.beyond.library.account.service.UserRoleService;
import org.beyond.library.commons.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Beyond
 */
@RestController
@RequestMapping("/api/users/{userId}/roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(final UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping
    public Result<?> assignRole(@PathVariable long userId,
                                @Validated @RequestBody RoleCode params) {
        userRoleService.assignRole(userId, params.getCode());
        return Result.ok();
    }

    @DeleteMapping("/{userRoleId}")
    public Result<?> removeRole(@PathVariable long userId,
                                @PathVariable int userRoleId) {
        userRoleService.removeRole(userRoleId);
        return Result.ok();
    }

}
