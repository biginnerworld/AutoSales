package com.karpovskiy.autosales.service.implementation;

import com.karpovskiy.autosales.model.User;
import com.karpovskiy.autosales.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User tempUser =userRepository.findUserByUsername(username);
        if (tempUser == null){
            throw new UsernameNotFoundException("Unknown user:" + username);
        }

        UserDetails user = org.springframework.security.core.userdetails.User.builder()
                .username(tempUser.getUsername())
                .password(tempUser.getPassword())
                .roles(tempUser.getRole().name())
                .authorities(tempUser.getRole().getGrantedAuthority())
                .accountExpired(false)
                .accountLocked(false)
                .disabled(false)
                .build();

        return user;
    }
}
