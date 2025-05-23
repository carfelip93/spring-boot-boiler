package com.rocketicg.app.infrastructure.user.web.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private String fullName;
}