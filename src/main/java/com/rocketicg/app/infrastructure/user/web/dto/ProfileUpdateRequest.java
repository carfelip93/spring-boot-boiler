package com.rocketicg.app.infrastructure.user.web.dto;

import lombok.Data;

@Data
public class ProfileUpdateRequest {
    private String fullName;
    private String email;
}