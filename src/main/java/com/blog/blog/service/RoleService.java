package com.blog.blog.service;


import com.blog.blog.dto.UserDTO;
import com.blog.blog.exception.BlogException;
import com.blog.blog.model.Role;
import com.blog.blog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    RoleRepository roleRepository;

    UserService userService;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    public Role create(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public void addRoleToUser(UserDTO userDTO) throws BlogException {
        if (!userService.userExists(userDTO.getUsername()))
            throw new BlogException("Usuário não existe.");

        if (!userDTO.getRolesName().isEmpty()){
            userDTO.getRolesName().stream().forEach(role -> {
                if (!roleExists(role))
                    throw new BlogException("Roles não encontrada.");
            });
            userService.update(userDTO);
        }
    }

    private Optional<Role> findRoleByName(String name){
        return Optional.of(roleRepository.findByName(name));
    }

    private boolean roleExists(String name){
        return findRoleByName(name).isPresent();
    }

    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
