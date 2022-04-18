package org.beyond.library.gateway.filter;


import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.beyond.library.commons.constant.HttpAuthConstants;
import org.beyond.library.commons.model.AuthenticatedUser;
import org.beyond.library.commons.model.account.AuthorizationParams;
import org.beyond.library.commons.model.account.AuthorizationResult;
import org.beyond.library.gateway.client.account.AccessClient;
import org.beyond.library.gateway.utils.ApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * @author beyond
 * @date 2020/6/10 18:55
 */
@Component
public class AuthorizationGatewayFilterFactory extends BaseGatewayFilterFactory<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationGatewayFilterFactory.class);

    public AuthorizationGatewayFilterFactory() {
        LOGGER.info("Loaded GatewayFilterFactory [Authorization]");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String method = request.getMethodValue();
            RequestPath path = request.getPath();

            AuthenticatedUser user = exchange.getAttribute(HttpAuthConstants.HEADER_X_USER);
            AuthorizationParams params = new AuthorizationParams();
            params.setMethod(method);
            params.setUrl(path.pathWithinApplication().value());
            params.setUser(user);

            AccessClient accessClient = ApplicationContextUtils.getBean(AccessClient.class);
            try {
                AuthorizationResult result = super.remoteCall(() -> accessClient.authorize(params));
                if (!result.isSuccess()) {
                    return super.writeObject(exchange, HttpStatus.UNAUTHORIZED, result);
                }
                return chain.filter(exchange);
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error(e.getMessage(), e);
                return super.writeObject(exchange, HttpStatus.SERVICE_UNAVAILABLE);
            }
        };
    }


}
