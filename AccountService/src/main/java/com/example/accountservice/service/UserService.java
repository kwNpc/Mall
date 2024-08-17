package com.example.accountservice.service;

import com.example.accountservice.dto.UserDto;
import com.example.accountservice.entity.User;
import com.example.accountservice.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDto registerUser(UserDto userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent() ||
                userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Username or Email already taken");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        //user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public Optional<UserDto> loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        //if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
        //    return Optional.of(convertToDTO(user.get()));
        //}

        if (user.isPresent() && password.equals(user.get().getPassword())) {
            return Optional.of(convertToDTO(user.get()));
        }

        return Optional.empty();
    }

    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    public UserDto updateUser(Long id, UserDto userDTO) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setUpdatedAt(LocalDateTime.now());

            User updatedUser = userRepository.save(user);
            return convertToDTO(updatedUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto convertToDTO(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setCreatedAt(user.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME));
        userDTO.setUpdatedAt(user.getUpdatedAt().format(DateTimeFormatter.ISO_DATE_TIME));
        // 检查是否有设置密码字段
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

}
