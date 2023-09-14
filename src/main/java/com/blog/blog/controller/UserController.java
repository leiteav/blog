package com.blog.blog.controller;

import com.blog.blog.dto.UserDTO;
import com.blog.blog.model.User;
import com.blog.blog.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    UserRoleService userRoleService;

    @Autowired
    UserController(UserRoleService userService){
        this.userRoleService = userService;
    }

    @PostMapping("/create")
    public User create(@RequestBody UserDTO user){
        return userRoleService.createUser(user);
    }

    @GetMapping("/list")
    public List<User> findAll(){
        return userRoleService.findAllUser();
    }
}
