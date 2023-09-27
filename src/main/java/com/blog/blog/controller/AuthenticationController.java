package com.blog.blog.controller;

import com.blog.blog.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private AuthenticationManager manager;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.manager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody UserDTO userDTO){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        manager.authenticate(token);
        return ResponseEntity.ok().build();
    }

}
