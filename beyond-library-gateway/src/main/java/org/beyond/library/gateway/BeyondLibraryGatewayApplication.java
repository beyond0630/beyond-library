package org.beyond.library.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BeyondLibraryGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeyondLibraryGatewayApplication.class, args);
    }

}
