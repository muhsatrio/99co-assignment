package com.example.publicapi.response;

import java.util.List;

import com.example.publicapi.dto.ListingUserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListingWithUserResponse {
    private boolean result;
    private List<ListingUserDTO> listings;
}
