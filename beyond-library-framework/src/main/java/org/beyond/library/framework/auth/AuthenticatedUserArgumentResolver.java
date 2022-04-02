package org.beyond.library.framework.auth;

import javax.servlet.http.HttpServletRequest;

import org.beyond.library.commons.constant.HttpAuthConstants;
import org.beyond.library.commons.model.AuthenticatedUser;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthenticatedUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterType().equals(AuthenticatedUser.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request == null) {
            return null;
        }

        String userId = request.getHeader(HttpAuthConstants.HEADER_X_USER_ID);
        String username = request.getHeader(HttpAuthConstants.HEADER_X_USERNAME);
        if (StringUtils.hasText(userId)) {
            return new AuthenticatedUser(Long.parseLong(userId), username);
        }
        return null;
    }

}
