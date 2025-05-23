package com.rocketicg.app.infrastructure.user.web;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    private String newPassword;
}