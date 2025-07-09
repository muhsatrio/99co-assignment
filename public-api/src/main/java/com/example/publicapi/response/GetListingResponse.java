package com.example.publicapi.response;

import java.util.List;

import com.example.publicapi.dto.ListingDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListingResponse {
    private boolean result;
    private List<ListingDTO> listings;
}
