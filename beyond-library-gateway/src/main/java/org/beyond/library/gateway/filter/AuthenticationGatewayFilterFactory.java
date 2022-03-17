package org.beyond.library.gateway.filter;


import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.beyond.library.commons.constant.HttpAuthConstants;
import org.beyond.library.commons.model.AuthenticatedUser;
import org.beyond.library.commons.model.account.VerifyTokenResult;
import org.beyond.library.gateway.client.TokenClient;
import org.beyond.library.gateway.utils.ApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * @author beyond
 * @date 2020/6/10 18:55
 */
@Component
public class AuthenticationGatewayFilterFactory extends BaseGatewayFilterFactory<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationGatewayFilterFactory.class);


    ExecutorService executorService = Executors.newFixedThreadPool(1);

    public AuthenticationGatewayFilterFactory() {
        LOGGER.info("Loaded GatewayFilterFactory [Authentication]");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String token = getToken(request);
            if (StringUtils.isEmpty(token)) {
                removeHeaders(request);
                return chain.filter(exchange);
            }
            TokenClient authServiceClient = ApplicationContextUtils.getBean(TokenClient.class);
            Future<VerifyTokenResult> future = executorService.submit(() -> authServiceClient.verifyToken(token));
            while (!future.isDone()) {
                try {
                    TimeUnit.MICROSECONDS.sleep(10000);
                } catch (InterruptedException e) {
                    return super.writeObject(exchange, HttpStatus.SERVICE_UNAVAILABLE);
                }
            }
            VerifyTokenResult result;
            try {
                result = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return super.writeObject(exchange, HttpStatus.SERVICE_UNAVAILABLE);
            }
            if (!result.isSuccess()) {
                removeHeaders(request);
                return chain.filter(exchange);
            } else {
                VerifyTokenResult.UserTokenClaims claims = result.getClaims();
                AuthenticatedUser user = new AuthenticatedUser(claims.getUserId(), claims.getUsername());
                exchange.getAttributes().putIfAbsent(HttpAuthConstants.HEADER_X_USER, user);

                request.mutate()
                    .headers(httpHeaders -> {
                        httpHeaders.set(HttpAuthConstants.HEADER_X_USER_ID, Long.toString(user.getUserId()));
                        httpHeaders.set(HttpAuthConstants.HEADER_X_USERNAME, user.getName());
                    });

            }
            return chain.filter(exchange);
        };
    }

    private void removeHeaders(ServerHttpRequest request) {
        request.mutate()
            .headers(httpHeaders -> {
                httpHeaders.remove(HttpAuthConstants.HEADER_X_USER_ID);
                httpHeaders.remove(HttpAuthConstants.HEADER_X_USERNAME);
            });
    }

    private String getToken(final ServerHttpRequest request) {
        return this.getTokenFromHeader(request.getHeaders());
    }

    private String getTokenFromHeader(final HttpHeaders headers) {
        final String authValue = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (Strings.isNullOrEmpty(authValue)) {
            return null;
        }
        return authValue;
    }

}
