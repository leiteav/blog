package com.blog.blog.controller;


import com.blog.blog.model.Role;
import com.blog.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    private RoleService roleService;

    @Autowired
    RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping("create")
    public Role create(@RequestBody Role role){
        return roleService.create(role);
    }



}
