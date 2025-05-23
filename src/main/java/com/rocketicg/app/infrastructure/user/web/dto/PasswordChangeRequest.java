package com.rocketicg.app.infrastructure.user.web.dto;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    private String newPassword;
}