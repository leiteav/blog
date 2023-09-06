package com.blog.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "USUARIO")
public class User {

    @Id
    @GeneratedValue
    private UUID idUser;

    private String name;

    private String username;

    private String password;

    @ManyToMany
    private List<Role> role;
}
