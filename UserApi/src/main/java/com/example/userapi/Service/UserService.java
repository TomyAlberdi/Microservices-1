package com.example.userapi.Service;

import com.example.userapi.DTO.UserDTO;
import com.example.userapi.Entity.User;
import com.example.userapi.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> list() {
        return userRepository.findAll();
    }

    public User add(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setDescription("Hey there! I'm using Notes App.");
        if (userRepository.findByEmail(userDTO.getEmail()).isEmpty()) {
            user.setEmail(userDTO.getEmail());
        }
        String encodedPassword = this.passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
