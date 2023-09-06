package com.blog.blog.service;

import com.blog.blog.dto.UserDTO;
import com.blog.blog.model.User;
import com.blog.blog.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User create(User user) {
        if (!userExists(user.getUsername()))
            return userRepository.save(user);

        return null;
    }

    public Optional<UserDTO> findUserByUsername(String username){
        if (Objects.nonNull(username)){
            final User user = this.userRepository.findByUsername(username);
            return Optional.ofNullable(modelMapper.map(user, UserDTO.class));
        }
        return Optional.empty();
    }

    public boolean userExists(String username){
        return findUserByUsername(username).isPresent();
    }

}
