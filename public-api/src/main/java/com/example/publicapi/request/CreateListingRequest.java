package com.example.publicapi.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateListingRequest {
    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("listing_type")
    private String listingType;

    private int price;
}
