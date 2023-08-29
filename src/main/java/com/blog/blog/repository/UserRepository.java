package com.blog.blog.repository;

import java.util.UUID;

import com.blog.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

}