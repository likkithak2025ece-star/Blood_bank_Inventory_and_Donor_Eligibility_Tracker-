package com.blood.bank.service;

import com.blood.bank.entity.User;
import com.blood.bank.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register a new user
    public User registerUser(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        String encryptedPassword =
                passwordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    // Find user by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public boolean passwordMatches(String rawPassword, String encryptedPassword) {
    return passwordEncoder.matches(rawPassword, encryptedPassword);
}
public boolean changePassword(
        String username,
        String oldPassword,
        String newPassword) {

    User user = userRepository
            .findByUsername(username)
            .orElse(null);

    if (user == null) {
        return false;
    }

    if (!passwordEncoder.matches(
            oldPassword,
            user.getPassword())) {

        return false;
    }

    user.setPassword(
            passwordEncoder.encode(newPassword)
    );

    userRepository.save(user);

    return true;
}
public java.util.List<User> getAllUsers() {
    return userRepository.findAll();
}
}