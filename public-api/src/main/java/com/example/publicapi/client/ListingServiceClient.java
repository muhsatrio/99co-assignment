package com.example.publicapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.publicapi.response.CreateListingResponse;
import com.example.publicapi.response.GetListingResponse;

@FeignClient(name = "listingService", url = "http://localhost:6000")
public interface ListingServiceClient {
    @PostMapping(value = "/listings", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    CreateListingResponse createListing(@RequestParam("user_id") int userId, @RequestParam("listing_type") String listingType, @RequestParam int price);

    @GetMapping(value = "/listings")
    GetListingResponse getListing(@RequestParam("page_num") int pageNum, @RequestParam("page_size") int pageSize, @RequestParam(value = "user_id") String userId);
}
