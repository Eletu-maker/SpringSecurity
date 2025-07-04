package com.Usman.SpringSecurity.DTO.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
