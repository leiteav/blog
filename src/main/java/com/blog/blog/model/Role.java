package com.blog.blog.model;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue
    private UUID idRole;

    private String name;

    public Role(UUID id) {
        this.idRole = id;
    }
    public Role(String name) {
        this.name = name;
    }


}