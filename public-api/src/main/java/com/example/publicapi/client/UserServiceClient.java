package com.example.publicapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.publicapi.request.CreateUserRequest;
import com.example.publicapi.response.CreateUserResponse;
import com.example.publicapi.response.GetUserByIdResponse;

@FeignClient(name = "userService", url = "http://localhost:8000")
public interface UserServiceClient {
    @PostMapping("/users")
    CreateUserResponse createUser(@RequestBody CreateUserRequest request);

    @GetMapping("/users/{id}")
    GetUserByIdResponse getUserByID(@PathVariable("id") Long userId);
}
