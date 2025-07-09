package com.example.publicapi.response;

import com.example.publicapi.dto.ListingDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateListingResponse {
    private ListingDTO listing;
}
