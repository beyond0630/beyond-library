package org.beyond.library.gateway.client.account;

import org.beyond.library.commons.model.account.AuthorizationParams;
import org.beyond.library.commons.model.account.AuthorizationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
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

    @Component
    class Fallback implements AccessClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(Fallback.class);

        @Override
        public AuthorizationResult authorize(final AuthorizationParams params) {
            LOGGER.warn("No servers available fallback");
            return AuthorizationResult.fail(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
        }

    }

}
