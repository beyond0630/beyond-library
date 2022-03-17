package org.beyond.library.account.config;

import org.beyond.library.framework.common.IdFactory;
import org.beyond.library.framework.common.impl.SnowflakeIdFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author Beyond
 */
@Configuration
public class BeanConfig {

    @Bean
    @Lazy
    public IdFactory idFactory() {
        return new SnowflakeIdFactory(0, 0);
    }

}
