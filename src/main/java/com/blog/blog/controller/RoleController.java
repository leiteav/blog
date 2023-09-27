package com.blog.blog.controller;


import com.blog.blog.dto.UserDTO;
import com.blog.blog.model.Role;
import com.blog.blog.model.User;
import com.blog.blog.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    private UserRoleService userRoleService;

    @Autowired
    RoleController(UserRoleService userRoleService){
        this.userRoleService = userRoleService;
    }

    @PostMapping("/create")
    public ResponseEntity<Role> create(@RequestBody Role role){
        return new ResponseEntity<>(userRoleService.createRole(role), HttpStatus.OK);
    }
/*
    @PostMapping("/assign")
    public ResponseEntity<User> addRoleToUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userRoleService.addRoleToUser(userDTO), HttpStatus.OK);
    }
*/

    @GetMapping("/list")
    public ResponseEntity<List<Role>> findAll(){
        return new ResponseEntity<>(userRoleService.findAllRole(), HttpStatus.OK);
    }

}
