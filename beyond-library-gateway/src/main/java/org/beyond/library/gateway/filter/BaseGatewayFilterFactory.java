package org.beyond.library.gateway.filter;

import java.util.concurrent.*;

import org.beyond.library.commons.result.Result;
import org.beyond.library.commons.utils.JsonUtils;
import org.beyond.library.gateway.utils.ApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public abstract class BaseGatewayFilterFactory<C> extends AbstractGatewayFilterFactory<C> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseGatewayFilterFactory.class);

    Mono<Void> writeObject(final ServerWebExchange exchange,
                           final HttpStatus httpStatus) {
        return this.writeObject(exchange, httpStatus,
            Result.make(String.valueOf(httpStatus.value()), httpStatus.getReasonPhrase(), null));
    }

    Mono<Void> writeObject(final ServerWebExchange exchange,
                           final HttpStatus httpStatus,
                           final Object object) {
        ServerWebExchangeUtils.setResponseStatus(exchange, httpStatus);
        ServerHttpResponse response = exchange.getResponse();

        byte[] bytes = JsonUtils.serializeAsBytes(object);

        HttpHeaders headers = response.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentLength(bytes.length);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(buffer));
    }

    <T> T remoteCall(final Callable<T> callable) throws InterruptedException, ExecutionException {
        ExecutorService executorService = ApplicationContextUtils.getBean(ExecutorService.class);
        Future<T> future = executorService.submit(callable);
        while (!future.isDone()) {
            TimeUnit.MICROSECONDS.sleep(10000);
        }
        return future.get();
    }

}
