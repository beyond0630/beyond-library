package org.beyond.library.gateway.client.account;

import org.beyond.library.commons.model.account.VerifyTokenResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author beyond
 * @date 2020/6/10 19:26
 */

@FeignClient(
    name = "beyond-library-svc-account",
    contextId = "tokenClient",
    fallback = TokenClient.Fallback.class)
public interface TokenClient {

    @PostMapping(value = "/api/internal/token/verify")
    VerifyTokenResult verifyToken(@RequestHeader(value = "Authorization") String token);

    @Component
    class Fallback implements TokenClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(Fallback.class);

        @Override
        public VerifyTokenResult verifyToken(final String token) {
            LOGGER.warn("No servers available fallback");
            return VerifyTokenResult.of(false, null);
        }

    }

}
