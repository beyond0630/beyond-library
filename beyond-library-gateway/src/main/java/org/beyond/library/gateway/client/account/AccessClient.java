package org.beyond.library.gateway.client.account;

import org.beyond.library.commons.model.account.AuthorizationParams;
import org.beyond.library.commons.model.account.AuthorizationResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author beyond
 * @date 2020/6/10 19:26
 */
@FeignClient(
    name = "beyond-library-svc-account",
    contextId = "accessClient",
    fallback = AccessClient.Fallback.class)
public interface AccessClient {

    @PostMapping(value = "/api/internal/access")
    AuthorizationResult authorize(@RequestBody AuthorizationParams params);

    class Fallback implements AccessClient {

        @Override
        public AuthorizationResult authorize(final AuthorizationParams params) {
            return AuthorizationResult.fail(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
        }

    }

}
