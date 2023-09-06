package com.blog.blog.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;
@Data
public class UserDTO{
    UUID idUser;
    String username;
    String password;
    List<String> rolesName;
}
