package com.blog.blog.controller;

import com.blog.blog.dto.UserDTO;
import com.blog.blog.model.User;
import com.blog.blog.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private AuthenticationManager manager;
    private TokenManager tokenGenerator;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, TokenManager generator) {
        this.manager = authenticationManager;
        this.tokenGenerator = generator;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody UserDTO userDTO){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        Authentication authentication = manager.authenticate(token);
        return ResponseEntity.ok(tokenGenerator.generateToken((User) authentication.getPrincipal()));
    }

}
