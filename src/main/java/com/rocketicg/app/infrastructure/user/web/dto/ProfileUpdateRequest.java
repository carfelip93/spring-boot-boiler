package com.rocketicg.app.infrastructure.user.web;

import lombok.Data;

@Data
public class ProfileUpdateRequest {
    private String fullName;
    private String email;
}