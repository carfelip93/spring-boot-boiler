package com.rocketicg.app.domain.user;

import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private boolean active;

    public User(String username, String email, String password, String fullName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.active = true;
    }

    public void updateProfile(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }
}