package org.beyond.library.account.controller;

import org.beyond.library.account.model.param.SaveUserParams;
import org.beyond.library.account.service.UserService;
import org.beyond.library.commons.annotation.AllowAnonymous;
import org.beyond.library.commons.model.AuthenticatedUser;
import org.beyond.library.commons.model.account.UserVO;
import org.beyond.library.commons.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Beyond
 */
@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;

    public UserController(final UserService userService) { this.userService = userService; }

    @AllowAnonymous
    @PostMapping
    public Result<?> saveUser(@Validated @RequestBody SaveUserParams params) {
        userService.saveUser(params);
        return Result.ok();
    }

    @GetMapping
    public Result<UserVO> getUser(AuthenticatedUser user) {
        return Result.ok(userService.findUser(user.getUserId()));
    }

}
