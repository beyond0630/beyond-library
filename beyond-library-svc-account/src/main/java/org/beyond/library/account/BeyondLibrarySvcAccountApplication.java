package org.beyond.library.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Beyond
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = { "org.beyond.library.account", "org.beyond.library.framework" })
public class BeyondLibrarySvcAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeyondLibrarySvcAccountApplication.class, args);
    }
}
