package com.karpovskiy.autosales.repository;

import com.karpovskiy.autosales.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUsername(String username);
}
