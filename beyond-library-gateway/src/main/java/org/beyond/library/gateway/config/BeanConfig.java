package org.beyond.library.gateway.config;

import java.util.concurrent.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Beyond
 */
@Configuration
public class BeanConfig {

    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(4,8, 180, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(500));
    }
}
