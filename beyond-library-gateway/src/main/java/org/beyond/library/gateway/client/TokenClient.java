package org.beyond.library.gateway.client;

import org.beyond.library.commons.model.account.VerifyTokenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author beyond
 * @date 2020/6/10 19:26
 */
@FeignClient(name = "beyond-library-svc-account", fallback = TokenClient.Fallback.class)
public interface TokenClient {

    @PostMapping(value = "/api/token/verify")
    VerifyTokenResult verifyToken(@RequestHeader(value = "Authorization") String token);

    class Fallback implements TokenClient {

        @Override
        public VerifyTokenResult verifyToken(final String token) {
            return VerifyTokenResult.of(false, null);
        }

    }

}
