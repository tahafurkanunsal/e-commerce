package com.tfunsal.ecommerce.services.jwt;

import com.tfunsal.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.tfunsal.ecommerce.entity.User> optionalUser = userRepository.findFirstByEmail(username);

        if (optionalUser.isEmpty()) throw new UsernameNotFoundException("Username not found" , null);
        return new User(optionalUser.get().getEmail(), optionalUser.get().getPassword(), new ArrayList<>());

    }
}
