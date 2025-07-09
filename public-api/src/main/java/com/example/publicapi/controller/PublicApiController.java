package com.example.publicapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.publicapi.request.CreateListingRequest;
import com.example.publicapi.request.CreateUserRequest;
import com.example.publicapi.response.CreateListingResponse;
import com.example.publicapi.response.CreateUserResponse;
import com.example.publicapi.response.GetListingResponse;
import com.example.publicapi.response.GetListingWithUserResponse;
import com.example.publicapi.service.PublicApiService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/public-api")
public class PublicApiController {
    
    private final PublicApiService publicApiService;

    @PostMapping("/users")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        return publicApiService.createUser(request);
    }

    @PostMapping("/listings")
    public CreateListingResponse createListing(@RequestBody CreateListingRequest request) {
        return publicApiService.createListing(request);
    }

    @GetMapping(value = "/listings")
    public GetListingWithUserResponse getListing(@RequestParam("page_num") int pageNum, @RequestParam("page_size") int pageSize, @RequestParam(value = "user_id") String userId) {
        return publicApiService.getListing(pageNum, pageSize, userId);
    }
}
