package com.example.gateway.Client;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserApi")
@LoadBalancerClient(name = "UserApi")
public interface UserServiceClient {

    @GetMapping("/users/searchEmail/{email}")
    UserDTO findByEmail(@PathVariable String email);

}
