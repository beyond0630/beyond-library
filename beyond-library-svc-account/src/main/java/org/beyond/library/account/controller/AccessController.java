package org.beyond.library.account.controller;

import org.beyond.library.account.biz.AccessManager;
import org.beyond.library.account.biz.TokenManager;
import org.beyond.library.commons.model.account.AuthorizationParams;
import org.beyond.library.commons.model.account.AuthorizationResult;
import org.beyond.library.commons.model.account.VerifyTokenResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Beyond
 */
@RestController
@RequestMapping("/api/internal")
public class AccessController {

    private final TokenManager tokenManager;
    private final AccessManager accessManager;

    public AccessController(final TokenManager tokenManager,
                            final AccessManager accessManager) {
        this.tokenManager = tokenManager;
        this.accessManager = accessManager; }

    @PostMapping("/token/verify")
    public VerifyTokenResult verifyToken(@RequestHeader(value = "Authorization") String token) {
        return tokenManager.verifyToken(token);
    }


    @PostMapping("/access")
    public AuthorizationResult authorize(@RequestBody AuthorizationParams params) {
        return accessManager.userHasPermission(params.getUser(), params.getMethod(), params.getUrl());
    }


}
