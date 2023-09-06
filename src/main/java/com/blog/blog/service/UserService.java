package com.blog.blog.service;

import com.blog.blog.dto.UserDTO;
import com.blog.blog.model.Role;
import com.blog.blog.model.User;
import com.blog.blog.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final RoleService roleService;

    @Autowired
    UserService(UserRepository userRepository, RoleService roleService){
        this.userRepository = userRepository;
        this.roleService = roleService;
    }
    public User create(User user) {
        if (!userExists(user.getUsername()))
            return userRepository.save(user);

        return null;
    }

    public Optional<UserDTO> findUserByUsernameToDto(String username){
        if (Objects.nonNull(username)){
            final User user = this.userRepository.findByUsername(username);
            if (Objects.nonNull(user))
            return Optional.of(modelMapper.map(user, UserDTO.class));
        }
        return Optional.empty();
    }

    Optional<User> findByUsername(String username){
        return Optional.of(userRepository.findByUsername(username));
    }

    public boolean userExists(String username){
        return findByUsername(username).isPresent();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(UserDTO userDTO){
          User user = findByUsername(userDTO.getUsername()).orElseThrow();
          user.setUsername(userDTO.getUsername());
          user.setPassword(userDTO.getPassword());

          if (!userDTO.getRolesName().isEmpty()) {
            List<Role> newRoles = mapperRoles(userDTO.getRolesName());
            user.setRole(newRoles);
          }
        return userRepository.save(user);
    }

    List<Role> mapperRoles(List<String> rolesName){
        List<Role> rolesList = new ArrayList<>();
        if (!rolesName.isEmpty()) {
            rolesName.stream().forEach(roleName -> {
                Role role = roleService.findByName(roleName);
                rolesList.add(role);
            });
        }
        return rolesList;
    }
}
