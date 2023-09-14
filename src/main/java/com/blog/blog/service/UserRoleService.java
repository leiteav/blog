package com.blog.blog.service;

import com.blog.blog.dto.UserDTO;
import com.blog.blog.exception.BlogException;
import com.blog.blog.model.Role;
import com.blog.blog.model.User;
import com.blog.blog.repository.RoleRepository;
import com.blog.blog.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserRoleService {
    UserRepository userRepository;

    RoleRepository roleRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserRoleService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    public User addRoleToUser(UserDTO userDTO) throws BlogException {
        if (!userExists(userDTO.getUsername()))
            throw new BlogException("Usuário não existe.");

        if (!userDTO.getRolesName().isEmpty()) {
            userDTO.getRolesName().stream().forEach(role -> {
                if (!roleExists(role))
                    throw new BlogException("Roles não encontrada.");
            });
            return updateRole(userDTO);
        }
        throw new BlogException("Role não localizada.");
    }

    private Optional<Role> findRoleByName(String name) {
        return Optional.of(roleRepository.findByName(name));
    }

    private boolean roleExists(String name) {
        return findRoleByName(name).isPresent();
    }

    public Optional<Role> findByName(String roleName) {
        return Optional.ofNullable(roleRepository.findByName(roleName));
    }

    private List<Role> mapperRoles(List<String> rolesName) {
        final List<Role> rolesList = new ArrayList<>();
        if (!rolesName.isEmpty()) {
            rolesName.stream().forEach(roleName -> {
                final Optional<Role> role = findByName(roleName);
                if (!role.isPresent())
                    throw new BlogException("Role inválida.");
                rolesList.add(role.get());
            });
        }
        return rolesList;
    }

    public User createUser(UserDTO userDTO) {
        if (!userExists(userDTO.getUsername())) {
            List<Role> roles = mapperRoles(userDTO.getRolesName());
            final User user = mapperToUser(userDTO);
            user.setRoles(roles);
            return userRepository.save(user);
        }
        throw new BlogException("Username já existente.");
    }

    public Optional<UserDTO> findUserByUsernameToDto(String username) {
        if (Objects.nonNull(username)) {
            final User user = this.userRepository.findByUsername(username);
            if (Objects.nonNull(user))
                return Optional.of(modelMapper.map(user, UserDTO.class));
        }
        return Optional.empty();
    }

    Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public boolean userExists(String username) {
        return findByUsername(username).isPresent();
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User updateUser(UserDTO userDTO) {
        User user = findByUsername(userDTO.getUsername()).orElseThrow();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        return userRepository.save(user);
    }

    public User updateRole(UserDTO userDTO) {
        User user = findByUsername(userDTO.getUsername()).orElseThrow();
        List<Role> roles = mapperRoles(userDTO.getRolesName());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    User mapperToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
