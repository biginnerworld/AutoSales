package com.karpovskiy.autosales.service.implementation;

import com.karpovskiy.autosales.generator.IDGenerator;
import com.karpovskiy.autosales.model.User;
import com.karpovskiy.autosales.repository.UserRepository;
import com.karpovskiy.autosales.security.Role;
import com.karpovskiy.autosales.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(User user) {
        user.setId(IDGenerator.generateID());
        user.setRole(Role.USER);
        user.setDeleted(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.getById(id);
    }

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void patchUser(User editedUser, String id) {
        User user = getUserById(id);

        user.setPassword(editedUser.getPassword());
        user.setEmail(editedUser.getEmail());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.getById(id).setDeleted(true);
    }
}
