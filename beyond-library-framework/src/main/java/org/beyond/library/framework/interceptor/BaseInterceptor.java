package org.beyond.library.framework.interceptor;

import javax.servlet.http.HttpServletResponse;

import org.beyond.library.commons.result.Result;
import org.beyond.library.commons.utils.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

public interface BaseInterceptor extends HandlerInterceptor {

    default void writeError(final HttpServletResponse response, final Result<?> result) throws Exception {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(JsonUtils.serializeAsBytes(result));
    }

}
