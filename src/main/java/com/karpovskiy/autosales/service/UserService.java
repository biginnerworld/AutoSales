package com.karpovskiy.autosales.service;

import com.karpovskiy.autosales.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void createUser(User user);

    User getUserById(String id);

    void patchUser(User editedUser, String id);

    void deleteUser(String id);
}
