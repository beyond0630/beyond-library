package org.beyond.library.account.controller;

import org.beyond.library.account.biz.TokenManager;
import org.beyond.library.account.model.param.LoginParams;
import org.beyond.library.account.service.AuthService;
import org.beyond.library.commons.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Beyond
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(final AuthService authService,
                                    final TokenManager tokenManager) {
        this.authService = authService;
    }

    @PostMapping
    public Result<String> login(@Validated @RequestBody LoginParams params) {
        String token = authService.login(params);
        return Result.data(token);
    }

}
