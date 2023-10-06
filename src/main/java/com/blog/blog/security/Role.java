package com.blog.blog.security;

public enum Role {

    DEFAULT("default"),
    ADMIN("admin");
    private String value;

    Role(String value) {
        this.value = value;
    }
}
