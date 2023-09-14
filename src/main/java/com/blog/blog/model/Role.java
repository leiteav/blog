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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    private String name;

    public Role(Long id) {
        this.idRole = id;
    }
    public Role(String name) {
        this.name = name;
    }


}