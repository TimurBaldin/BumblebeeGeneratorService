package com.rufus.bumblebee.configurer.enums;

public enum UserRole {

    ADMIN("ADMIN"),
    USER("USER"),
    ANONYMOUS("ANONYMOUS");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
