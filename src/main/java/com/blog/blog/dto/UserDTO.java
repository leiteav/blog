package com.blog.blog.dto;

import lombok.NonNull;

import java.util.UUID;

public class UserDTO{

    private UUID idUser;

    @NonNull
    String username;

    String password;
}
