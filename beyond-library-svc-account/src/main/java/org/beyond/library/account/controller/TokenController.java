package org.beyond.library.account.controller;

import org.beyond.library.account.biz.TokenManager;
import org.beyond.library.account.model.param.LoginParams;
import org.beyond.library.account.service.AuthService;
import org.beyond.library.commons.annotation.AllowAnonymous;
import org.beyond.library.commons.model.account.VerifyTokenResult;
import org.beyond.library.commons.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Beyond
 */
@RestController
@RequestMapping("/api/token")
public class TokenController {

    private final AuthService authService;
    private final TokenManager tokenManager;

    public TokenController(final AuthService authService,
                           final TokenManager tokenManager) {
        this.authService = authService;
        this.tokenManager = tokenManager;
    }

    @AllowAnonymous
    @PostMapping
    public Result<String> login(@Validated @RequestBody LoginParams params) {
        String token = authService.login(params);
        return Result.data(token);
    }

    @AllowAnonymous
    @PostMapping("/verify")
    public VerifyTokenResult verifyToken(@RequestHeader(value = "Authorization") String token) {
        return tokenManager.verifyToken(token);
    }

}
