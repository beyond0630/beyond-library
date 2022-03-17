package org.beyond.library.framework.config;

import java.util.List;

import org.beyond.library.framework.auth.AuthenticatedUserArgumentResolver;
import org.beyond.library.framework.auth.HttpAuthenticator;
import org.beyond.library.framework.interceptor.AuthenticationInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Beyond
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {


    private final ApplicationContext applicationContext;

    public MvcConfig(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(applicationContext.getBean(HttpAuthenticator.class)))
            .addPathPatterns("/api/**");
//        registry.addInterceptor(new AuthorizationInterceptor(applicationContext.getBean(HttpAuthorizer.class)))
//            .addPathPatterns("/api/**");
    }


    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticatedUserArgumentResolver());
    }

}
