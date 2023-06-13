package com.example.noteapi.Client;

import com.example.noteapi.Entity.User;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserApi")
@LoadBalancerClient(name = "UserApi")
public interface UserServiceClient {

    @GetMapping("/users/search/{id}")
    User searchById(@PathVariable Long id);


}
