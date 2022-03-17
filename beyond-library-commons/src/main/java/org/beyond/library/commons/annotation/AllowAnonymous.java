package org.beyond.library.commons.annotation;

import java.lang.annotation.*;


/**
 * 标记一个请求或多个请求允许匿名访问
 *
 * @author Beyond
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AllowAnonymous {
}
