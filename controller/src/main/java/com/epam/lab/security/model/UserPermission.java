package com.epam.lab.security.model;

public enum UserPermission {
    TAGS_POST("tags:post"),
    TAGS_PUT("tags:put"),
    TAGS_DELETE("tags:delete"),
    AUTHORS_POST("authors:post"),
    AUTHORS_PUT("authors:put"),
    AUTHORS_DELETE("authors:delete"),
    NEWS_POST("news:post"),
    NEWS_PUT("news:put"),
    NEWS_DELETE("news:delete"),
    USERS_GET("users:get"),
    USERS_POST("users:post"),
    USERS_PUT("users:put"),
    USERS_DELETE("users:delete");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
