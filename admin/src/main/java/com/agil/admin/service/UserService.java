package com.agil.admin.service;

import com.agil.admin.ResourceNotFoundException;
import com.agil.admin.model.User;
import com.agil.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(Long id, User user) {
        return userRepository.save(user);
    }
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
    public Long getUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return user.getId();
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }
}

