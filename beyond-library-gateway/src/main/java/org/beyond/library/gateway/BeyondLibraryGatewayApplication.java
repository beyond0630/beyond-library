package org.beyond.library.gateway;

import org.beyond.library.gateway.loadbalancer.GrayLoadBalancerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@LoadBalancerClients(defaultConfiguration = GrayLoadBalancerConfiguration.class)
public class BeyondLibraryGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeyondLibraryGatewayApplication.class, args);
    }

}
