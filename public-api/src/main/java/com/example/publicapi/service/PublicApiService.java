package com.example.publicapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.publicapi.client.ListingServiceClient;
import com.example.publicapi.client.UserServiceClient;
import com.example.publicapi.dto.ListingDTO;
import com.example.publicapi.dto.ListingUserDTO;
import com.example.publicapi.request.CreateListingRequest;
import com.example.publicapi.request.CreateUserRequest;
import com.example.publicapi.response.CreateListingResponse;
import com.example.publicapi.response.CreateUserResponse;
import com.example.publicapi.response.GetListingResponse;
import com.example.publicapi.response.GetListingWithUserResponse;
import com.example.publicapi.response.GetUserByIdResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicApiService {

    private final UserServiceClient userServiceClient;
    private final ListingServiceClient listingServiceClient;

    public CreateUserResponse createUser(CreateUserRequest request) {
        return userServiceClient.createUser(request);
    }

    public CreateListingResponse createListing(CreateListingRequest request) {
        return listingServiceClient.createListing(request.getUserId(), request.getListingType(), request.getPrice());
    }

    public GetListingWithUserResponse getListing(int pageNum, int pageSize, String userId) {
        var resp = listingServiceClient.getListing(pageNum, pageSize, userId);

        List<ListingUserDTO> listingUserDTOs = new ArrayList<>();

        for (ListingDTO listingDTO : resp.getListings()) {
            GetUserByIdResponse getUserByIdResponse = userServiceClient.getUserByID(listingDTO.getUserId());
            ListingUserDTO temp = ListingUserDTO.builder()
                .id(listingDTO.getId())
                .user(getUserByIdResponse.getUser())
                .listingType(listingDTO.getListingType())
                .price(listingDTO.getPrice())
                .createdAt(listingDTO.getCreatedAt())
                .updatedAt(listingDTO.getUpdatedAt())
                .build();
            listingUserDTOs.add(temp);
        }
        
        return GetListingWithUserResponse.builder()
            .result(resp.isResult())
            .listings(listingUserDTOs)
            .build();
    }

}
