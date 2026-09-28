package com.blood.bank.entity;

public class UserResponse {

    private Long userId;
    private String username;
    private String role;

    public UserResponse(Long userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}