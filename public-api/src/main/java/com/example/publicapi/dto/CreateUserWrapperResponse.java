package com.example.publicapi.dto;

import com.example.publicapi.response.CreateUserResponse;

import lombok.Data;

@Data
public class CreateUserWrapperResponse {
    private CreateUserResponse user;
}
