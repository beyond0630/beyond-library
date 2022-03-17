package org.beyond.library.framework.interceptor;

import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beyond.library.commons.annotation.AllowAnonymous;
import org.beyond.library.commons.constant.HttpAuthConstants;
import org.beyond.library.commons.model.AuthenticatedUser;
import org.beyond.library.commons.result.Result;
import org.beyond.library.framework.auth.HttpAuthenticator;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;

/**
 * @author Beyond
 */
public class AuthenticationInterceptor implements BaseInterceptor {

    private final HttpAuthenticator httpAuthenticator;

    public AuthenticationInterceptor(final HttpAuthenticator httpAuthenticator) {
        this.httpAuthenticator = httpAuthenticator;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return BaseInterceptor.super.preHandle(request, response, handler);
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        AllowAnonymous allowAnonymous = Optional.ofNullable(handlerMethod.getMethodAnnotation(AllowAnonymous.class))
            .orElseGet(() -> handlerMethod.getBeanType().getAnnotation(AllowAnonymous.class));
        if (allowAnonymous != null) {
            return BaseInterceptor.super.preHandle(request, response, handler);
        }
        AuthenticatedUser user = this.httpAuthenticator.authenticate(request);
        if (Objects.isNull(user)) {
            writeError(response, Result.make(HttpStatus.UNAUTHORIZED.getReasonPhrase(), "未登录或登录信息已过期", null));
            return false;
        }

        request.setAttribute(HttpAuthConstants.HEADER_X_USER, user);
        return BaseInterceptor.super.preHandle(request, response, handler);
    }


}
